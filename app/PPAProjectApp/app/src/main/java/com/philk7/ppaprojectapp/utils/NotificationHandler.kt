package com.philk7.ppaprojectapp.utils

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import android.text.SpannableStringBuilder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.text.bold
import androidx.core.text.toSpannable
import androidx.navigation.NavDeepLinkBuilder
import com.philk7.ppaprojectapp.HomeActivity
import com.philk7.ppaprojectapp.LocationIndicationActivity
import com.philk7.ppaprojectapp.R
import com.philk7.ppaprojectapp.assistants.RecommendationAssistant
import com.philk7.ppaprojectapp.backend.DecisionBackendHandling
import com.philk7.ppaprojectapp.enums.NotificationChannelEnum
import com.philk7.ppaprojectapp.enums.NotificationIdEnum
import com.philk7.ppaprojectapp.enums.ReceiverIntentEnum
import java.util.*


object NotificationHandler {

    // NOTE: upon first opening HomeActivity (after survey), the notification channels are created


    /**
     * Builds a pending intent for a single privacy decision, as needed to build a
     *  single decision notification.
     * @param context: notification builder's context
     * @param nid: the correct notification ID
     * @param pid: the current user PID
     * @param place: the decision's place
     * @param device: the decision's device
     * @param data: the decision data type
     * @param allow: whether the decision is 'allow', instead of 'deny'
     * @return a pending intent targeted to allow/deny a decision request
     */
    private fun getSingleDecisionIntent(context: Context, nid: Int,
                                pid: String, place: String,
                                device: String, data: String, allow: Boolean): PendingIntent {

        // make the intent
        val intent = Intent(context, HomeActivity.Companion.DecisionRequestReceiver::class.java)
        if(allow) intent.action = ReceiverIntentEnum.ACTION_ALLOW.msg
        else intent.action = ReceiverIntentEnum.ACTION_DENY.msg

        // put extras for DecisionRequestReceiver
        intent.apply {
            putExtra("nid", nid)
            putExtra("pid", pid)
            putExtra("place", place)
            putExtra("device", device)
            putExtra("data", data)
        }

        return PendingIntent.getBroadcast(
            context, 0,
            intent, PendingIntent.FLAG_ONE_SHOT
        )
    }

