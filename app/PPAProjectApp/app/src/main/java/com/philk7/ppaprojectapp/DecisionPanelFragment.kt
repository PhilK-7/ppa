package com.philk7.ppaprojectapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.philk7.ppaprojectapp.assistants.AssistantEnum
import com.philk7.ppaprojectapp.backend.DecisionBackendHandling
import com.philk7.ppaprojectapp.databinding.FragmentDecisionPanelBinding
import com.philk7.ppaprojectapp.enums.ReceiverIntentEnum
import com.philk7.ppaprojectapp.utils.NotificationHandler


open class DecisionPanelFragment : Fragment() {
    private lateinit var binding: FragmentDecisionPanelBinding
    private lateinit var ab: ActionBar

    // also used in DailyDecisionsFragment
    private var currentPid: String = ""  // is assigned later
    protected lateinit var sharedPref: SharedPreferences

    // RecyclerView classes below

    data class Decision(
        val did: Int, val timestamp: String, val place: String,
        val device: String, val data: String, val access: Boolean
    )

    /**
     * The adapter for the decision recycler view.
     * @param mDecisions: the loaded decisions
     * @param outerContext: fragment's context
     * @param outerThis: direct reference to fragment namespace
     * @param pid: user's ID
     */
    class DecisionsAdapter(
        private val mDecisions: List<Decision>,
        private val outerContext: Context,
        private val outerThis: DecisionPanelFragment, private val pid: String
    ) :
        RecyclerView.Adapter<DecisionsAdapter.ViewHolder>() {

        inner class ViewHolder(decisionItemView: View) : RecyclerView.ViewHolder(decisionItemView) {
            val timeView: TextView = decisionItemView.findViewById(R.id.timeText)
            val placeView: TextView = decisionItemView.findViewById(R.id.placeText)
            val deviceView: TextView = decisionItemView.findViewById(R.id.deviceText2)
            val dataView: TextView = decisionItemView.findViewById(R.id.dataText2)
            val accessView: TextView = decisionItemView.findViewById(R.id.accessText)
            val accessIndicator: ImageView = decisionItemView.findViewById(R.id.accessIndicatorIcon)
            val theCard: CardView = decisionItemView.findViewById(R.id.decisionCard)
        }

        // inflate the decision item layout and return a ViewHolder
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): DecisionsAdapter.ViewHolder {
            val parContext = parent.context
            val inflater = LayoutInflater.from(parContext)
            val decisionView = inflater.inflate(R.layout.item_decision, parent, false)

            return ViewHolder(decisionView)
        }

