package com.philk7.ppaprojectapp.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bJ4\u0010\u0011\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016J4\u0010\u0018\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016J6\u0010\u0019\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004J6\u0010\u001c\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004J&\u0010\u001d\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00122\b\b\u0002\u0010%\u001a\u00020&J\u0016\u0010\'\u001a\u00020#2\u0006\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u0012J\u000e\u0010*\u001a\u00020&2\u0006\u0010\n\u001a\u00020\u000bJ>\u0010+\u001a\u00020\u001f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010,\u001a\u00020&H\u0002J@\u0010-\u001a\u00020\u001f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010.\u001a\u00020&H\u0002J:\u0010/\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00100\u001a\u00020#2\u0006\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020&2\b\b\u0002\u00103\u001a\u00020#2\b\b\u0002\u00104\u001a\u00020&J\u001e\u00105\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00106\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u00067"}, d2 = {"Lcom/philk7/ppaprojectapp/utils/NotificationHandler;", "", "()V", "NOTIFICATION_COUNT_KEY", "", "NOTIFICATION_DAY_COUNT_KEY", "NOTIFICATION_DAY_KEY", "NOTIFICATION_HISTORY_KEY", "broadcastNotificationToMainReceiver", "", "context", "Landroid/content/Context;", "notf", "Landroid/app/Notification;", "nid", "Lcom/philk7/ppaprojectapp/enums/NotificationIdEnum;", "buildLocationQueryNotification", "buildMultiRequestDecisionNotificationAssistantNotification", "", "pid", "place", "requests", "", "Lcom/philk7/ppaprojectapp/utils/DecisionRequest;", "buildMultiRequestDecisionRecommendationAssistantNotification", "buildNotificationAssistantNotification", "device", "data", "buildRecommendationAssistantNotification", "buildReminderNotification", "pintent", "Landroid/app/PendingIntent;", "title", "text", "calculateSchedulingPoint", "", "delayInSeconds", "useSystemClock", "", "calculateSchedulingPointClock", "hour", "minute", "checkWithinNotificationDayBudget", "getMultiDecisionIntent", "batchAllow", "getSingleDecisionIntent", "allow", "scheduleActionAlarmToMainReceiver", "schedulePoint", "action", "repeating", "repeatInterval", "exact", "showNotification", "notification", "app_debug"})
public final class NotificationHandler {
    private static final java.lang.String NOTIFICATION_COUNT_KEY = "sent_notifications_count";
    private static final java.lang.String NOTIFICATION_HISTORY_KEY = "zzz_sent_notifications";
    private static final java.lang.String NOTIFICATION_DAY_KEY = "last_notification_sent_on_day";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NOTIFICATION_DAY_COUNT_KEY = "today_notifications_sent";
    @org.jetbrains.annotations.NotNull()
    public static final com.philk7.ppaprojectapp.utils.NotificationHandler INSTANCE = null;
    
    /**
     * Builds a pending intent for a single privacy decision, as needed to build a
     * single decision notification.
     * @param context: notification builder's context
     * @param nid: the correct notification ID
     * @param pid: the current user PID
     * @param place: the decision's place
     * @param device: the decision's device
     * @param data: the decision data type
     * @param allow: whether the decision is 'allow', instead of 'deny'
     * @return a pending intent targeted to allow/deny a decision request
     */
    private final android.app.PendingIntent getSingleDecisionIntent(android.content.Context context, int nid, java.lang.String pid, java.lang.String place, java.lang.String device, java.lang.String data, boolean allow) {
        return null;
    }
    
