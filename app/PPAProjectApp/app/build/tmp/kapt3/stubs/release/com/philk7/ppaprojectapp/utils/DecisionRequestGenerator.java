package com.philk7.ppaprojectapp.utils;

import java.lang.System;

/**
 * Generates sets of decision requests, for any kind of privacy assistant.
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u000bJ\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\bR\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\b\u00a8\u0006\u0010"}, d2 = {"Lcom/philk7/ppaprojectapp/utils/DecisionRequestGenerator;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "dataOptions", "", "", "[Ljava/lang/String;", "deviceOptions", "generateDecisionRequest", "Lcom/philk7/ppaprojectapp/utils/DecisionRequest;", "generateMultipleDecisionRequests", "", "amount", "", "app_release"})
public final class DecisionRequestGenerator {
    private final java.lang.String[] deviceOptions = null;
    private final java.lang.String[] dataOptions = null;
    
    /**
     * Generates a random decision request, based on the available string arrays.
     * @return a random DecisionRequest object
     */
    @org.jetbrains.annotations.NotNull()
    public final com.philk7.ppaprojectapp.utils.DecisionRequest generateDecisionRequest() {
        return null;
    }
    
    /**
     * Generates multiple random decision requests.
     * @param amount: how many random request object
     * @return a list containing all randomly generated decision requests
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.philk7.ppaprojectapp.utils.DecisionRequest> generateMultipleDecisionRequests(int amount) {
        return null;
    }
    
    public DecisionRequestGenerator(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
}