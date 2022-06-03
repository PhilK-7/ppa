package com.philk7.ppaprojectapp.assistants

import android.app.Notification
import android.content.Context
import com.philk7.ppaprojectapp.HomeActivity
import com.philk7.ppaprojectapp.enums.NotificationIdEnum
import com.philk7.ppaprojectapp.utils.*
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * The Recommendation Assistant works similarly to the Notification Assistant, but extends it.
 *  It tracks user behavior more intensively and makes recommendations based on it.
 */
class RecommendationAssistant(context: Context) : AllAssistants(context) {


    /**
     * Initializes the Recommendation Assistant. This means the weights are initialized,
     *  and also the supplement window alarm is scheduled.
     */
    override fun initializePrivacyAssistant() {
        WeightingHandler.initializeAllWeights(context)
        Supplementer.startServiceAlarm(context)
    }

    /**
     * Makes a decision recommendation for a single decision request.
     * @param req: the decision request
     * @return whether it is recommended to allow the decision request
     */
    fun makeRequestRecommendation(req: DecisionRequest): Boolean {
        return WeightingHandler.getRequestRecommendation(context, req)
    }


    /**
     * Makes a decision recommendation for a set of decision requests.
     * @param reqs: the decision requests
     * @return the overall decision recommendation for this set of decision requests
     */
    fun makeRequestRecommendation(reqs: List<DecisionRequest>): Boolean {
        return WeightingHandler.getBatchRecommendation(context, reqs)
    }


    /**
     * Builds and shows a privacy decision notification in the style of
     *  the Recommendation Assistant. The recommendation is extracted when building
     *  the notification.
     */
    override fun doPrivacyDecisionQuery() {
        val place = LocationProcessing.readLastPlace(context)
        val pid = HomeActivity.readUserPid(context)

        val pnotf: Notification = if (this.doMultiRequest()) {
            val randomAmt = Random.nextInt(2..4)
            val drs = DecisionRequestGenerator(context).generateMultipleDecisionRequests(randomAmt)
            NotificationHandler.buildMultiRequestDecisionRecommendationAssistantNotification(
                context,
                NotificationIdEnum.NOW__PRIVACY_DECISION__NTF_ID.id, pid, place, drs
            )
        } else {
            val dr = DecisionRequestGenerator(context).generateDecisionRequest()
            NotificationHandler.buildRecommendationAssistantNotification(
                context,
                NotificationIdEnum.NOW__PRIVACY_DECISION__NTF_ID.id,
                pid, place, dr.deviceType, dr.dataType
            )
        }

        NotificationHandler.showNotification(
            context, pnotf,
            NotificationIdEnum.NOW__PRIVACY_DECISION__NTF_ID.id
        )
    }


    companion object {
        const val usesWeights: Boolean = true
        const val weightBoost: Int = 3
    }
}