    /**
     * Composes a notification for a single privacy decision,
     * as delivered by Notification Assistant.
     * @param context: caller context (often HomeActivity)
     * @param nid: the correct notification ID
     * @param pid: the current user PID
     * @param place: the decision's place
     * @param device: the decision's device
     * @param data: the decision data type
     * @return the built notification
     */
    @org.jetbrains.annotations.NotNull()
    public final android.app.Notification buildNotificationAssistantNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int nid, @org.jetbrains.annotations.NotNull()
    java.lang.String pid, @org.jetbrains.annotations.NotNull()
    java.lang.String place, @org.jetbrains.annotations.NotNull()
    java.lang.String device, @org.jetbrains.annotations.NotNull()
    java.lang.String data) {
        return null;
    }
    
    /**
     * Builds a notification for a single decision request, in the style of the
     * Recommendation Assistant.
     * @param context: caller context
     * @param nid: the notification ID
     * @param pid: current user ID
     * @param place: place of the decision
     * @param device: decision device type
     * @param data: decision data type
     * @return the built notification
     */
    @org.jetbrains.annotations.NotNull()
    public final android.app.Notification buildRecommendationAssistantNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int nid, @org.jetbrains.annotations.NotNull()
    java.lang.String pid, @org.jetbrains.annotations.NotNull()
    java.lang.String place, @org.jetbrains.annotations.NotNull()
    java.lang.String device, @org.jetbrains.annotations.NotNull()
    java.lang.String data) {
        return null;
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
    private final android.app.PendingIntent getMultiDecisionIntent(android.content.Context context, int nid, java.lang.String pid, java.lang.String place, java.util.List<com.philk7.ppaprojectapp.utils.DecisionRequest> requests, boolean batchAllow) {
        return null;
    }
    
    /**
     * Builds a notification for multiple privacy decisions at once,
     * as delivered by the Notification Assistant.
     * Upon clicking the notification, it opens up a panel where a single decision can be made
     * for each distinct privacy request.
     * NOTE: Because of Android layout constraints, at most 7 decision requests should be passed.
     * Otherwise, some might get cut off.
     * @param context: caller context
     * @param nid: the notification ID
     * @param pid: current user ID
     * @param place: the decisions' place
     * @param requests: the set of decision requests
     * @return a notification for a multi request, Notification Assistant action
     */
    @org.jetbrains.annotations.NotNull()
    public final android.app.Notification buildMultiRequestDecisionNotificationAssistantNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int nid, @org.jetbrains.annotations.NotNull()
    java.lang.String pid, @org.jetbrains.annotations.NotNull()
    java.lang.String place, @org.jetbrains.annotations.NotNull()
    java.util.List<com.philk7.ppaprojectapp.utils.DecisionRequest> requests) {
        return null;
    }
    
    /**
     * Builds a Recommendation Assistant notification with multiple decision requests.
     * NOTE: Because of notification layout constraints, it is recommended to pass at most
     * 4 decision requests. Otherwise, the recommendation could be cut off because the
     * notification size would exceed maximum.
     * @param context: caller context
     * @param nid: the notification ID
     * @param pid: current user ID
     * @param place for the decision requests
     * @param requests: the set of decision requests
     * @return a multi-request, Recommendation Assistant style notification
     */
    @org.jetbrains.annotations.NotNull()
    public final android.app.Notification buildMultiRequestDecisionRecommendationAssistantNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int nid, @org.jetbrains.annotations.NotNull()
    java.lang.String pid, @org.jetbrains.annotations.NotNull()
    java.lang.String place, @org.jetbrains.annotations.NotNull()
    java.util.List<com.philk7.ppaprojectapp.utils.DecisionRequest> requests) {
        return null;
    }
    
    /**
     * Builds a reminder notification that will be scheduled to show up, maybe repeatedly.
     * @param context: the caller context
     * @param pintent: the pending intent for the notification
     * @param title: the content title for the notification
     * @param text: the content text for the notification
     * @return a defined reminder notification
     */
    @org.jetbrains.annotations.NotNull()
    public final android.app.Notification buildReminderNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.app.PendingIntent pintent, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return null;
    }
    
    /**
     * Checks whether a (privacy) notification is allowed to be sent, according to
     * the daily notification maximum. Can reset notification day counter.
     * NOTE: Also works, and must be used with autonomous assistants. Then it instead
     * checks whether the amount of privacy decision sets is within the daily limit.
     * @param context: caller context
     * @return whether a privacy notification / decision is still allowed today
     */
    public final boolean checkWithinNotificationDayBudget(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    /**
     * Shows a notification immediately. Also updates sharedPref values.
     * @param context: a caller's context
     * @param notification: a built notification
     * @param nid: a notification ID, unique for any type of notification
     */
    public final void showNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.app.Notification notification, int nid) {
    }
    
    /**
     * Broadcasts a notification to the MainReceiver.
     * @param context: caller context
     * @param notf: the given notification
     * @param nid: the notification ID to use
     */
    public final void broadcastNotificationToMainReceiver(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.app.Notification notf, @org.jetbrains.annotations.NotNull()
    com.philk7.ppaprojectapp.enums.NotificationIdEnum nid) {
    }
    
    /**
     * Schedules a certain action, supported by MainReceiver (see also ACTION constants in
     * companion object), for a specific time.
     * @param context: caller context
     * @param schedulePoint: schedule start / event point for action
     * @param action: an action supported by MainReceiver in HomeActivity
     * @param repeating: whether the scheduled action should be repeated periodically
     * @param repeatInterval: the repeating period, if repeating, in milliseconds
     * @param exact: whether to use an (about) exact, non-repeating alarm
     */
    public final void scheduleActionAlarmToMainReceiver(@org.jetbrains.annotations.NotNull()
    android.content.Context context, long schedulePoint, @org.jetbrains.annotations.NotNull()
    java.lang.String action, boolean repeating, long repeatInterval, boolean exact) {
    }
    
    /**
     * Computes the exact UNIX time some event should be schedule for.
     * @param delayInSeconds: in how many seconds the event should occur
     * @param useSystemClock: whether to use SystemClock instead of System
     * @return the UNIX time point of event occurrence in milliseconds
     */
    public final long calculateSchedulingPoint(int delayInSeconds, boolean useSystemClock) {
        return 0L;
    }
    
    /**
     * Computes the time for some scheduled event according
     * to a given clock time, based on Calendar.
     * @param hour: the clock hour
     * @param minute: the clock time
     * @return the UNIX time point of a click time for today
     */
    public final long calculateSchedulingPointClock(int hour, int minute) {
        return 0L;
    }
    
    /**
     * Builds a notification for a location query.
     * @param context: caller context
     * @return a notification, that on clicking opens the Location Indicator
     */
    @org.jetbrains.annotations.NotNull()
    public final android.app.Notification buildLocationQueryNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    private NotificationHandler() {
        super();
    }
}