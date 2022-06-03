package com.philk7.ppaprojectapp.assistants;

import java.lang.System;

/**
 * The Automatic Assistant works autonomously, and makes decisions on its own, without user input.
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0016\u0010\u000b\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/philk7/ppaprojectapp/assistants/AutomaticAssistant;", "Lcom/philk7/ppaprojectapp/assistants/AllAssistants;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "pid", "", "place", "doPrivacyDecisionQuery", "", "initializePrivacyAssistant", "makeDecision", "req", "Lcom/philk7/ppaprojectapp/utils/DecisionRequest;", "reqs", "", "Companion", "app_debug"})
public final class AutomaticAssistant extends com.philk7.ppaprojectapp.assistants.AllAssistants {
    private java.lang.String pid;
    private java.lang.String place;
    public static final boolean usesWeights = true;
    public static final int weightBoost = 5;
    @org.jetbrains.annotations.NotNull()
    public static final com.philk7.ppaprojectapp.assistants.AutomaticAssistant.Companion Companion = null;
    
    /**
     * Initializes the Automatic Assistant. The weights are initialized, supplement window alarm
     * is scheduled, and also the Daily Overview reminder is activated.
     */
    @java.lang.Override()
    public void initializePrivacyAssistant() {
    }
    
    /**
     * Makes a privacy decision, containing a random amount (1-4) of random requests,
     * autonomously. This directly insert decisions and calls the backend.
     */
    @java.lang.Override()
    public void doPrivacyDecisionQuery() {
    }
    
    /**
     * Makes a privacy decision for a single decision request (based on the weighting system).
     * @param req: a single decision request
     */
    private final void makeDecision(com.philk7.ppaprojectapp.utils.DecisionRequest req) {
    }
    
    /**
     * Makes a batch of privacy decisions for a set of decision requests (using weighting).
     * @param reqs: the set of decision requests
     */
    private final void makeDecision(java.util.List<com.philk7.ppaprojectapp.utils.DecisionRequest> reqs) {
    }
    
    public AutomaticAssistant(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/philk7/ppaprojectapp/assistants/AutomaticAssistant$Companion;", "", "()V", "usesWeights", "", "weightBoost", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}