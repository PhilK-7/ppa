package com.philk7.ppaprojectapp.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\u0018\u0010\u000f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u001c\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u0014J\u0016\u0010\u0015\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J \u0010\u0019\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J(\u0010\u001c\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\fH\u0002J\u001e\u0010\u001f\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/philk7/ppaprojectapp/utils/WeightingHandler;", "", "()V", "DATA_WEIGHTS_CACHE_KEY", "", "DEVICE_WEIGHTS_CACHE_KEY", "RECOMMENDATION_THRESHOLD", "", "WEIGHT_STEP_SIZE", "computeInitialWeights", "Lorg/json/JSONObject;", "forData", "", "context", "Landroid/content/Context;", "getAverageForDecisionRequest", "req", "Lcom/philk7/ppaprojectapp/utils/DecisionRequest;", "getBatchRecommendation", "reqs", "", "getRequestRecommendation", "initializeAllWeights", "", "initializeWeightCache", "readWeight", "fromData", "key", "updateWeight", "inData", "access", "updateWeightsForRequest", "app_debug"})
public final class WeightingHandler {
    private static final java.lang.String DEVICE_WEIGHTS_CACHE_KEY = "device_weights_cache";
    private static final java.lang.String DATA_WEIGHTS_CACHE_KEY = "data_weights_cache";
    private static final double WEIGHT_STEP_SIZE = 0.0625;
    private static final double RECOMMENDATION_THRESHOLD = 0.5;
    @org.jetbrains.annotations.NotNull()
    public static final com.philk7.ppaprojectapp.utils.WeightingHandler INSTANCE = null;
    
    /**
     * Computes the initial weights for a weights cache, depending on which one.
     * For device weights, all weights are initialized equal.
     * For data weights, the weights are chosen specifically and distinct.
     * @param forData: whether the weights for data types are calculated or not
     * @param context: caller context, only needed when computing device type weights
     */
    private final org.json.JSONObject computeInitialWeights(boolean forData, android.content.Context context) {
        return null;
    }
    
    /**
     * Initializes a weight cache.
     * @param forData: whether to initialize the data weights cache (instead of device)
     * @param context: caller context, necessary for writing to sharedPref
     */
    private final void initializeWeightCache(boolean forData, android.content.Context context) {
    }
    
    /**
     * Initializes all (both) weight caches using the initial weights.
     * @param context: caller context
     */
    public final void initializeAllWeights(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    /**
     * Reads a weight from a weight cache.
     * @param context: caller context
     * @param fromData: whether to read from the data weights cache (instead device)
     * @param key: the key the weight is mapped to
     * @return the weight associated with the key in the accessed cache
     */
    private final double readWeight(android.content.Context context, boolean fromData, java.lang.String key) {
        return 0.0;
    }
    
    /**
     * Updates a specific weight in one of the caches, depending on a privacy decision.
     * @param context: caller context
     * @param inData: whether the weight is in data cache (instead of device)
     * @param access: whether the initiating privacy decision allowed access for a request
     */
    private final void updateWeight(android.content.Context context, boolean inData, java.lang.String key, boolean access) {
    }
    
    /**
     * Calculates the arithmetic mean value for a decision request. That means, the weights
     * for its device and data are averaged.
     * Can be used for making recommendations on privacy decisions.
     * @param context: caller context
     * @param req: the decision request object waiting to get a privacy decision answer
     */
    private final double getAverageForDecisionRequest(android.content.Context context, com.philk7.ppaprojectapp.utils.DecisionRequest req) {
        return 0.0;
    }
    
    /**
     * Updates the respective weights for a decision request, depending on the privacy decision.
     * @param context: caller context
     * @param req: the answered decision request that requires weight updating
     * @param access: whether the decision request was answered with "allow"
     */
    public final void updateWeightsForRequest(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.philk7.ppaprojectapp.utils.DecisionRequest req, boolean access) {
    }
    
    /**
     * Makes a decision request recommendation, based on weights and the threshold.
     * @param context: caller context
     * @param req: decision request to make a recommendation for
     * @return whether to recommend access for the decision request
     */
    public final boolean getRequestRecommendation(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.philk7.ppaprojectapp.utils.DecisionRequest req) {
        return false;
    }
    
    /**
     * Makes a recommendation for a batch of decisions, based on some kind of majority vote.
     *
     * This is done when a user can accept or deny all requests from a notification.
     * Instead of batch deciding, a user could also decide for each single case.
     * @param context: caller context
     * @param reqs: list of decision requests to make a batch recommendation for
     * @return whether to recommend access for all requests (or deny all)
     */
    public final boolean getBatchRecommendation(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<com.philk7.ppaprojectapp.utils.DecisionRequest> reqs) {
        return false;
    }
    
    private WeightingHandler() {
        super();
    }
}