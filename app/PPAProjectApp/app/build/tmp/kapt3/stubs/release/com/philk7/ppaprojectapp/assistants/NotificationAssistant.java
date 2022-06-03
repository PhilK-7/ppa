package com.philk7.ppaprojectapp.assistants;

import java.lang.System;

/**
 * The Notification Assistant sends privacy notifications that lets the user freely decide,
 * and does not make recommendations.
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \b2\u00020\u0001:\u0001\bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/philk7/ppaprojectapp/assistants/NotificationAssistant;", "Lcom/philk7/ppaprojectapp/assistants/AllAssistants;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "doPrivacyDecisionQuery", "", "initializePrivacyAssistant", "Companion", "app_release"})
public class NotificationAssistant extends com.philk7.ppaprojectapp.assistants.AllAssistants {
    public static final boolean usesWeights = false;
    public static final int weightBoost = 1;
    @org.jetbrains.annotations.NotNull()
    public static final com.philk7.ppaprojectapp.assistants.NotificationAssistant.Companion Companion = null;
    
    /**
     * Initializes the Notification Assistant. No weights are initialized,
     * just the supplement time window alarm is set.
     */
    @java.lang.Override()
    public void initializePrivacyAssistant() {
    }
    
    /**
     * Builds and shows a privacy decision notification in the style of Notification Assistant.
     * The number of decision requests is random, between 1-4.
     */
    @java.lang.Override()
    public void doPrivacyDecisionQuery() {
    }
    
    public NotificationAssistant(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/philk7/ppaprojectapp/assistants/NotificationAssistant$Companion;", "", "()V", "usesWeights", "", "weightBoost", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}