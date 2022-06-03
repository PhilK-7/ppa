package com.philk7.ppaprojectapp.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0010\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/philk7/ppaprojectapp/utils/LocationProcessing;", "", "()V", "HUNDRED_METERS_PLACE_CHANGED_THRESHOLD", "", "LOCATION_KEY", "", "PREV_LOCATION_KEY", "STEPS_CACHE_KEY", "STEPS_KEY", "STEPS_PLACE_CHANGED_THRESHOLD", "checkIndicatedLocationChanged", "", "context", "Landroid/content/Context;", "checkStepsLocationChanged", "readLastPlace", "writeLastPlace", "", "place", "app_release"})
public final class LocationProcessing {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String STEPS_KEY = "step_counter";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String STEPS_CACHE_KEY = "steps_location_last_changed";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOCATION_KEY = "last_location_indicated";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREV_LOCATION_KEY = "previous_location_indicated";
    private static final float HUNDRED_METERS_PLACE_CHANGED_THRESHOLD = 1.0F;
    public static final float STEPS_PLACE_CHANGED_THRESHOLD = 135.0F;
    @org.jetbrains.annotations.NotNull()
    public static final com.philk7.ppaprojectapp.utils.LocationProcessing INSTANCE = null;
    
    /**
     * Reads the last indicated place string.
     * @param context: caller context
     * @return the place string stored in sharedPref
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String readLastPlace(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    /**
     * Writes the currently indicated place string.
     * This also shifts the previous indicated location to the previous location cache.
     * @param context: caller context
     * @param place: place (see R.array.place_examples) just indicated (or custom)
     */
    public final void writeLastPlace(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String place) {
    }
    
    /**
     * Checks whether the user's (theoretical) location has changed,
     * according to given thresholds and the observed step counts.
     * Also updates the steps counter cache (steps count last location change)
     * if a location change was detected.
     * @param context: caller context
     * @return whether the location seems to have changed since the last steps cache update
     */
    public final boolean checkStepsLocationChanged(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    /**
     * Checks whether the user's indicated location has changed, based on the string values
     * in sharedPref. Might be called after asking to indicate the place.
     * @param context: caller context
     * @return whether two successively indicated locations are different
     */
    public final boolean checkIndicatedLocationChanged(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    private LocationProcessing() {
        super();
    }
}