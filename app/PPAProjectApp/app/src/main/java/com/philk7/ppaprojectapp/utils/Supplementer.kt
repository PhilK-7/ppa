package com.philk7.ppaprojectapp.utils

import android.content.Context
import com.philk7.ppaprojectapp.ConsentFormActivity
import com.philk7.ppaprojectapp.R
import com.philk7.ppaprojectapp.enums.ReceiverIntentEnum
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashSet
import kotlin.math.roundToInt
import kotlin.math.roundToLong
import kotlin.random.Random

object Supplementer {

    const val TIME_WINDOW_START = "10:00"
    const val TIME_WINDOW_END = "20:00"

    // not used anymore by current service system
    const val WINDOW_SCHEDULE_PADDING = 1 * 60000  // in minutes each at start/end
    private const val SUPPLEMENT_EXTRA_COUNT = 1  // more than necessary catch-up
    const val WINDOW_SIZE: Long = (3600000 * 2.5).toLong()
    private const val WINDOW_START_LIST_KEY = "time_window_starts"
    private const val CURRENT_WINDOW_SCHEDULED_KEY = "current_window_schedule_points"
    private const val CURRENT_WINDOW_SPS_CACHE_KEY = "current_window_schedule_points_raw"
    // block end


    const val SERVICE_ALARM_LAST_KEY = "last_service_alarm_time"
    const val SERVICE_ALARM_NEXT_KEY = "next_service_alarm_time"


    /**
     * Takes a time string of form HH:mm and calculates its meaning in UNIX time, for today.
     * @param timestr: a string of form ab:cd where a,b,c,d are all decimal digits
     * @return the time strings translation to UNIX time in milliseconds
     */
    fun translateTimeStringToUnixTime(timestr: String): Long {

        // split string by : and remove leading zeroes
        var h = timestr.split(":")[0]
        var m = timestr.split(":")[1]
        if (h.startsWith("0")) h = h[1].toString()
        if (m.startsWith("0")) m = m[1].toString()

        return NotificationHandler.calculateSchedulingPointClock(h.toInt(), m.toInt())
    }


    /**
     * Computes random scheduling points within a certain time window, at relatively even
     *  intervals. Used for scheduling additional privacy decisions / notifications.
     * @param amount: how many scheduling points are needed
     * @param windowStart: start of the time window in Unix milliseconds
     * @param windowEnd: end of the time window in Unix milliseconds
     * @return a list containing sampled (restricted) random scheduling points in Unix ms time
     */
    @Deprecated("old system")
    fun computeSchedulePointsForWindow(
        amount: Int,
        windowStart: Long,
        windowEnd: Long
    ): List<Long> {
        // compute start, end, length of actual usable window and equal parts length
        val intervalStart = windowStart + WINDOW_SCHEDULE_PADDING
        val intervalEnd = windowEnd - WINDOW_SCHEDULE_PADDING
        val intervalLength = intervalEnd - intervalStart
        val subWindowLength = (intervalLength.toFloat() / amount).roundToInt()

        val spList = mutableListOf<Long>()
        for (i in 0 until amount) {
            // generate random scheduling point within each sub-window
            val subWindowStart = intervalStart + i * subWindowLength + 1
            val subWindowSchedulePointRelative = Random.nextInt(subWindowLength)
            val subWindowSchedulePoint = subWindowStart + subWindowSchedulePointRelative
            spList.add(subWindowSchedulePoint)
        }

        return spList
    }


