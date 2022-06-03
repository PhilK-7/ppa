package com.philk7.ppaprojectapp.utils;

import java.lang.System;

/**
 * @param deviceType: which kind of device
 * @param dataType: which kind of data the device wants to collect
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00000\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u001b\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00000\u0014H\u0002\u00a2\u0006\u0002\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0012H\u0002J\u001c\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00000\u000eR\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n\u00a8\u0006\u001b"}, d2 = {"Lcom/philk7/ppaprojectapp/utils/DecisionRequest;", "", "()V", "deviceType", "", "dataType", "(Ljava/lang/String;Ljava/lang/String;)V", "getDataType", "()Ljava/lang/String;", "setDataType", "(Ljava/lang/String;)V", "getDeviceType", "setDeviceType", "readRequestCache", "", "callerContext", "Landroid/content/Context;", "serializeRequests", "Lorg/json/JSONArray;", "requests", "", "([Lcom/philk7/ppaprojectapp/utils/DecisionRequest;)Lorg/json/JSONArray;", "serializedToString", "jarray", "writeRequestCache", "", "Companion", "app_release"})
public final class DecisionRequest {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String deviceType;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String dataType;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REQUEST_CACHE_KEY = "request_cache_object";
    @org.jetbrains.annotations.NotNull()
    public static final com.philk7.ppaprojectapp.utils.DecisionRequest.Companion Companion = null;
    
    /**
     * Converts an array of decision requests into a JSON array,
     * containing each request as JSON object.
     * @param requests: the decision requests
     * @return a JSON array, containing a JSON object for each request
     */
    private final org.json.JSONArray serializeRequests(com.philk7.ppaprojectapp.utils.DecisionRequest[] requests) {
        return null;
    }
    
    /**
     * Stringifies a JSON array (of decision requests).
     * @param jarray: a JSON array
     * @return the JSON array as string
     */
    private final java.lang.String serializedToString(org.json.JSONArray jarray) {
        return null;
    }
    
    /**
     * Writes some decision requests to the cached value.
     * @param callerContext: the caller (activity, fragment) context
     * @param requests: list containing Decision Request objects
     */
    public final void writeRequestCache(@org.jetbrains.annotations.NotNull()
    android.content.Context callerContext, @org.jetbrains.annotations.NotNull()
    java.util.List<com.philk7.ppaprojectapp.utils.DecisionRequest> requests) {
    }
    
    /**
     * Reads the decision objects last written to the cache.
     * Deserializes the saved string and parses JSON.
     * @param callerContext: the caller (activity, fragment) context
     * @return a list containing the decision requests encoded in the cached json string
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.philk7.ppaprojectapp.utils.DecisionRequest> readRequestCache(@org.jetbrains.annotations.NotNull()
    android.content.Context callerContext) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDeviceType() {
        return null;
    }
    
    public final void setDeviceType(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDataType() {
        return null;
    }
    
    public final void setDataType(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public DecisionRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String deviceType, @org.jetbrains.annotations.NotNull()
    java.lang.String dataType) {
        super();
    }
    
    public DecisionRequest() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/philk7/ppaprojectapp/utils/DecisionRequest$Companion;", "", "()V", "REQUEST_CACHE_KEY", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}