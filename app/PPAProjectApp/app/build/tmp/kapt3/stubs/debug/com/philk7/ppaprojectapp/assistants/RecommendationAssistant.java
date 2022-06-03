package com.philk7.ppaprojectapp.assistants;

import java.lang.System;

/**
 * The Recommendation Assistant works similarly to the Notification Assistant, but extends it.
 * It tracks user behavior more intensively and makes recommendations based on it.
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0014\u0010\b\u001a\u00020\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a8\u0006\u000f"}, d2 = {"Lcom/philk7/ppaprojectapp/assistants/RecommendationAssistant;", "Lcom/philk7/ppaprojectapp/assistants/AllAssistants;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "doPrivacyDecisionQuery", "", "initializePrivacyAssistant", "makeRequestRecommendation", "", "req", "Lcom/philk7/ppaprojectapp/utils/DecisionRequest;", "reqs", "", "Companion", "app_debug"})
public final class RecommendationAssistant extends com.philk7.ppaprojectapp.assistants.AllAssistants {
    public static final boolean usesWeights = true;
    public static final int weightBoost = 3;
    @org.jetbrains.annotations.NotNull()
    public static final com.philk7.ppaprojectapp.assistants.RecommendationAssistant.Companion Companion = null;
    
    /**
     * Initializes the Recommendation Assistant. This means the weights are initialized,
     * and also the supplement window alarm is scheduled.
     */
    @java.lang.Override()
    public void initializePrivacyAssistant() {
    }
    
    /**
     * Makes a decision recommendation for a single decision request.
     * @param req: the decision request
     * @return whether it is recommended to allow the decision request
     */
    public final boolean makeRequestRecommendation(@org.jetbrains.annotations.NotNull()
    com.philk7.ppaprojectapp.utils.DecisionRequest req) {
        return false;
    }
    
    /**
     * Makes a decision recommendation for a set of decision requests.
     * @param reqs: the decision requests
     * @return the overall decision recommendation for this set of decision requests
     */
    public final boolean makeRequestRecommendation(@org.jetbrains.annotations.NotNull()
    java.util.List<com.philk7.ppaprojectapp.utils.DecisionRequest> reqs) {
        return false;
    }
    
    /**
     * Builds and shows a privacy decision notification in the style of
     * the Recommendation Assistant. The recommendation is extracted when building
     * the notification.
     */
    @java.lang.Override()
    public void doPrivacyDecisionQuery() {
    }
    
    public RecommendationAssistant(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/philk7/ppaprojectapp/assistants/RecommendationAssistant$Companion;", "", "()V", "usesWeights", "", "weightBoost", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}