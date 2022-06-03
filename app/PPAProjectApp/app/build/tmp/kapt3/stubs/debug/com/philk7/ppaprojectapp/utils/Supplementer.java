package com.philk7.ppaprojectapp.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00112\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000eH\u0007J\u0016\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\u0010\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\u000e\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\tH\u0007J\u000e\u0010\u001f\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010 \u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u0004J&\u0010$\u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00112\u0006\u0010&\u001a\u00020\'H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/philk7/ppaprojectapp/utils/Supplementer;", "", "()V", "CURRENT_WINDOW_SCHEDULED_KEY", "", "CURRENT_WINDOW_SPS_CACHE_KEY", "SERVICE_ALARM_LAST_KEY", "SERVICE_ALARM_NEXT_KEY", "SUPPLEMENT_EXTRA_COUNT", "", "TIME_WINDOW_END", "TIME_WINDOW_START", "WINDOW_SCHEDULE_PADDING", "WINDOW_SIZE", "", "WINDOW_START_LIST_KEY", "computeSchedulePointsForWindow", "", "amount", "windowStart", "windowEnd", "computeVelocityDifference", "context", "Landroid/content/Context;", "dayTimeProgress", "", "determineLeftNotifications", "getNextSchedulePointFromStack", "getRepeatInterval", "getSupplementNotificationsAmount", "velDif", "getUserDailyQuota", "startServiceAlarm", "", "translateTimeStringToUnixTime", "timestr", "writeWindowSchedulePoints", "schedulePoints", "startPoints", "", "app_debug"})
public final class Supplementer {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TIME_WINDOW_START = "10:00";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TIME_WINDOW_END = "20:00";
    public static final int WINDOW_SCHEDULE_PADDING = 60000;
    private static final int SUPPLEMENT_EXTRA_COUNT = 1;
    public static final long WINDOW_SIZE = 9000000L;
    private static final java.lang.String WINDOW_START_LIST_KEY = "time_window_starts";
    private static final java.lang.String CURRENT_WINDOW_SCHEDULED_KEY = "current_window_schedule_points";
    private static final java.lang.String CURRENT_WINDOW_SPS_CACHE_KEY = "current_window_schedule_points_raw";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SERVICE_ALARM_LAST_KEY = "last_service_alarm_time";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SERVICE_ALARM_NEXT_KEY = "next_service_alarm_time";
    @org.jetbrains.annotations.NotNull()
    public static final com.philk7.ppaprojectapp.utils.Supplementer INSTANCE = null;
    
    /**
     * Takes a time string of form HH:mm and calculates its meaning in UNIX time, for today.
     * @param timestr: a string of form ab:cd where a,b,c,d are all decimal digits
     * @return the time strings translation to UNIX time in milliseconds
     */
    public final long translateTimeStringToUnixTime(@org.jetbrains.annotations.NotNull()
    java.lang.String timestr) {
        return 0L;
    }
    
    /**
     * Computes random scheduling points within a certain time window, at relatively even
     * intervals. Used for scheduling additional privacy decisions / notifications.
     * @param amount: how many scheduling points are needed
     * @param windowStart: start of the time window in Unix milliseconds
     * @param windowEnd: end of the time window in Unix milliseconds
     * @return a list containing sampled (restricted) random scheduling points in Unix ms time
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public final java.util.List<java.lang.Long> computeSchedulePointsForWindow(int amount, long windowStart, long windowEnd) {
        return null;
    }
    
    /**
     * Computes how many privacy notifications / sets of privacy notifications happened so far
     * today, according to the counter, in relation the absolute amount of privacy notifications
     * that should be shown per day (see R.integer.daily_notification_amount).
     * @param context: caller context
     * @param dayTimeProgress: today's time progress between 0-1 in relation to
     * FIRST_WINDOW_START and LAST_WINDOW_END, that means 0.5 for half-time
     * @return a positive number, if enough privacy notifications happened today up this point;
     * or a negative number, if not enough privacy decisions have been made yet today
     */
    public final int computeVelocityDifference(@org.jetbrains.annotations.NotNull()
    android.content.Context context, float dayTimeProgress) {
        return 0;
    }
    
    /**
     * Calculates the amount of extra privacy notifications that should be sent for a time window,
     * on the basis of a "velocity" difference. Also uses SUPPLEMENT_EXTRA_COUNT for this.
     * (When assistant is autonomous, this instead means how many sets of automatic privacy
     * decisions should be made within a time window).
     * @param velDif: absolute "velocity" difference based on counters
     * @return how many supplementary privacy notifications should be scheduled for a window
     */
    @java.lang.Deprecated()
    public final int getSupplementNotificationsAmount(int velDif) {
        return 0;
    }
    
    /**
     * Determines how many privacy notifications (or autonomous sets of privacy decisions) are
     * still left for today.
     * @param context: caller context
     * @return difference between daily limit and notification counter
     */
    @java.lang.Deprecated()
    public final int determineLeftNotifications(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return 0;
    }
    
    /**
     * Writes the starting / schedule points (clock times) for all windows / current window
     * to the respective sharedPref cache values.
     * @param context: caller context
     * @param schedulePoints: list of start / schedule points (Unix milliseconds)
     * @param startPoints: whether the schedule points are the start points of every time window,
     * instead of schedule points of the current time window
     */
    @java.lang.Deprecated()
    public final void writeWindowSchedulePoints(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> schedulePoints, boolean startPoints) {
    }
    
    /**
     * Fetches the next schedule point for a time window off the "stack",
     * which means the sharedPref cache value that contains them as Long representations.
     * This also pops the value off the stack.
     * @param context: caller context
     * @return the next schedule point for the current time window in Unix time;
     * or -1 if window's schedule point stack empty
     */
    @java.lang.Deprecated()
    public final long getNextSchedulePointFromStack(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return 0L;
    }
    
    /**
     * Starts the service alarm. This repeating, periodic alarm checks if the user is on "pace",
     * having seen enough privacy notifications so far today.
     * Can also be used for restarting the alarm.
     * @param context: caller context
     */
    public final void startServiceAlarm(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    /**
     * Computes the repeat interval for the scheduler service alarm,
     * based on the time window length, and the daily notification quota.
     * @param context: the caller context
     * @return the repeat interval length for the service alarm
     */
    public final long getRepeatInterval(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return 0L;
    }
    
    /**
     * Returns the daily privacy query quota of the current user,
     * according to the randomly assigned group, or the standard value in R.integers.
     * @param context: caller context
     * @return how many privacy queries should be done per day for the current user
     */
    public final int getUserDailyQuota(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return 0;
    }
    
    private Supplementer() {
        super();
    }
}