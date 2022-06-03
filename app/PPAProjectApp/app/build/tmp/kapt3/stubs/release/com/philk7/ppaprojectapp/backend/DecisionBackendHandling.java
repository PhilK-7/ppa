package com.philk7.ppaprojectapp.backend;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJG\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u000f\u00a2\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J,\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0004J>\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004\u00a8\u0006 "}, d2 = {"Lcom/philk7/ppaprojectapp/backend/DecisionBackendHandling;", "", "()V", "deleteUserDecision", "", "did", "", "outerContext", "Landroid/content/Context;", "executeBatchDecision", "allowed", "pid", "", "place", "devices", "", "types", "(Landroid/content/Context;ZLjava/lang/String;Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)Z", "getNextDID", "getUserDecisions", "", "Lcom/philk7/ppaprojectapp/DecisionPanelFragment$Decision;", "context", "contextActivity", "Landroid/app/Activity;", "filterToday", "insertUserDecision", "device", "data", "access", "weightBoosted", "Companion", "app_release"})
public final class DecisionBackendHandling {
    @org.jetbrains.annotations.NotNull()
    public static final com.philk7.ppaprojectapp.backend.DecisionBackendHandling.Companion Companion = null;
    
    /**
     * Retrieves the user decisions from the backend. Also shows info toasts.
     * @param context: the caller activity / fragment context
     * @param contextActivity: either the caller activity, or the caller fragment's activity base
     * @param pid: PID of the current user
     * @param filterToday: whether to remove decisions not made today
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.philk7.ppaprojectapp.DecisionPanelFragment.Decision> getUserDecisions(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.app.Activity contextActivity, @org.jetbrains.annotations.NotNull()
    java.lang.String pid, boolean filterToday) {
        return null;
    }
    
    /**
     * Deletes a specific decision, given its ID.
     * @param did: the decision's ID
     * @param outerContext: the caller's (outer) context
     */
    public final boolean deleteUserDecision(int did, @org.jetbrains.annotations.NotNull()
    android.content.Context outerContext) {
        return false;
    }
    
    /**
     * Gets the next higher decision ID (primary key of pdecision table in database).
     * @param outerContext: the caller's (outer) context
     * @return the next higher decision ID
     */
    private final int getNextDID(android.content.Context outerContext) {
        return 0;
    }
    
    /**
     * Inserts a user decision into table pdecision.
     * As side effect, this also updates the respective weights, if necessary.
     * @param outerContext: the caller's (outer) context
     * @param pid: the current user ID
     * @param place: the decision place
     * @param device: the decision device
     * @param data: the decision data type
     * @param access: whether the decision allows or denies access to the data instance
     * @param weightBoosted: whether to use weight boosting (for decision panels)
     * @return whether the decision could be inserted
     */
    public final boolean insertUserDecision(@org.jetbrains.annotations.NotNull()
    android.content.Context outerContext, @org.jetbrains.annotations.NotNull()
    java.lang.String pid, @org.jetbrains.annotations.NotNull()
    java.lang.String place, @org.jetbrains.annotations.NotNull()
    java.lang.String device, @org.jetbrains.annotations.NotNull()
    java.lang.String data, boolean access, boolean weightBoosted) {
        return false;
    }
    
    /**
     * Inserts a batch of privacy decisions using the backend,
     * usually following a multi decision notification.
     * @param outerContext: the caller / receiver context
     * @param allowed: whether the privacy objects get an access permission or not
     * @param pid: current user ID
     * @param place: the decision place
     * @param devices: the privacy objects' devices
     * @param types: the privacy object data types
     * @return whether all decision were successfully inserted
     */
    public final boolean executeBatchDecision(@org.jetbrains.annotations.NotNull()
    android.content.Context outerContext, boolean allowed, @org.jetbrains.annotations.NotNull()
    java.lang.String pid, @org.jetbrains.annotations.NotNull()
    java.lang.String place, @org.jetbrains.annotations.NotNull()
    java.lang.String[] devices, @org.jetbrains.annotations.NotNull()
    java.lang.String[] types) {
        return false;
    }
    
    public DecisionBackendHandling() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/philk7/ppaprojectapp/backend/DecisionBackendHandling$Companion;", "", "()V", "getCurrentUnixTimeAsTimestamp", "", "offset", "", "app_release"})
    public static final class Companion {
        
        /**
         * Computes a time stamp at the current point in time.
         * @param offset: an optional offset
         * @return a timestamp of format yyyy-MM-dd HH:mm:ss (for GMT+1 zone)
         */
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getCurrentUnixTimeAsTimestamp(long offset) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}