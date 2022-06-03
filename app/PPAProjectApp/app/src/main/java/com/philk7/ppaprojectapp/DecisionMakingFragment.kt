package com.philk7.ppaprojectapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.philk7.ppaprojectapp.backend.BackendRequest
import com.philk7.ppaprojectapp.backend.DecisionBackendHandling
import com.philk7.ppaprojectapp.databinding.FragmentDecisionMakingBinding
import com.philk7.ppaprojectapp.enums.NotificationIdEnum
import com.philk7.ppaprojectapp.utils.DecisionRequest
import com.philk7.ppaprojectapp.utils.LocationProcessing
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.List
import kotlin.collections.set



class DecisionMakingFragment : Fragment() {

    private lateinit var binding: FragmentDecisionMakingBinding
    private lateinit var sp: SharedPreferences
    private var receiveDMN: Boolean = true
    private var comment: String = ""


    // start of adapter classes

    /**
     * Adapter for showing decision requests in a recycler view.
     * @param mDecisionRequests: the read decision requests to answer
     * @param theFrag: reference to this fragment
     * @param pid: current user's ID
     */
    class DecisionRequestAdapter(
        private val mDecisionRequests: List<DecisionRequest>,
        private val theFrag: DecisionMakingFragment,
        private val pid: String
    ): RecyclerView.Adapter<DecisionRequestAdapter.ViewHolder>() {

        private var remainingDecisions = mDecisionRequests.size  // count down to 0

        inner class ViewHolder(decisionRequestView: View):
            RecyclerView.ViewHolder(decisionRequestView) {
            val theCard: CardView = decisionRequestView.findViewById(R.id.decisionRequestCard)
            val allowButton: ImageButton = decisionRequestView.findViewById(R.id.allowButton)
            val denyButton: ImageButton = decisionRequestView.findViewById(R.id.denyButton)
            val deviceText: TextView = decisionRequestView.findViewById(R.id.deviceText2)
            val dataText: TextView = decisionRequestView.findViewById(R.id.dataText2)
        }

        // inflate layout
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val parContext = parent.context
            val inflater = LayoutInflater.from(parContext)
            val drView = inflater.inflate(
                R.layout.item_decision_request,
                parent, false
            )

            return ViewHolder(drView)
        }

        // set context and behavior of item view
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val dr = this.mDecisionRequests[position]
            holder.deviceText.text = dr.deviceType
            holder.dataText.text = dr.dataType
            // duration of animation played in onPressDecisionButton
            val animDelay = this.theFrag.resources
                .getInteger(R.integer.card_slideout_custom_duration).toLong()

            // NOTE: When making a decision, the location currently selected in spinner is used.
            // set action for clicking on allow / deny buttons
            holder.allowButton.setOnClickListener {
                holder.allowButton.elevation = 0F
                holder.denyButton.elevation = 24F
                this.onPressDecisionButton(
                    this.theFrag.requireContext(),
                    holder.theCard,
                    this.pid,
                    this.theFrag.binding.placeSpinner2.selectedItem.toString(),
                    dr.deviceType,
                    dr.dataType,
                    true
                )
                // remove card when slide out
                Handler(Looper.getMainLooper()).postDelayed({
                    holder.theCard.visibility = View.GONE
                }, animDelay)
                if(remainingDecisions == 0) this.onAllDecisionsMade()
            }

