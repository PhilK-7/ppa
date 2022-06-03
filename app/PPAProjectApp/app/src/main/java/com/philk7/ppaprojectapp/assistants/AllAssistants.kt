package com.philk7.ppaprojectapp.assistants

import android.app.AlarmManager
import android.content.Context
import androidx.core.app.NotificationManagerCompat
import com.philk7.ppaprojectapp.enums.NotificationIdEnum
import com.philk7.ppaprojectapp.enums.ReceiverIntentEnum
import com.philk7.ppaprojectapp.utils.NotificationHandler
import com.philk7.ppaprojectapp.utils.Supplementer
import kotlin.random.Random


/**
 * A super class for all privacy assistants that implements the IPrivacyAssistant interface,
 *  and already provides some implementations that can be shared by all assistants.
 */
abstract class AllAssistants(val context: Context) : IPrivacyAssistant {

    private val singleDecisionProb = 0.5  // probability of single decision request vs. multi

    /**
     * Shows a notification that asks the user to indicate the current location
     *  (uses the main broadcast receiver so that it works even when app closed).
     */
    override fun queryLocation() {
        val locNotf = NotificationHandler.buildLocationQueryNotification(context)
        NotificationManagerCompat.from(context).cancel(NotificationIdEnum.LOCATION_QUERY__NTF_ID.id)
        NotificationHandler.broadcastNotificationToMainReceiver(
            context, locNotf, NotificationIdEnum.LOCATION_QUERY__NTF_ID
        )
    }


    /**
     * Decides randomly whether a multi decision request should be used instead of a single one.
     * @return do multi decision request?
     */
    fun doMultiRequest(): Boolean {
        val dnum = Random.nextInt(100)  // random num 0-99

        return dnum >= (100 * singleDecisionProb)  // multi if num geq 100 * singleDecisionProb
    }


    /**
     * Builds the pending intents that are scheduled to check at certain times
     *  (starts of some time windows) every day whether the user is "behind" in the daily
     *  notification count, and may schedule some extra notifications (decisions) for their
     *  respective time windows. The last pending intent is different, and schedules for the last
     *  time window of a day the amount of notifications (decisions) that is missing to meet
     *  the daily quota.
     * NOTE: Even when too many notifications are scheduled, the daily limit will not be
     *  exceeded. The notifications are always built at the final scheduled time, and only
     *  if the daily limit would not be exceeded.
     */
    @Deprecated("old system")
    fun setupSupplementWindowAlarms() {

        val firstWindowStart = Supplementer
            .translateTimeStringToUnixTime(Supplementer.TIME_WINDOW_START)
        val windowLength = Supplementer.WINDOW_SIZE
        val windowAmount = ((Supplementer
            .translateTimeStringToUnixTime(Supplementer.TIME_WINDOW_END)
                - firstWindowStart) / windowLength)
        val startPointsToCache = mutableListOf<Long>()

        for (i in 1..windowAmount - 2) {  // schedule the normal window pending intents

            // determine start point: either today, or tomorrow (if already in past)
            var startPoint = firstWindowStart + i * windowLength
            if (startPoint < System.currentTimeMillis()) startPoint += AlarmManager.INTERVAL_DAY
            startPointsToCache.add(startPoint)

            // schedule alarm for this window
            /* NOTE: When multiple pending intents should exist at the same time
            with basically the same purpose and the same intended action, the intent actions
            must be set to be somehow unique, or else pending intents scheduled later will
            overwrite seemingly identical ones. Therefore, for the time windows, and also
            the schedule points within those windows, make the actions somehow unique,
            and check for the right action prefix in the MainReceiver.
             */
            NotificationHandler.scheduleActionAlarmToMainReceiver(
                context, startPoint,
                ReceiverIntentEnum.ACTION_WINDOW_SCHEDULE.msg + "_$i",
                true, AlarmManager.INTERVAL_DAY
            )
        }

        // schedule daily final window pending intent
        val finalStartPoint = firstWindowStart + (windowAmount - 1) * windowLength
        startPointsToCache.add(finalStartPoint)
        Supplementer.writeWindowSchedulePoints(context, startPointsToCache, true)

        NotificationHandler.scheduleActionAlarmToMainReceiver(
            context, finalStartPoint,
            ReceiverIntentEnum.ACTION_LAST_WINDOW_SCHEDULE.msg,
            true, AlarmManager.INTERVAL_DAY
        )
    }


}