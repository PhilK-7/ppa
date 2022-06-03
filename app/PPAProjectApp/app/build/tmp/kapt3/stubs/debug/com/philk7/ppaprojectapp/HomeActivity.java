package com.philk7.ppaprojectapp;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001a2\u00020\u00012\u00020\u0002:\u0001\u001aB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\tH\u0002J\u001a\u0010\u000e\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\tH\u0016J\u0012\u0010\u0013\u001a\u00020\t2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\tH\u0014J\u0012\u0010\u0017\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\tH\u0014R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/philk7/ppaprojectapp/HomeActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroid/hardware/SensorEventListener;", "()V", "sensor", "Landroid/hardware/Sensor;", "sensorManager", "Landroid/hardware/SensorManager;", "checkMovePermission", "", "createNotificationChannel", "channel", "Lcom/philk7/ppaprojectapp/enums/NotificationChannelEnum;", "initializeAppState", "onAccuracyChanged", "p0", "p1", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onSensorChanged", "Landroid/hardware/SensorEvent;", "onStop", "Companion", "app_debug"})
public final class HomeActivity extends androidx.appcompat.app.AppCompatActivity implements android.hardware.SensorEventListener {
    private android.hardware.SensorManager sensorManager;
    private android.hardware.Sensor sensor;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INITIALIZED_KEY = "app_init_done";
    public static final int INTERVAL_WEEK = 604800;
    public static final boolean IGNORE_ALREADY_SETUP = false;
    public static final double LOCATION_QUERY_TRIGGER_PROB = 0.5;
    @org.jetbrains.annotations.NotNull()
    public static final com.philk7.ppaprojectapp.HomeActivity.Companion Companion = null;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onStop() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    /**
     * Checks whether the permission ACTIVITY_RECOGNITION is granted, and asks for it if not.
     * NOTE: This permission needs to be granted in order for the step counter to be usable
     * for all Android 10+.
     */
    private final void checkMovePermission() {
    }
    
    /**
     * Creates the notification channel needed for displaying notifications of the app.
     * @param channel: the notification channel, depends on the caller / type of notification
     */
    private final void createNotificationChannel(com.philk7.ppaprojectapp.enums.NotificationChannelEnum channel) {
    }
    
    /**
     * Initializes the app state when the home screen is opened for the first time.
     * The following things are done:
     *     - (re)set some sharedPref values
     *     - create the notification channels
     *     - initialize the privacy assistant (e.g. weights, supplementer)
     *     - schedule mid-term survey reminder
     *     - confirm initialization
     */
    private final void initializeAppState() {
    }
    
    /**
     * When the device's step counter sensor fires (not every step!), this method
     * writes the total step counter (e.g. since system reboot) to sharedPref,
     * and might trigger a location/privacy query.
     * @param p0: the fired event object
     */
    @java.lang.Override()
    public void onSensorChanged(@org.jetbrains.annotations.Nullable()
    android.hardware.SensorEvent p0) {
    }
    
    @java.lang.Override()
    public void onAccuracyChanged(@org.jetbrains.annotations.Nullable()
    android.hardware.Sensor p0, int p1) {
    }
    
    public HomeActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0002\u0016\u0017B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\fJ\u0016\u0010\u0012\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/philk7/ppaprojectapp/HomeActivity$Companion;", "", "()V", "IGNORE_ALREADY_SETUP", "", "INITIALIZED_KEY", "", "INTERVAL_WEEK", "", "LOCATION_QUERY_TRIGGER_PROB", "", "determineAssistant", "Lcom/philk7/ppaprojectapp/assistants/AssistantEnum;", "context", "Landroid/content/Context;", "overwriteAssistant", "", "assistant", "passHandleToAssistant", "action", "Lcom/philk7/ppaprojectapp/assistants/ActionEnum;", "readUserPid", "DecisionRequestReceiver", "MainReceiver", "app_debug"})
    public static final class Companion {
        
        /**
         * ESSENTIAL FUNCTION.
         * Passes generic actions to privacy assistants. This is based on the assistant hierarchy
         * pattern. Determines the current user's assistant, and calls the appropriate method.
         * @param context: caller context
         * @param action: which generic action (also see IPrivacyAssistant methods)
         */
        public final void passHandleToAssistant(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        com.philk7.ppaprojectapp.assistants.ActionEnum action) {
        }
        
        /**
         * Checks what the user's privacy assistant is.
         * @param context: caller context
         * @return the user's chosen assistant as AssistantEnum
         */
        @org.jetbrains.annotations.NotNull()
        public final com.philk7.ppaprojectapp.assistants.AssistantEnum determineAssistant(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        /**
         * Overwrites the user's assigned privacy assistant.
         * WARNING: This changes the app's behavior. Overwrite only for testing purposes.
         * @param context: caller context
         * @param assistant: the assistant to change to
         */
        public final void overwriteAssistant(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        com.philk7.ppaprojectapp.assistants.AssistantEnum assistant) {
        }
        
        /**
         * Reads the current user ID from shared preferences.
         * @param context: caller context
         * @return the user PID
         */
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String readUserPid(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private Companion() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016\u00a8\u0006\t"}, d2 = {"Lcom/philk7/ppaprojectapp/HomeActivity$Companion$MainReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "p0", "Landroid/content/Context;", "p1", "Landroid/content/Intent;", "app_debug"})
        public static final class MainReceiver extends android.content.BroadcastReceiver {
            
            @java.lang.Override()
            public void onReceive(@org.jetbrains.annotations.Nullable()
            android.content.Context p0, @org.jetbrains.annotations.Nullable()
            android.content.Intent p1) {
            }
            
            public MainReceiver() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\"\u00020\u0007H\u0002\u00a2\u0006\u0002\u0010\bJ!\u0010\t\u001a\u00020\n2\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\"\u00020\u0007H\u0002\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016\u00a8\u0006\u0011"}, d2 = {"Lcom/philk7/ppaprojectapp/HomeActivity$Companion$DecisionRequestReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "handleSystemState", "", "params", "", "", "([Ljava/lang/Object;)V", "interactWithBackend", "", "([Ljava/lang/Object;)Z", "onReceive", "p0", "Landroid/content/Context;", "p1", "Landroid/content/Intent;", "app_debug"})
        public static final class DecisionRequestReceiver extends android.content.BroadcastReceiver {
            
            @java.lang.Override()
            public void onReceive(@org.jetbrains.annotations.Nullable()
            android.content.Context p0, @org.jetbrains.annotations.Nullable()
            android.content.Intent p1) {
            }
            
            private final boolean interactWithBackend(java.lang.Object... params) {
                return false;
            }
            
            private final void handleSystemState(java.lang.Object... params) {
            }
            
            public DecisionRequestReceiver() {
                super();
            }
        }
    }
}