            holder.denyButton.setOnClickListener {
                holder.denyButton.elevation = 0F
                holder.allowButton.elevation = 24F
                this.onPressDecisionButton(
                    this.theFrag.requireContext(),
                    holder.theCard,
                    this.pid,
                    this.theFrag.binding.placeSpinner2.selectedItem.toString(),
                    dr.deviceType,
                    dr.dataType,
                    false
                )
                Handler(Looper.getMainLooper()).postDelayed({
                    holder.theCard.visibility = View.GONE
                }, animDelay)
                if(remainingDecisions == 0) this.onAllDecisionsMade()
            }
        }

        override fun getItemCount(): Int {
            return mDecisionRequests.size
        }


        /**
         * Inserts the decision, and removes the decision card from the panel.
         * @param context: (outer) context
         * @param card: the current card view
         * @param pid: user ID
         * @param place: currently indicated place by the fragment
         * @param device: the decision's device type
         * @param data: the decision's data type
         * @param access: whether the decision request is allowed / denied
         */
        private fun onPressDecisionButton(
            context: Context, card: CardView, pid: String, place: String,
            device: String, data: String, access: Boolean
        ){
            // insert decision USING WEIGHT BOOST
            val success = DecisionBackendHandling()
                .insertUserDecision(context, pid, place, device, data, access, true)
            if(!success) Toast.makeText(
                context,
                "Could not insert decision. Is the server down?", Toast.LENGTH_SHORT
            ).show()
            this.remainingDecisions--  // update remaining decisions
            card.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_out_card))

        }


        /**
         * Called when all decision cards are gone. Writes the (last entered) place to the
         *  location cache, sends remaining collected information to the server,
         *  and returns to the home screen.
         */
        private fun onAllDecisionsMade(){

            // selected location?
            var ll = this.theFrag.binding.placeSpinner2.selectedItem.toString()
            if(ll != "")  // write value to its cache
                LocationProcessing.writeLastPlace(this.theFrag.requireContext(), ll)
            else  // if nothing selected, don't write cache, instead read last known value
                ll = LocationProcessing.readLastPlace(this.theFrag.requireContext())

            // server request
            val params = HashMap<String, String>()
            params["pid"] = this.pid
            params["t"] = SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.GERMANY
            ).format(Date())
            params["l"] = ll
            params["r"] = this.theFrag.receiveDMN.toString()
            params["c"] = this.theFrag.comment
            val br = BackendRequest(this.theFrag.requireContext().resources.getString(R.string.backend_address), "pcomment_insert",
                "POST", params, this.theFrag.requireContext())

            var response = "norun"
            val run = Runnable {
                response = br.performRequest()
            }
            val t = Thread(run)
            t.start()
            t.join(this.theFrag.requireContext().resources
                .getInteger(R.integer.server_request_timeout_std)
                .toLong())

            try {  // react to response
                val result = JSONObject(response)["result"] as String
                if (!result.startsWith("SUCCESS"))   Toast.makeText(
                    this.theFrag.requireContext(),
                    "Could not insert information.", Toast.LENGTH_SHORT).show()
            }
            catch(e: JSONException){  // either timeout, or bad response
                e.printStackTrace()
                Toast.makeText(this.theFrag.requireContext(),
                    "Bad server response.", Toast.LENGTH_SHORT).show()
            }

            // go back to home menu
            val homeIntent = Intent(this.theFrag.requireContext(), HomeActivity::class.java)
            homeIntent
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            this.theFrag.requireContext().startActivity(homeIntent)
        }

    }

    // end of adapter classes

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_decision_making,
            container, false
        )

        val fragNamespace = this  // outer namespace for reference in listeners etc.

        // cancel notification that opened this
        NotificationManagerCompat.from(this.requireContext())
            .cancel(NotificationIdEnum.NOW__PRIVACY_DECISION__NTF_ID.id)

        // hide action bar
        val ab = (activity as AppCompatActivity).supportActionBar!!
        ab.hide()

        // read from sharedPref

        val sp = this.activity?.getSharedPreferences(
            resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        if (sp != null) {
            this.sp = sp
        }

        val pid: String
        with(this.sp.edit()){
            pid = sp?.getString("pid", "").toString()
            this.apply()
        }

        // read from request cache
        val drs = DecisionRequest().readRequestCache(this.requireContext())

        // set recycler view adapter
        val adapter = DecisionRequestAdapter(drs, fragNamespace, pid)
        binding.decisionRequestsRecycler.adapter = adapter
        binding.decisionRequestsRecycler.layoutManager = LinearLayoutManager(this.requireContext())


        // behavior of other components

        binding.commentDoneButton.setOnClickListener {
            this.comment = binding.commentField.text.toString()
            val view = this.view
            if(view != null){
                val iman = this.context
                ?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                iman.hideSoftInputFromWindow(view.windowToken, 0)
            }
            binding.commentCard.visibility = View.GONE
        }
        binding.rqYesButton.setOnClickListener {
            binding.receiveQuestionCard.visibility = View.GONE
        }
        binding.rqNoButton.setOnClickListener {
            this.receiveDMN = false
            binding.receiveQuestionCard.visibility = View.GONE
        }

        // load initial selection from location cache
        val currentLocation = LocationProcessing.readLastPlace(this.requireContext())
        val placeOptions = this.requireContext().resources.getStringArray(R.array.place_examples)
        val currentOptionIndex = placeOptions.indexOf(currentLocation)
        if(currentOptionIndex != -1)
            binding.placeSpinner2.setSelection(currentOptionIndex)
        else
            binding.placeSpinner2.setSelection(placeOptions.indexOf("Other"))


        return binding.root
    }


}