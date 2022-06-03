package com.philk7.ppaprojectapp.assistants;

import java.lang.System;

/**
 * A super class for all privacy assistants that implements the IPrivacyAssistant interface,
 * and already provides some implementations that can be shared by all assistants.
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/philk7/ppaprojectapp/assistants/AllAssistants;", "Lcom/philk7/ppaprojectapp/assistants/IPrivacyAssistant;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "singleDecisionProb", "", "doMultiRequest", "", "queryLocation", "", "setupSupplementWindowAlarms", "app_debug"})
public abstract class AllAssistants implements com.philk7.ppaprojectapp.assistants.IPrivacyAssistant {
    private final double singleDecisionProb = 0.5;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    /**
     * Shows a notification that asks the user to indicate the current location
     * (uses the main broadcast receiver so that it works even when app closed).
     */
    @java.lang.Override()
    public void queryLocation() {
    }
    
    /**
     * Decides randomly whether a multi decision request should be used instead of a single one.
     * @return do multi decision request?
     */
    public final boolean doMultiRequest() {
        return false;
    }
    
    /**
     * Builds the pending intents that are scheduled to check at certain times
     * (starts of some time windows) every day whether the user is "behind" in the daily
     * notification count, and may schedule some extra notifications (decisions) for their
     * respective time windows. The last pending intent is different, and schedules for the last
     * time window of a day the amount of notifications (decisions) that is missing to meet
     * the daily quota.
     * NOTE: Even when too many notifications are scheduled, the daily limit will not be
     * exceeded. The notifications are always built at the final scheduled time, and only
     * if the daily limit would not be exceeded.
     */
    @java.lang.Deprecated()
    public final void setupSupplementWindowAlarms() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    public AllAssistants(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
}