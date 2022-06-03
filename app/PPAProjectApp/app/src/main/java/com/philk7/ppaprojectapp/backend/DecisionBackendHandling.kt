package com.philk7.ppaprojectapp.backend

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.philk7.ppaprojectapp.DecisionPanelFragment
import com.philk7.ppaprojectapp.HomeActivity
import com.philk7.ppaprojectapp.R
import com.philk7.ppaprojectapp.assistants.AssistantEnum
import com.philk7.ppaprojectapp.assistants.AutomaticAssistant
import com.philk7.ppaprojectapp.assistants.NotificationAssistant
import com.philk7.ppaprojectapp.assistants.RecommendationAssistant
import com.philk7.ppaprojectapp.utils.DecisionRequest
import com.philk7.ppaprojectapp.utils.WeightingHandler
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class DecisionBackendHandling {

    /**
     * Retrieves the user decisions from the backend. Also shows info toasts.
     * @param context: the caller activity / fragment context
     * @param contextActivity: either the caller activity, or the caller fragment's activity base
     * @param pid: PID of the current user
     * @param filterToday: whether to remove decisions not made today
     */
    fun getUserDecisions(
        context: Context, contextActivity: Activity, pid: String,
        filterToday: Boolean
    ):
            MutableList<DecisionPanelFragment.Decision> {

        var decisionList = mutableListOf<DecisionPanelFragment.Decision>()

        val param = HashMap<String, String>()
        param["pid"] = pid

        val req = contextActivity.applicationContext?.let {
            BackendRequest(
                context.resources.getString(R.string.backend_address),
                "get_user_decisions",
                "GET",
                param,
                it
            )
        }


        var response: Any? = "norun"
        val runnable = Runnable {
            if (req != null) {
                response = req.performRequest()
            }
        }
        val sepThread = Thread(runnable)
        sepThread.start()
        sepThread.join(context.resources
            .getInteger(R.integer.server_request_timeout_std)
            .toLong())  // try to join thread within timeout

        if (response != "norun") {
            try {  // process response, get decisions, and display them in the table view
                val decisions: JSONArray
                val decisionData = JSONObject(response as String)
                decisions = decisionData["result"] as JSONArray

                for (i in 0 until decisions.length()) {  // parse decisions
                    val decision: JSONObject = decisions.getJSONObject(i)
                    val did: Int = decision["did"] as Int  // only for internal reference
                    val timestamp: String = decision["time"] as String
                    val place: String = decision["place"] as String
                    val device: String = decision["device"] as String
                    val data: String = decision["data"] as String
                    val av = decision["access"]
                    val access: Boolean = av == "true"
                    decisionList.add(
                        DecisionPanelFragment.Decision(
                            did,
                            timestamp,
                            place,
                            device,
                            data,
                            access
                        )
                    )
                }

                if (filterToday) {  // for DailyDecisionsFragment
                    // remove all decisions but today's

                    // get timestamp for today
                    val todayPrefix = SimpleDateFormat(
                        "yyyy-MM-dd", Locale.GERMANY
                    ).format(Date())
                    // filter to keep only today's decisions
                    decisionList = decisionList.filter {
                        it.timestamp.startsWith(todayPrefix, true)
                    }
                            as MutableList<DecisionPanelFragment.Decision>
                }


            } catch (e: JSONException) {
                e.printStackTrace()
                // happens in any case where the response string cannot be parsed as JSON
                // a possible cause is that instead an exception message was returned
                Toast.makeText(
                    context,
                    "Error: either the server is offline, or faulty server response.",
                    Toast.LENGTH_LONG
                ).show()
            }

        } else {  // timeout / no server response
            Toast.makeText(
                context,
                "Decisions could not be loaded!", Toast.LENGTH_LONG
            ).show()
        }

        return decisionList
    }


    /**
     * Deletes a specific decision, given its ID.
     * @param did: the decision's ID
     * @param outerContext: the caller's (outer) context
     */
    fun deleteUserDecision(did: Int, outerContext: Context): Boolean {
        val params1 = HashMap<String, String>()
        params1["d"] = did.toString()
        val deleteRequest = BackendRequest(
            outerContext.resources.getString(R.string.backend_address),
            "pdecision_delete",
            "DELETE",
            params1,
            outerContext
        )

        var resp1: Any? = "norun"
        val run1 = Runnable {
            resp1 = deleteRequest.performRequest()
        }
        val sepThread1 = Thread(run1)
        sepThread1.start()
        sepThread1.join(outerContext.resources
            .getInteger(R.integer.server_request_timeout_std)
            .toLong())

        return if (resp1 !is String || resp1 == "norun")
            false
        else {
            try {
                val result = JSONObject(resp1.toString())
                val msg: String = result["result"] as String
                msg.startsWith("SUCCESS")
            } catch (e: JSONException) {
                e.printStackTrace()
                false
            }
        }

    }

    /**
     * Gets the next higher decision ID (primary key of pdecision table in database).
     * @param outerContext: the caller's (outer) context
     * @return the next higher decision ID
     */
    private fun getNextDID(outerContext: Context): Int {
        val params2 = HashMap<String, String>()  // stays empty, just for parameter's sake
        val numRequest = BackendRequest(
            outerContext.resources.getString(R.string.backend_address),
            "get_next_higher_did",
            "GET",
            params2,
            outerContext
        )

        var resp2: Any? = "norun"
        val run2 = Runnable {
            resp2 = numRequest.performRequest()
        }
        val sepThread2 = Thread(run2)
        sepThread2.start()
        sepThread2.join(outerContext.resources
            .getInteger(R.integer.server_request_timeout_std)
            .toLong())

        return if (resp2 == "norun" || resp2 !is String)
            -1
        else {
            try {
                val result = JSONObject(resp2 as String)
                Integer.parseInt(result["result"].toString())
            } catch (e: JSONException) {
                e.printStackTrace()
                -1
            }

        }


    }


    /**
     * Inserts a user decision into table pdecision.
     *  As side effect, this also updates the respective weights, if necessary.
     * @param outerContext: the caller's (outer) context
     * @param pid: the current user ID
     * @param place: the decision place
     * @param device: the decision device
     * @param data: the decision data type
     * @param access: whether the decision allows or denies access to the data instance
     * @param weightBoosted: whether to use weight boosting (for decision panels)
     * @return whether the decision could be inserted
     */
    fun insertUserDecision(
        outerContext: Context, pid: String, place: String,
        device: String, data: String, access: Boolean, weightBoosted: Boolean
    ): Boolean {

        val useWeights: Boolean
        var weightBoost = 1

        // determine values for weight system
        when (HomeActivity.determineAssistant(outerContext)) {
            AssistantEnum.NOTIFICATION_ASSISTANT -> {
                useWeights = NotificationAssistant.usesWeights
                if (weightBoosted)
                    weightBoost = NotificationAssistant.weightBoost
            }
            AssistantEnum.RECOMMENDATION_ASSISTANT -> {
                useWeights = RecommendationAssistant.usesWeights
                if (weightBoosted)
                    weightBoost = RecommendationAssistant.weightBoost
            }
            AssistantEnum.AUTOMATIC_ASSISTANT -> {
                useWeights = AutomaticAssistant.usesWeights
                if (weightBoosted)
                    weightBoost = AutomaticAssistant.weightBoost
            }
            else -> {
                useWeights = false
                weightBoost = 0
            }
        }

        // update the weights, even when server interaction fails (to have an effect anyway)
        if (useWeights) {

            // if using weight boosting, update same weights more than once
            for (i in 1..weightBoost)
                WeightingHandler.updateWeightsForRequest(
                    outerContext, DecisionRequest(device, data), access
                )
        }


        // set the parameters
        val params3 = HashMap<String, String>()  // necessary parameters for inserting into table
        params3["pid"] = pid
        params3["t"] = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.GERMANY
        ).format(Date())
        params3["p"] = place
        params3["de"] = device
        params3["da"] = data
        params3["a"] = access.toString()

        // request DID
        val did = this.getNextDID(outerContext)
        params3["d"] = did.toString()

        val insertRequest = BackendRequest(
            outerContext.resources.getString(R.string.backend_address),
            "pdecision_insert",
            "POST",
            params3,
            outerContext
        )

        var resp3: Any? = "norun"
        val run3 = Runnable {
            resp3 = insertRequest.performRequest()
        }

        val sepThread3 = Thread(run3)
        sepThread3.start()
        sepThread3.join(outerContext.resources
            .getInteger(R.integer.server_request_timeout_std)
            .toLong())


        return if (resp3 == "norun" || resp3 !is String)
            false
        else {
            try {
                val result = JSONObject(resp3.toString())
                val msg = result["result"] as String
                msg.startsWith("SUCCESS")
            } catch (e: JSONException) {
                e.printStackTrace()
                false
            }
        }

    }


    /**
     * Inserts a batch of privacy decisions using the backend,
     *  usually following a multi decision notification.
     * @param outerContext: the caller / receiver context
     * @param allowed: whether the privacy objects get an access permission or not
     * @param pid: current user ID
     * @param place: the decision place
     * @param devices: the privacy objects' devices
     * @param types: the privacy object data types
     * @return whether all decision were successfully inserted
     */
    fun executeBatchDecision(
        outerContext: Context,
        allowed: Boolean,
        pid: String,
        place: String,
        devices: Array<String>,
        types: Array<String>
    ): Boolean {

        for (i in devices.indices) {
            val success = DecisionBackendHandling().insertUserDecision(
                outerContext,
                pid, place,
                devices[i], types[i],
                allowed, false  // no weight boosting for batch decisions
            )
            if (!success) return false  // something went wrong, cancel
        }

        return true  // all successfully inserted
    }


    companion object {

        /**
         * Computes a time stamp at the current point in time.
         * @param offset: an optional offset
         * @return a timestamp of format yyyy-MM-dd HH:mm:ss (for GMT+1 zone)
         */
        fun getCurrentUnixTimeAsTimestamp(offset: Long = 0): String {
            // current UNIX time in ms
            val ct = System.currentTimeMillis()
            val cal = Calendar.getInstance().apply {
                timeInMillis = ct + offset
            }

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.GERMANY)  // ts format

            return sdf.format(cal.time)
        }
    }

}