    /**
     * Composes a notification for a single privacy decision,
     *  as delivered by Notification Assistant.
     * @param context: caller context (often HomeActivity)
     * @param nid: the correct notification ID
     * @param pid: the current user PID
     * @param place: the decision's place
     * @param device: the decision's device
     * @param data: the decision data type
     * @return the built notification
     */
    fun buildNotificationAssistantNotification(
        context: Context, nid: Int,
        pid: String, place: String,
        device: String, data: String
    ): Notification {
        // assemble notification text
        val ntb = SpannableStringBuilder()
        ntb.append("The place you are at contains \n")
        ntb.append("\u2022 ")
        ntb.bold { append(device) }
        ntb.append(" that records ")
        ntb.bold { append(data) }
        val notificationText = ntb.toSpannable()

        // make pending intents
        val allowIntent = getSingleDecisionIntent(context, nid, pid, place, device, data, true)
        val denyIntent = getSingleDecisionIntent(context, nid, pid, place, device, data, false)

        return context.let {
            NotificationCompat.Builder(it, NotificationChannelEnum.NORMAL_NOTF_CHANNEL_ID.name)
                .setSmallIcon(R.drawable.mask_icon)
                .setContentTitle("Notification Assistant")
                .setContentText(notificationText)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(notificationText)
                )
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(R.drawable.ic_allow, "Allow", allowIntent)
                .addAction(R.drawable.ic_deny, "Deny", denyIntent)
                .setOngoing(true)  // not cancellable
                .build()
        }

    }


    /**
     * Builds a notification for a single decision request, in the style of the
     *  Recommendation Assistant.
     * @param context: caller context
     * @param nid: the notification ID
     * @param pid: current user ID
     * @param place: place of the decision
     * @param device: decision device type
     * @param data: decision data type
     * @return the built notification
     */
    fun buildRecommendationAssistantNotification(
        context: Context, nid: Int,
        pid: String, place: String,
        device: String, data: String
    ): Notification {

        // get recommendation
        val recommended = if (RecommendationAssistant(context)
                .makeRequestRecommendation(DecisionRequest(device, data))
        ) "allow"
        else "deny"

        // save for case when is configured
        DecisionRequest().writeRequestCache(context, List(1) { DecisionRequest(device, data) })

        // compose the notification text
        val sb = SpannableStringBuilder()
            .append("The place you are at contains \n")
            .append("\u2022 ")
            .bold { append(device) }
            .append(" that records ")
            .bold { append(data) }
            .append(". \n\n")
            .append("Based on your past decisions, it is recommended to ")
            .bold { append(recommended) }
            .append(" this request.")
        val nt = sb.toSpannable()

        // make pending intents
        val allowIntent = getSingleDecisionIntent(context, nid, pid, place, device, data, true)
        val denyIntent = getSingleDecisionIntent(context, nid, pid, place, device, data, false)
        val configIntent = NavDeepLinkBuilder(context)
            .setComponentName(HomeActivity::class.java)
            .setGraph(R.navigation.home_navigation)
            .setDestination(R.id.decisionMakingFragment)
            .createPendingIntent()

        return NotificationCompat.Builder(
            context, NotificationChannelEnum.NORMAL_NOTF_CHANNEL_ID.name
        )
            .setSmallIcon(R.drawable.mask_icon)
            .setContentTitle("Recommendation Assistant")
            .setContentText(nt)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(nt)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(R.drawable.ic_allow, "Allow", allowIntent)
            .addAction(R.drawable.ic_deny, "Deny", denyIntent)
            .addAction(R.drawable.ic_decision, "Change...", configIntent)
            .setOngoing(true)  // not cancellable
            .build()
    }


    /**
     * Builds a pending intent for a multi decision request notification.
     * @param context: multi decision request notification builder's context
     * @param nid: the notification ID
     * @param pid: current user ID
     * @param place: supposed place of the decision requests
     * @param requests: the set of decision requests
     * @param batchAllow: whether the decision is 'allow all' instead of 'deny all'
     * @return a pending intent targeted to batch allow / deny decision requests
     */
    private fun getMultiDecisionIntent(context: Context, nid: Int,
                                       pid: String, place: String, requests: List<DecisionRequest>,
                                       batchAllow: Boolean): PendingIntent {

        // make intent, put extras and action
        val intent = Intent(context, HomeActivity.Companion.DecisionRequestReceiver::class.java)
        val deviceTypes = Array(requests.size){""}
        val dataTypes = Array(requests.size){""}
        for(i in requests.indices){
            deviceTypes[i] = requests[i].deviceType
            dataTypes[i] = requests[i].dataType
        }
        intent.apply {
            putExtra("nid", nid)
            putExtra("place", place)
            putExtra("pid", pid)
            putExtra("rd", deviceTypes)
            putExtra("rt", dataTypes)
        }
        if(batchAllow) intent.action = ReceiverIntentEnum.ACTION_ALL_ALLOW.msg
        else intent.action = ReceiverIntentEnum.ACTION_ALL_DENY.msg

        // pending intent
        return PendingIntent.getBroadcast(
            context, 0,
            intent, PendingIntent.FLAG_ONE_SHOT
        )
    }


    /**
     * Builds a notification for multiple privacy decisions at once,
     *  as delivered by the Notification Assistant.
     *  Upon clicking the notification, it opens up a panel where a single decision can be made
     *  for each distinct privacy request.
     * NOTE: Because of Android layout constraints, at most 7 decision requests should be passed.
     *  Otherwise, some might get cut off.
     * @param context: caller context
     * @param nid: the notification ID
     * @param pid: current user ID
     * @param place: the decisions' place
     * @param requests: the set of decision requests
     * @return a notification for a multi request, Notification Assistant action
     */
    fun buildMultiRequestDecisionNotificationAssistantNotification(
        context: Context, nid: Int, pid: String,
        place: String, requests: List<DecisionRequest>
    ): Notification {

        // compose notification text for all devices
        val ntb = SpannableStringBuilder()
        ntb.append("The place you are at contains \n")
        for (r in requests) {
            ntb.append("\u2022 ")  // bullet point
            ntb.bold { append(r.deviceType) }
            ntb.append(" that wants to access ")
            ntb.bold { append(r.dataType) }
            ntb.append("\n")
        }
        val nText = ntb.toSpannable()  // as spannable string

        // also write decision requests to special cache in sharedPref
        DecisionRequest().writeRequestCache(context, requests)


        // make pending intents
        val allowIntent = getMultiDecisionIntent(context, nid, pid, place, requests, true)
        val denyIntent = getMultiDecisionIntent(context, nid, pid, place, requests, false)
        val configIntent = NavDeepLinkBuilder(context)
            .setComponentName(HomeActivity::class.java)
            .setGraph(R.navigation.home_navigation)
            .setDestination(R.id.decisionMakingFragment)
            .createPendingIntent()

        // build notification
        return NotificationCompat.Builder(
            context,
            NotificationChannelEnum.NORMAL_NOTF_CHANNEL_ID.name
        )
            .setSmallIcon(R.drawable.mask_icon)
            .setContentTitle("Notification Assistant")
            .setContentText(nText)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(nText)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(R.drawable.ic_allow, "Allow all", allowIntent)
            .addAction(R.drawable.ic_deny, "Deny all", denyIntent)
            .addAction(R.drawable.ic_decision, "Configure...", configIntent)
            .setOngoing(true)  // cannot cancel
            .build()
    }


    /**
     * Builds a Recommendation Assistant notification with multiple decision requests.
     * NOTE: Because of notification layout constraints, it is recommended to pass at most
     *  4 decision requests. Otherwise, the recommendation could be cut off because the
     *  notification size would exceed maximum.
     * @param context: caller context
     * @param nid: the notification ID
     * @param pid: current user ID
     * @param place for the decision requests
     * @param requests: the set of decision requests
     * @return a multi-request, Recommendation Assistant style notification
     */
    fun buildMultiRequestDecisionRecommendationAssistantNotification(
        context: Context, nid: Int, pid: String,
        place: String, requests: List<DecisionRequest>
    ): Notification {

        // compose notification text for all devices
        val ntb = SpannableStringBuilder()
        ntb.append("The place you are at contains \n")
        for (r in requests) {
            ntb.append("\u2022 ")  // bullet point
            ntb.bold { append(r.deviceType) }
            ntb.append(" that wants to access ")
            ntb.bold { append(r.dataType) }
            ntb.append("\n")
        }
        ntb.append("\n")
        ntb.append("Based on your past decisions, it is recommended to ")
        val recommended = if (RecommendationAssistant(context)
                .makeRequestRecommendation(requests)
        ) "allow"
        else "deny"
        ntb.bold { append(recommended) }
        ntb.append(" the requests.")
        val nt = ntb.toSpannable()

        // also write decision requests to special cache in sharedPref
        DecisionRequest().writeRequestCache(context, requests)


        // make pending intents
        val allowIntent = getMultiDecisionIntent(context, nid, pid, place, requests, true)
        val denyIntent = getMultiDecisionIntent(context, nid, pid, place, requests, false)
        val configIntent = NavDeepLinkBuilder(context)
            .setComponentName(HomeActivity::class.java)
            .setGraph(R.navigation.home_navigation)
            .setDestination(R.id.decisionMakingFragment)
            .createPendingIntent()


        // build notification
        return NotificationCompat.Builder(
            context,
            NotificationChannelEnum.NORMAL_NOTF_CHANNEL_ID.name
        )
            .setSmallIcon(R.drawable.mask_icon)
            .setContentTitle("Recommendation Assistant")
            .setContentText(nt)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(nt)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(R.drawable.ic_allow, "Allow all", allowIntent)
            .addAction(R.drawable.ic_deny, "Deny all", denyIntent)
            .addAction(R.drawable.ic_decision, "Change...", configIntent)
            .setOngoing(true)  // cannot cancel
            .build()
    }


    /**
     * Builds a reminder notification that will be scheduled to show up, maybe repeatedly.
     * @param context: the caller context
     * @param pintent: the pending intent for the notification
     * @param title: the content title for the notification
     * @param text: the content text for the notification
     * @return a defined reminder notification
     */
    fun buildReminderNotification(
        context: Context, pintent: PendingIntent,
        title: String, text: String
    ): Notification {

        // build notification
        return NotificationCompat.Builder(
            context,
            NotificationChannelEnum.SPECIAL_NOTF_CHANNEL_ID.name
        )
            .setSmallIcon(R.drawable.mask_icon)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pintent)
            .setOngoing(true)
            .build()
    }


    /**
     * Checks whether a (privacy) notification is allowed to be sent, according to
     *  the daily notification maximum. Can reset notification day counter.
     * NOTE: Also works, and must be used with autonomous assistants. Then it instead
     *  checks whether the amount of privacy decision sets is within the daily limit.
     * @param context: caller context
     * @return whether a privacy notification / decision is still allowed today
     */
    fun checkWithinNotificationDayBudget(context: Context): Boolean {
        val ntfBudget = Supplementer.getUserDailyQuota(context)
        var canSendNtf = false
        val sharedPref = context.getSharedPreferences(
            context.resources
                .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )

        with(sharedPref.edit()) {
            val lastNtfDay = sharedPref.getString(NOTIFICATION_DAY_KEY, "1970-01-01") ?: ""
            var ntfsSentToday = sharedPref.getInt(NOTIFICATION_DAY_COUNT_KEY, 0)
            val today = DecisionBackendHandling.getCurrentUnixTimeAsTimestamp()

            if (today.startsWith(lastNtfDay)) {
                // not the first notification sent today
                if (ntfsSentToday < ntfBudget)  // within budget?
                    canSendNtf = true

            } else {  // first notification sent today
                ntfsSentToday = 0  // reset counter and update day
                this.putString(NOTIFICATION_DAY_KEY, today.slice(0..9))
                this.putInt(NOTIFICATION_DAY_COUNT_KEY, ntfsSentToday)
                if (ntfBudget != 0) canSendNtf = true
            }

            this.apply()
        }

        return canSendNtf
    }


    /**
     * Shows a notification immediately. Also updates sharedPref values.
     * @param context: a caller's context
     * @param notification: a built notification
     * @param nid: a notification ID, unique for any type of notification
     */
    fun showNotification(context: Context, notification: Notification, nid: Int) {

        // log to sharedPref
        val sharedPref = context.getSharedPreferences(
            context.resources.getString(
                R.string.main_sharedpref_name
            ), Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            var ntfCount = sharedPref.getInt(NOTIFICATION_COUNT_KEY, 0)
            this.putInt(NOTIFICATION_COUNT_KEY, ++ntfCount)

            // append this notification's representation to the history
            val ts = DecisionBackendHandling.getCurrentUnixTimeAsTimestamp()
            val ntfRepr = "Nr. $ntfCount \n@ $ts \nNID: $nid \n\n"
            val hist = sharedPref.getString(NOTIFICATION_HISTORY_KEY, "") ?: ""
            this.putString(NOTIFICATION_HISTORY_KEY, hist.plus(ntfRepr))

            this.apply()
        }

        // show
        with(NotificationManagerCompat.from(context)) {
            notify(nid, notification)
        }

    }


    /**
     * Broadcasts a notification to the MainReceiver.
     * @param context: caller context
     * @param notf: the given notification
     * @param nid: the notification ID to use
     */
    fun broadcastNotificationToMainReceiver(
        context: Context,
        notf: Notification,
        nid: NotificationIdEnum
    ) {

        val intent = Intent(context, HomeActivity.Companion.MainReceiver::class.java)
        intent.apply {
            putExtra("nid", nid.id)
            putExtra("notf", notf)
        }.action = ReceiverIntentEnum.ACTION_NEST.msg
        context.sendBroadcast(intent)
    }


    /**
     * Schedules a certain action, supported by MainReceiver (see also ACTION constants in
     *  companion object), for a specific time.
     * @param context: caller context
     * @param schedulePoint: schedule start / event point for action
     * @param action: an action supported by MainReceiver in HomeActivity
     * @param repeating: whether the scheduled action should be repeated periodically
     * @param repeatInterval: the repeating period, if repeating, in milliseconds
     * @param exact: whether to use an (about) exact, non-repeating alarm
     */
    fun scheduleActionAlarmToMainReceiver(
        context: Context, schedulePoint: Long, action: String,
        repeating: Boolean, repeatInterval: Long = AlarmManager.INTERVAL_FIFTEEN_MINUTES,
        exact: Boolean = false
    ) {

        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // build intent
        val bcIntent = Intent(
            context,
            HomeActivity.Companion.MainReceiver::class.java
        ).setAction(action)
        val pintent = PendingIntent.getBroadcast(
            context,
            1, bcIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        if(exact){
            // "exact" alarm, differentiate between versions

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            am.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                schedulePoint, pintent
            )
            else
                am.setExact(
                    AlarmManager.RTC_WAKEUP,
                    schedulePoint, pintent
                )
        }

        else if (repeating)  // periodic alarm
            am.setRepeating(
                AlarmManager.RTC_WAKEUP,
                schedulePoint, repeatInterval, pintent
            )
        else  // normal, one-time alarm
            am.set(AlarmManager.RTC_WAKEUP, schedulePoint, pintent)
    }


    /**
     * Computes the exact UNIX time some event should be schedule for.
     * @param delayInSeconds: in how many seconds the event should occur
     * @param useSystemClock: whether to use SystemClock instead of System
     * @return the UNIX time point of event occurrence in milliseconds
     */
    fun calculateSchedulingPoint(delayInSeconds: Int, useSystemClock: Boolean = false): Long {
        val base = if (useSystemClock) SystemClock.elapsedRealtime()
        else System.currentTimeMillis()

        return base + (delayInSeconds * 1000)
    }


    /**
     * Computes the time for some scheduled event according
     *  to a given clock time, based on Calendar.
     * @param hour: the clock hour
     * @param minute: the clock time
     * @return the UNIX time point of a click time for today
     */
    fun calculateSchedulingPointClock(hour: Int, minute: Int): Long {
        val cal = Calendar.getInstance()
        cal.timeInMillis = System.currentTimeMillis()
        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)
        cal.set(Calendar.SECOND, 0)

        return cal.timeInMillis
    }


    /**
     * Builds a notification for a location query.
     * @param context: caller context
     * @return a notification, that on clicking opens the Location Indicator
     */
    fun buildLocationQueryNotification(context: Context): Notification {

        // make pending intent
        val intent = Intent(context, LocationIndicationActivity::class.java)
        val pintent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Builder(
            context, NotificationChannelEnum.SPECIAL_NOTF_CHANNEL_ID.name
        )
            .setContentTitle("Your Location")
            .setContentText("Where are you right now?")
            .setSmallIcon(R.drawable.mask_icon)
            .setContentIntent(pintent)
            .setOngoing(true)
            .setAutoCancel(true)
            .build()
    }


    private const val NOTIFICATION_COUNT_KEY = "sent_notifications_count"
    private const val NOTIFICATION_HISTORY_KEY = "zzz_sent_notifications"
    private const val NOTIFICATION_DAY_KEY = "last_notification_sent_on_day"
    const val NOTIFICATION_DAY_COUNT_KEY = "today_notifications_sent"

}