package com.philk7.ppaprojectapp

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.philk7.ppaprojectapp.assistants.AssistantEnum
import com.philk7.ppaprojectapp.backend.BackendRequest
import com.philk7.ppaprojectapp.databinding.FragmentAssistantChoiceBinding
import com.philk7.ppaprojectapp.enums.BackendInsertQueryEnum
import com.philk7.ppaprojectapp.enums.PersonaTypeEnum
import org.json.JSONException
import org.json.JSONObject
import java.security.InvalidParameterException

class AssistantChoiceFragment : Fragment() {
    private lateinit var binding: FragmentAssistantChoiceBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_assistant_choice,
            container, false
        )

        // animation
        val animElems = listOf(
            binding.assistantDescrCard, binding.choiceNAButton,
            binding.choiceRAButton, binding.choiceAutoButton, binding.confirmChoiceButton
        )
        animElems.forEach {
            it.startAnimation(
                AnimationUtils.loadAnimation(this.context, R.anim.show_delay)
            )
        }

        // set button on click listeners
        binding.choiceNAButton.setOnClickListener {
            this.onChoiceButtonClick(AssistantEnum.NOTIFICATION_ASSISTANT)
        }
        binding.choiceRAButton.setOnClickListener {
            this.onChoiceButtonClick(AssistantEnum.RECOMMENDATION_ASSISTANT)
        }
        binding.choiceAutoButton.setOnClickListener {
            this.onChoiceButtonClick(AssistantEnum.AUTOMATIC_ASSISTANT)
        }

        // Save assistant, submit data to server, show permission info, and go to home menu.
        binding.confirmChoiceButton.setOnClickListener {
            // check if choice made
            if (binding.assistantTitle.text == getString(R.string.empty_placeholder)) {
                val toast = Toast.makeText(
                    this.requireContext(),
                    "Please choose an Assistant first!", Toast.LENGTH_SHORT
                )
                toast.show()
            } else {

                // the confirm dialog
                val outerContext = this.requireContext()
                val confirmDialog = MaterialAlertDialogBuilder(
                    ContextThemeWrapper(outerContext, R.style.AlertDialogCustom))
                    .setTitle("Confirm Privacy Assistant Choice")

                    .setPositiveButton(R.string.confirm) { _, _ ->
                        // write assistant choice to sharedPref
                        val sharedPref = this.activity?.getSharedPreferences(
                            resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
                        )
                        val chosenAssistant: AssistantEnum = when (binding.assistantTitle.text) {
                            getString(R.string.na_header) -> AssistantEnum.NOTIFICATION_ASSISTANT
                            getString(R.string.ra_header) -> AssistantEnum.RECOMMENDATION_ASSISTANT
                            getString(R.string.auto_header) -> AssistantEnum.AUTOMATIC_ASSISTANT
                            else -> AssistantEnum.NONE
                        }
                        with(sharedPref?.edit()) {
                            this?.putInt(ASSISTANT_KEY, chosenAssistant.assistantId)
                            this?.apply()
                        }

                        // send all collected information to server

                        /*
                        Can skip part where backend is (repeatedly) queried to insert all information
                        collected in the intro survey. Even if the queries are unsuccessful
                        (e.g. the user already has data, which the database rejects then, error 500),
                        the app still continues to the home menu.
                        */
                        if (!NO_SERVER_INFO_UPDATE) {
                            /* Perform the backend requests to insert information into
                            plikert, pdemo, ppassign and ppersona. */

                            val appContext = this.activity?.applicationContext

                            if (appContext != null) {

                                val querySubjects = listOf(
                                    BackendInsertQueryEnum.LIKERT,
                                    BackendInsertQueryEnum.DEMO,
                                    BackendInsertQueryEnum.PASSIGN,
                                    BackendInsertQueryEnum.PERSONA
                                )

                                val successList = mutableListOf<Boolean>()  // track query results

                                for (subject in querySubjects) {

                                    val params = getQueryParamsFromSp(subject)
                                    val req = BackendRequest(
                                        this.requireContext().resources.getString(R.string.backend_address),
                                        when (subject) {  // which query function to use for POST
                                            BackendInsertQueryEnum.LIKERT -> "plikert_insert"
                                            BackendInsertQueryEnum.DEMO -> "pdemo_insert"
                                            BackendInsertQueryEnum.PASSIGN -> "ppassign_insert"
                                            BackendInsertQueryEnum.PERSONA -> "ppersona_insert"
                                            else -> ""
                                        },
                                        "POST", params, appContext
                                    )

                                    var response = "norun"
                                    val runnable = Runnable {
                                        response = req.performRequest()
                                    }
                                    val sepThread = Thread(runnable)
                                    sepThread.start()
                                    sepThread.join(this.requireContext().resources
                                        .getInteger(R.integer.server_request_timeout_std)
                                        .toLong())  // try to join thread within timeout

                                    // check response
                                    if (response == "norun") Log.i(
                                        "Query",
                                        "Unknown error!"
                                    )
                                    else {  // got normal response

                                        try {
                                            val rj = JSONObject(response)
                                            val result = rj["result"]
                                            if (result is Boolean) {
                                                if (result) {
                                                    Log.i(
                                                        "Query",
                                                        "Insertion successful."
                                                    )
                                                    successList.add(true)
                                                } else {
                                                    Log.i("Query", "Insertion failed!")
                                                    successList.add(false)
                                                }
                                            }
                                        } catch (e: JSONException) {
                                            // occurs when performRequest throws exception
                                            /* When the backend returns with status code 500
                                             (here: POST
                                            unsuccessful because DB condition not satisfied),
                                             it still returns
                                            a JSON. But still, Java/Kotlin then disallow
                                             reading the input,
                                            and throw an IOException instead.*/
                                            successList.add(false)
                                            e.printStackTrace()
                                        }

                                    }

                                }

                                println(successList)

                            }
                        }

                        // update progress bar for the last time
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            this.activity?.findViewById<ProgressBar>(R.id.surveyProgressBar)
                                ?.setProgress(100, true)
                        else
                            this.activity
                                ?.findViewById<ProgressBar>(R.id.surveyProgressBar)?.progress = 100

                        // show second alert dialog for info on permissions
                        MaterialAlertDialogBuilder(
                            ContextThemeWrapper(outerContext, R.style.AlertDialogCustom))
                            .setTitle("Required Permissions")
                            .setMessage(
                                outerContext.resources.getString(R.string.permissions_info_text)
                            )

                            .setPositiveButton("Ok") { _: DialogInterface, _: Int ->
                                // finish survey
                                Handler(Looper.getMainLooper()).postDelayed({
                                    // goto home screen
                                    val homeIntent = Intent(this.activity, HomeActivity::class.java)
                                    homeIntent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    startActivity(homeIntent)
                                }, 1200)
                            }.show()  // second (inner) dialog


                    }

                        // negative button of previous (outer) dialog
                    .setNegativeButton(R.string.cancel) { _, _ ->
                        // just close dialog
                    }
                    .create()
                confirmDialog.show()
            }

        }

        // update progress bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            this.activity?.findViewById<ProgressBar>(R.id.surveyProgressBar)
                ?.setProgress(90, true)
        else
            this.activity?.findViewById<ProgressBar>(R.id.surveyProgressBar)?.progress = 90

        return binding.root
    }


    /**
     * Sets the appropriate text for the assistant card according to the pressed button,
     *  and also manipulates button layout.
     *  @param assistant: which type of AssistantEnum belongs to pressed button
     */
    private fun onChoiceButtonClick(assistant: AssistantEnum) {
        when (assistant) {
            AssistantEnum.NOTIFICATION_ASSISTANT -> {
                binding.choiceNAButton.elevation = 0F
                binding.choiceRAButton.elevation = 32F
                binding.choiceAutoButton.elevation = 32F

                binding.assistantTitle.text = getString(R.string.na_header)
                binding.assistantDescr.text = getString(R.string.na_descr)
            }
            AssistantEnum.RECOMMENDATION_ASSISTANT -> {
                binding.choiceNAButton.elevation = 32F
                binding.choiceRAButton.elevation = 0F
                binding.choiceAutoButton.elevation = 32F

                binding.assistantTitle.text = getString(R.string.ra_header)
                binding.assistantDescr.text = getString(R.string.ra_descr)
            }
            AssistantEnum.AUTOMATIC_ASSISTANT -> {
                binding.choiceNAButton.elevation = 32F
                binding.choiceRAButton.elevation = 32F
                binding.choiceAutoButton.elevation = 0F

                binding.assistantTitle.text = getString(R.string.auto_header)
                binding.assistantDescr.text = getString(R.string.auto_descr)
            }
            else -> return
        }

        binding.confirmChoiceButton.elevation = 48F  // highlight once choice clicked

    }


    /**
     * Reads the needed information saved to shared preferences (main_sharedpref_name),
     *  and assembles the parameter map needed to perform the corresponding backend request.
     * @param qt: which table to insert into
     * @return a Map of pairs key: HTTP param name and value: param value
     */
    private fun getQueryParamsFromSp(qt: BackendInsertQueryEnum): Map<String, String> {

        val sharedPref = this.activity?.getSharedPreferences(
            resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        val paraMap = HashMap<String, String>()

        // read from sharedPref
        if (sharedPref != null) {
            with(
                sharedPref.edit()
            ) {
                // PID also needed for every table
                val currentUserPID: String? = sharedPref.getString("pid", "")
                if (currentUserPID != null) paraMap["pid"] = currentUserPID
                else paraMap["pid"] = "<empty>"

                when (qt) {  // different parameters for every query

                    BackendInsertQueryEnum.LIKERT -> {
                        val likertAnswers =
                            sharedPref.getString(LikertQuestionsFragment.LIKERT_ANSWERS_KEY, "")
                        val sb = StringBuilder()
                        if (likertAnswers != null && likertAnswers != "") {
                            val answerIndices = likertAnswers.toCharArray()
                            for (i in 0..answerIndices.size) {
                                if (i < answerIndices.size)
                                    sb.append(answerIndices[i])
                                if (i < answerIndices.size - 1) sb.append(",")  // separator
                            }
                            paraMap["lqs"] = sb.toString()
                        }
                    }

                    BackendInsertQueryEnum.DEMO -> {
                        // read values
                        val g = sharedPref.getString(PersonQuestionsFragment.GENDER_KEY, "")
                        val a = sharedPref.getInt(PersonQuestionsFragment.AGE_KEY, 0)
                        val c = sharedPref.getString(PersonQuestionsFragment.COUNTRY_KEY, "Germany")
                        val e = sharedPref.getString(PersonQuestionsFragment.EMPLOYMENT_KEY, "")
                        val i = sharedPref.getString(PersonQuestionsFragment.USAGE_KEY, "")
                        val iotqh = sharedPref.getBoolean(PersonQuestionsFragment.IOTQ_KEY, false)
                        val iotqhe = sharedPref.getString(PersonQuestionsFragment.IOTQE_KEY, "")
                        val ioto = sharedPref.getBoolean(IotQuestionsFragment.IOT_OWN_KEY, false)
                        val iotol = sharedPref.getString(IotQuestionsFragment.IOT_DEVICE_KEY, "")

                        // set in map appropriately
                        paraMap["g"] = g ?: ""
                        paraMap["a"] = a.toString()
                        paraMap["c"] = c ?: ""
                        paraMap["e"] = e ?: ""
                        paraMap["i"] = when (i) {
                            "daily" -> "1"
                            "every other day" -> "2"
                            "once a week" -> "3"
                            "less than once a week" -> "4"
                            else -> "0"
                        }
                        paraMap["ih"] = when (iotqh) {
                            true -> "true"
                            else -> "false"
                        }
                        paraMap["ihe"] = iotqhe ?: ""
                        paraMap["io"] = when (ioto) {
                            true -> "true"
                            else -> "false"
                        }
                        paraMap["iol"] = iotol ?: ""
                    }

                    BackendInsertQueryEnum.PASSIGN -> {
                        // build comma-separated array string for assignment question answers
                        val pqaStr = StringBuilder()
                        for (i in 0..8) {
                            if (i > 0) pqaStr.append(",")
                            pqaStr.append(sharedPref.getInt("pqa_$i", 0))
                        }

                        val o = sharedPref.getBoolean(PersonaResultFragment.OVERWRITE_KEY, false)
                        val f = sharedPref.getInt(PersonaResultFragment.PERSONA_FIT_KEY, 0)

                        paraMap["pqs"] = pqaStr.toString()
                        paraMap["o"] = when (o) {
                            true -> "true"
                            else -> "false"  // maybe never written
                        }
                        paraMap["f"] = f.toString()

                    }
                    BackendInsertQueryEnum.PERSONA -> {
                        val p = sharedPref.getInt(PersonaResultFragment.PERSONA_KEY, 0)
                        val a = sharedPref.getInt(ASSISTANT_KEY, 0)

                        paraMap["p"] = p.toString()
                        paraMap["pstr"] = when (p) {
                            1 -> PersonaTypeEnum.HMHK.name
                            2 -> PersonaTypeEnum.HMLK.name
                            3 -> PersonaTypeEnum.HMMK.name
                            4 -> PersonaTypeEnum.LMHK.name
                            5 -> PersonaTypeEnum.LMLK.name
                            6 -> PersonaTypeEnum.LMMK.name
                            7 -> PersonaTypeEnum.MMHK.name
                            8 -> PersonaTypeEnum.MMLK.name
                            9 -> PersonaTypeEnum.MMMK.name
                            else -> "????"
                        }
                        paraMap["a"] = a.toString()
                    }
                    else -> throw InvalidParameterException("Not part of BackendInsertQueryEnum!")
                }
                apply()
            }
        }

        return paraMap
    }


    companion object {

        // set to true if data in server should not be updated, std: false
        const val NO_SERVER_INFO_UPDATE = false

        const val ASSISTANT_KEY = "assistant_id"
    }
}