    /**
     * Computes how many privacy notifications / sets of privacy notifications happened so far
     *  today, according to the counter, in relation the absolute amount of privacy notifications
     *  that should be shown per day (see R.integer.daily_notification_amount).
     * @param context: caller context
     * @param dayTimeProgress: today's time progress between 0-1 in relation to
     *  FIRST_WINDOW_START and LAST_WINDOW_END, that means 0.5 for half-time
     * @return a positive number, if enough privacy notifications happened today up this point;
     *  or a negative number, if not enough privacy decisions have been made yet today
     */
    fun computeVelocityDifference(context: Context, dayTimeProgress: Float): Int {

        // get necessary data
        val sharedPref = context.getSharedPreferences(
            context.resources
                .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        val dailyLimit = getUserDailyQuota(context)
        val notfsSentTodaySofar: Int
        with(sharedPref.edit()) {
            notfsSentTodaySofar = sharedPref
                .getInt(NotificationHandler.NOTIFICATION_DAY_COUNT_KEY, 0)
            this.apply()
        }

        // how many should have been done yet, according to average - use this as check
        val desiredAvg = dayTimeProgress * dailyLimit

        return (notfsSentTodaySofar - desiredAvg).roundToInt() - 1
    }


    /**
     * Calculates the amount of extra privacy notifications that should be sent for a time window,
     *  on the basis of a "velocity" difference. Also uses SUPPLEMENT_EXTRA_COUNT for this.
     *  (When assistant is autonomous, this instead means how many sets of automatic privacy
     *  decisions should be made within a time window).
     * @param velDif: absolute "velocity" difference based on counters
     * @return how many supplementary privacy notifications should be scheduled for a window
     */
    @Deprecated("old system")
    fun getSupplementNotificationsAmount(velDif: Int): Int {

        return if (velDif >= 0) 0
        else (-velDif) + SUPPLEMENT_EXTRA_COUNT  // negative velocity -> supplement notifications
    }


    /**
     * Determines how many privacy notifications (or autonomous sets of privacy decisions) are
     *  still left for today.
     * @param context: caller context
     * @return difference between daily limit and notification counter
     */
    @Deprecated("old system")
    fun determineLeftNotifications(context: Context): Int {
        val sharedPref = context.getSharedPreferences(
            context.resources
                .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        val dailyLimit = getUserDailyQuota(context)
        val dayCounter: Int
        with(sharedPref.edit()) {
            dayCounter = sharedPref.getInt(NotificationHandler.NOTIFICATION_DAY_COUNT_KEY, 0)
            this.apply()
        }

        return dailyLimit - dayCounter
    }


    /**
     * Writes the starting / schedule points (clock times) for all windows / current window
     *  to the respective sharedPref cache values.
     * @param context: caller context
     * @param schedulePoints: list of start / schedule points (Unix milliseconds)
     * @param startPoints: whether the schedule points are the start points of every time window,
     *  instead of schedule points of the current time window
     */
    @Deprecated("old system")
    fun writeWindowSchedulePoints(
        context: Context, schedulePoints: List<Long>,
        startPoints: Boolean
    ) {
        val sharedPref = context.getSharedPreferences(
            context.resources
                .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            val sb = StringBuilder()
            val spSet = HashSet<String>()

            // process schedule points to string / timestamp formats
            for (sp in schedulePoints) {
                spSet.add(sp.toString())
                val cal = Calendar.getInstance().apply {
                    timeInMillis = sp
                }
                val sdf = SimpleDateFormat("HH:mm:ss", Locale.GERMANY)
                val clockTime = sdf.apply {

                }.format(cal.time)
                sb.append(clockTime)
                sb.append("\n")
            }


            // write schedule point representation(s) to correct sharedPref value
            if (startPoints)
                this.putString(WINDOW_START_LIST_KEY, sb.toString())
            else {
                this.putString(CURRENT_WINDOW_SCHEDULED_KEY, sb.toString())
                this.putStringSet(CURRENT_WINDOW_SPS_CACHE_KEY, spSet)
            }

            this.apply()
        }
    }


    /**
     * Fetches the next schedule point for a time window off the "stack",
     *  which means the sharedPref cache value that contains them as Long representations.
     *  This also pops the value off the stack.
     * @param context: caller context
     * @return the next schedule point for the current time window in Unix time;
     *  or -1 if window's schedule point stack empty
     */
    @Deprecated("old system")
    fun getNextSchedulePointFromStack(context: Context): Long {

        val sharedPref = context.getSharedPreferences(
            context.resources
                .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        val theSp: Long
        with(sharedPref.edit()) {
            val spCache = sharedPref.getStringSet(CURRENT_WINDOW_SPS_CACHE_KEY, HashSet())
            val spsList = spCache?.toList()?.sorted() ?: listOf()  // sort list

            if (spsList.isNotEmpty()) {  // stack non-empty -> pop value
                val nextSp = spsList[0]  // first value of sorted collection is next sp
                spCache?.remove(nextSp)
                this.putStringSet(CURRENT_WINDOW_SPS_CACHE_KEY, spCache)  // write back
                theSp = nextSp.toLong()
            } else
                theSp = -1


            this.apply()
        }

        return theSp
    }


    /**
     * Starts the service alarm. This repeating, periodic alarm checks if the user is on "pace",
     *  having seen enough privacy notifications so far today.
     *  Can also be used for restarting the alarm.
     * @param context: caller context
     */
    fun startServiceAlarm(context: Context) {
        /* The repeat interval is regular, according to amount of privacy notifications,
            in relation to the time window. A higher "sampling rate" is used for robustness.
         */
        val repeatInterval = getRepeatInterval(context)
        val now = System.currentTimeMillis()
        // start alarm at next planned regular trigger time from now
        val startPoint = now - (now % repeatInterval) + repeatInterval
        // schedule first service alarm (each next service alarm is scheduled successively)
        NotificationHandler.scheduleActionAlarmToMainReceiver(
            context, startPoint,
            ReceiverIntentEnum.ACTION_SCHEDULE_SERVICE.msg, false, exact = true
        )
    }


    /**
     * Computes the repeat interval for the scheduler service alarm,
     *  based on the time window length, and the daily notification quota.
     * @param context: the caller context
     * @return the repeat interval length for the service alarm
     */
    fun getRepeatInterval(context: Context): Long {
        val dailyQuota = getUserDailyQuota(context)
        val repeatInterval = ((translateTimeStringToUnixTime(TIME_WINDOW_END) -
                translateTimeStringToUnixTime(TIME_WINDOW_START)).toFloat() /
                dailyQuota).roundToLong()

        return repeatInterval / 2  // twice the sampling rate, for robustness
    }


    /**
     * Returns the daily privacy query quota of the current user,
     *  according to the randomly assigned group, or the standard value in R.integers.
     * @param context: caller context
     * @return how many privacy queries should be done per day for the current user
     */
    fun getUserDailyQuota(context: Context): Int {
        val sharedPref = context.getSharedPreferences(context.resources
            .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE)
        // take standard value from R.integers, if no random group assigned
        var quota = context.resources.getInteger(R.integer.daily_notification_amount_std)

        with(sharedPref.edit()){
            val assignedAmt = sharedPref.getInt(ConsentFormActivity
                .DAILY_NOTIFICATION_AMOUNT_RANDOM_KEY, -1)
            // use specific value, if user assigned to random group
            if(assignedAmt != -1) quota = assignedAmt
            this.apply()
        }

        return quota
    }
}