        // set item properties to an item's layout
        override fun onBindViewHolder(holder: DecisionsAdapter.ViewHolder, position: Int) {
            val decision = mDecisions[position]  // get decision at index
            // set item layout content
            holder.timeView.text = decision.timestamp
            holder.placeView.text = decision.place
            holder.deviceView.text = decision.device
            holder.dataView.text = decision.data
            holder.accessView.text = decision.access.toString()

            // also change icon accordingly
            val counterMsgPart: String = if (decision.access) {
                holder.accessIndicator.setImageResource(R.drawable.ic_allow_2)
                holder.accessIndicator.setColorFilter(R.color.yes)
                "Deny"
            } else {
                holder.accessIndicator.setImageResource(R.drawable.ic_deny_2)
                holder.accessIndicator.setColorFilter(R.color.no)
                "Allow"
            }

            // show decision change dialog
            holder.theCard.setOnClickListener {
                val dialog = MaterialAlertDialogBuilder(
                    ContextThemeWrapper(
                        outerContext,
                        R.style.AlertDialogCustom
                    )
                )
                    .setTitle("Change Decision")
                    .setMessage("Do you want to change this privacy decision?")
                    .setNegativeButton(
                        "Cancel"
                    ) { dialogInterface, _ -> dialogInterface.cancel() }
                    .setPositiveButton(
                        "Change to $counterMsgPart"
                    ) { _, _ ->
                        // change decision accordingly
                        val success = this
                            .changePrivacyDecision(mDecisions[position], !decision.access)

                        // reload? and indicate change
                        if (success) {
                            // scroll up, if possible
                            try {
                                // needs initialized binding -> check if is daily overview instead
                                if (this.outerThis !is DailyDecisionsFragment)
                                    this.outerThis.binding
                                        .decisionRecycler.scrollToPosition(0)
                            } catch (e: UninitializedPropertyAccessException) {
                                // happens when adapter is used by DailyDecisionsFragment
                                e.printStackTrace()
                            }


                            // reload/update fragment view
                            if (this.outerThis !is DailyDecisionsFragment) {
                                val ft = this.outerThis.parentFragmentManager.beginTransaction()
                                ft.detach(outerThis).attach(outerThis).commit()
                            } else {
                                // remove card from daily overview
                                holder.theCard.startAnimation(AnimationUtils
                                    .loadAnimation(outerContext, R.anim.slide_out_card))
                                Handler(Looper.getMainLooper()).postDelayed({
                                    holder.theCard.visibility = View.GONE
                                },
                                    outerThis.resources
                                        .getInteger(R.integer.card_slideout_custom_duration)
                                        .toLong()
                                )
                            }

                            Toast.makeText(
                                outerContext,
                                "Decision changed!", Toast.LENGTH_LONG
                            ).show()

                        } else {
                            Toast.makeText(
                                outerContext,
                                "Something went wrong.", Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                dialog.show()
            }

        }

        override fun getItemCount(): Int {
            return mDecisions.size
        }

        /**
         * Changes a privacy decision, by deleting its old instance, and inserting the new one
         *  into the table pdecision. Uses a new decision ID.
         * @param oldDecision: the Decision that is modified / replaced
         * @param newAccess: whether the decision allows or denies access
         * @return whether both parts of the request were successful
         */
        private fun changePrivacyDecision(oldDecision: Decision, newAccess: Boolean): Boolean {

            // first delete old decision instance
            val r1b = DecisionBackendHandling()
                .deleteUserDecision(oldDecision.did, this.outerContext)

            // then insert changed, new decision instance; update weights BOOSTED
            val r2b = DecisionBackendHandling()
                .insertUserDecision(
                    this.outerContext, this.pid, oldDecision.place,
                    oldDecision.device, oldDecision.data, newAccess, true
                )

            return r1b && r2b
        }

    }

    // end of RecyclerView classes


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_decision_panel, container, false
        )

        // assimilate action bar
        ab = (activity as AppCompatActivity).supportActionBar!!
        ab.setDisplayHomeAsUpEnabled(true)
        ab.title = resources.getString(R.string.home_frag3_title)


        // get sharedPref and set globally
        val sp = this.activity?.getSharedPreferences(
            resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        if (sp != null) {
            this.sharedPref = sp
        }

        // call to fetch decisions and set recycler adapter
        this.setupDecisionCardRecycler(binding.decisionRecycler, false)

        // set displayed time and switch toggle
        val ntt: String
        val notf: Boolean
        with(sharedPref.edit()) {
            ntt = sharedPref.getString(DAILY_NOTF_TIME_KEY, "00:00").toString()
            notf = sharedPref.getBoolean(DAILY_NOTF_TOGGLE_KEY, false)
            this?.apply()
        }
        binding.notificationTimeText.text = ntt
        binding.overviewNotificationToggle.isChecked = notf

        /* If using Automatic Assistant (or another assistant that has daily overviews enabled
         by default), do not show toggle possibility, and do not set any onClickListener to it.
         */
        val outerContext = this.requireContext()
        if (HomeActivity.determineAssistant(outerContext)
            == AssistantEnum.AUTOMATIC_ASSISTANT
        )
            binding.overviewNotificationToggle.visibility = View.INVISIBLE
        else  // normal case: user can enable/disable optional daily overviews
            binding.overviewNotificationToggle.setOnClickListener {
                with(sharedPref.edit()) {
                    this?.putBoolean(
                        DAILY_NOTF_TOGGLE_KEY,
                        binding.overviewNotificationToggle.isChecked
                    )
                    this?.apply()
                }

                val timeParts = ntt.split(":")
                val h = if (timeParts[0].startsWith("0"))
                    timeParts[0][1].toString()
                else timeParts[0]
                val m = if (timeParts[1].startsWith("0"))
                    timeParts[1][1].toString()
                else timeParts[1]

                // either activate or deactivate notification
                if ((it as SwitchCompat).isChecked)
                    scheduleReminderNotification(
                        this.requireContext(), Integer.parseInt(h), Integer.parseInt(m)
                    )
                else cancelAlarm(outerContext)
            }

        // time picker dialogue button
        binding.changeTimeButton.setOnClickListener {

            TimePickerDialog(
                outerContext, R.style.TimePickerCustom,
                { _, hour, minute ->  // onTimeSet

                    // update shown value here
                    var hStr = hour.toString()
                    if (hStr.length < 2)
                        hStr = "0$hStr"
                    var mStr = minute.toString()
                    if (mStr.length < 2)
                        mStr = "0$mStr"
                    val timeStr = "$hStr:$mStr"
                    binding.notificationTimeText.text = timeStr

                    // toggle notifications to on
                    if (!binding.overviewNotificationToggle.isChecked) {
                        binding.overviewNotificationToggle.toggle()
                        with(sharedPref.edit()) {
                            this?.putBoolean(DAILY_NOTF_TOGGLE_KEY, true)
                            this?.apply()
                        }
                    }

                    // also update value in sharedPref
                    with(sharedPref.edit()) {
                        this?.putString(DAILY_NOTF_TIME_KEY, timeStr)
                        this?.apply()
                    }


                    scheduleReminderNotification(outerContext, hour, minute)
                },

                0, 0, true
            ).show()

        }


        return binding.root
    }


    /**
     * Receives and processes the current user's decisions from the backend,
     *  and sets the RecyclerView properties accordingly.
     * @param recyclerView: the current layout's recycler view
     * @param filterToday: whether to only keep today's decisions
     * @return list of loaded decisions
     */
    protected fun setupDecisionCardRecycler(recyclerView: RecyclerView, filterToday: Boolean):
            MutableList<Decision>? {

        // get user's decisions
        var pid: String?
        with(sharedPref.edit()) {
            pid = sharedPref.getString(FirstIDActivity.PID_KEY, "")
            this?.apply()
        }
        currentPid = pid.toString()   // assign to save as global variable

        val decisionList = this.context?.let {
            this.activity?.let { it1 ->
                DecisionBackendHandling().getUserDecisions(
                    it,
                    it1, currentPid, filterToday
                )
            }
        }

        // setup RecyclerView functionality
        val adapter = if (decisionList != null && this.context != null)
            DecisionsAdapter(
                decisionList, this.requireContext(),
                this, currentPid
            )
        else null

        recyclerView.adapter = adapter!!
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        return decisionList
    }


    companion object {

        const val DAILY_NOTF_TIME_KEY = "daily_overview_time"
        const val DAILY_NOTF_TOGGLE_KEY = "daily_overview_toggle"


        /**
         * Schedules the reminder notification that opens the daily decisions overview
         *  for a specific selected time.
         * @param context: caller context, either this fragment, or home activity
         * @param hour: the time's clock hour
         * @param minute: the time's clock minute
         */
        fun scheduleReminderNotification(context: Context, hour: Int, minute: Int) {
            cancelAlarm(context)  // remove previous alarm instance

            val sp = NotificationHandler.calculateSchedulingPointClock(hour, minute)

            // schedule (repeating) notification
            NotificationHandler.scheduleActionAlarmToMainReceiver(
                context, sp,
                ReceiverIntentEnum.ACTION_DAILY_REMINDER.msg,
                true, AlarmManager.INTERVAL_DAY
            )
        }


        /**
         * Cancels a set daily reminder alarm instance.
         * @param context: caller context
         */
        fun cancelAlarm(context: Context) {
            // clone pending intent that schedules the alarm
            val pintent = PendingIntent.getBroadcast(
                context, 1,
                Intent(
                    context,
                    HomeActivity.Companion.MainReceiver::class.java
                )
                    .setAction(ReceiverIntentEnum.ACTION_DAILY_REMINDER.msg),
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            // cancel the pending intent
            pintent.cancel()
            val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            am.cancel(pintent)

        }

    }

}