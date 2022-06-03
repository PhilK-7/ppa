package com.philk7.ppaprojectapp.assistants

import android.content.Context
import com.philk7.ppaprojectapp.DecisionPanelFragment
import com.philk7.ppaprojectapp.FirstIDActivity.Companion.PID_KEY
import com.philk7.ppaprojectapp.R
import com.philk7.ppaprojectapp.backend.DecisionBackendHandling
import com.philk7.ppaprojectapp.utils.*
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * The Automatic Assistant works autonomously, and makes decisions on its own, without user input.
 */
class AutomaticAssistant(context: Context) : AllAssistants(context) {
    private var pid: String
    private var place: String

    init {
        // read user ID and last known (description) place
        val sharedPref = context.getSharedPreferences(
            context.resources
                .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            pid = sharedPref.getString(PID_KEY, "") ?: ""
            this.apply()
        }
        place = LocationProcessing.readLastPlace(context)
    }


    /**
     * Initializes the Automatic Assistant. The weights are initialized, supplement window alarm
     *  is scheduled, and also the Daily Overview reminder is activated.
     */
    override fun initializePrivacyAssistant() {
        WeightingHandler.initializeAllWeights(context)
        Supplementer.startServiceAlarm(context)

        // also enable daily overview reminder, with std time given in integers
        val h = context.resources.getInteger(R.integer.daily_overview_std_time_hour)
        val m = context.resources.getInteger(R.integer.daily_overview_std_time_minute)
        val sharedPref = context.getSharedPreferences(
            context.resources
                .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        // get time strings
        val hstr = if (h.toString().length < 2) "0$h" else h.toString()
        val mstr = if (m.toString().length < 2) "0$m" else m.toString()
        val timestr = "$hstr:$mstr"
        with(sharedPref.edit()) {
            this.putString(DecisionPanelFragment.DAILY_NOTF_TIME_KEY, timestr)
            this.apply()
        }
        DecisionPanelFragment.scheduleReminderNotification(context, h, m)  // activate reminder
    }


    /**
     * Makes a privacy decision, containing a random amount (1-4) of random requests,
     *  autonomously. This directly insert decisions and calls the backend.
     */
    override fun doPrivacyDecisionQuery() {

        if (this.doMultiRequest()) {
            val randomAmt = Random.nextInt(2..4)
            val drs = DecisionRequestGenerator(context).generateMultipleDecisionRequests(randomAmt)
            this.makeDecision(drs)
        } else {
            val dr = DecisionRequestGenerator(context).generateDecisionRequest()
            this.makeDecision(dr)
        }
    }


    /**
     * Makes a privacy decision for a single decision request (based on the weighting system).
     * @param req: a single decision request
     */
    private fun makeDecision(req: DecisionRequest) {

        // use the recommendation as decision
        val recommendation = WeightingHandler.getRequestRecommendation(context, req)
        DecisionBackendHandling().insertUserDecision(
            context, pid, place, req.deviceType, req.dataType, recommendation, false
        )
    }

    /**
     * Makes a batch of privacy decisions for a set of decision requests (using weighting).
     * @param reqs: the set of decision requests
     */
    private fun makeDecision(reqs: List<DecisionRequest>) {
        val recommendation = WeightingHandler.getBatchRecommendation(context, reqs)
        DecisionBackendHandling().executeBatchDecision(context, recommendation, pid, place,
            Array(reqs.size) { reqs[it].deviceType }, Array(reqs.size) { reqs[it].dataType })
    }

    companion object {
        const val usesWeights: Boolean = true
        const val weightBoost: Int = 5
    }

}