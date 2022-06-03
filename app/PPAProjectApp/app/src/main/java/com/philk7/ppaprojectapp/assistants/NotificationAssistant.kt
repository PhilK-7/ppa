package com.philk7.ppaprojectapp.assistants

import android.app.Notification
import android.content.Context
import com.philk7.ppaprojectapp.HomeActivity
import com.philk7.ppaprojectapp.enums.NotificationIdEnum
import com.philk7.ppaprojectapp.utils.DecisionRequestGenerator
import com.philk7.ppaprojectapp.utils.LocationProcessing
import com.philk7.ppaprojectapp.utils.NotificationHandler
import com.philk7.ppaprojectapp.utils.Supplementer
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * The Notification Assistant sends privacy notifications that lets the user freely decide,
 *  and does not make recommendations.
 */
open class NotificationAssistant(context: Context) : AllAssistants(context) {


    /**
     * Initializes the Notification Assistant. No weights are initialized,
     *  just the supplement time window alarm is set.
     */
    override fun initializePrivacyAssistant() {
        Supplementer.startServiceAlarm(context)
    }


    /**
     * Builds and shows a privacy decision notification in the style of Notification Assistant.
     * The number of decision requests is random, between 1-4.
     */
    override fun doPrivacyDecisionQuery() {

        val place = LocationProcessing.readLastPlace(context)
        val pid = HomeActivity.readUserPid(context)

        val pnotf: Notification = if (this.doMultiRequest()) {
            val randomAmt = Random.nextInt(2..4)
            val drs = DecisionRequestGenerator(context).generateMultipleDecisionRequests(randomAmt)
            NotificationHandler.buildMultiRequestDecisionNotificationAssistantNotification(
                context,
                NotificationIdEnum.NOW__PRIVACY_DECISION__NTF_ID.id, pid, place, drs
            )
        } else {
            val dr = DecisionRequestGenerator(context).generateDecisionRequest()
            NotificationHandler.buildNotificationAssistantNotification(
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
        const val usesWeights: Boolean = false
        const val weightBoost: Int = 1
    }

}