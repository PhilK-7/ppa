package com.philk7.ppaprojectapp;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\b\u0010\n\u001a\u00020\bH\u0002J\b\u0010\u000b\u001a\u00020\bH\u0002J\b\u0010\f\u001a\u00020\bH\u0002J\u0018\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\b\u0010\u0011\u001a\u00020\bH\u0002J\u0014\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u0013H\u0002J$\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/philk7/ppaprojectapp/DevModeFragment;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "Lcom/philk7/ppaprojectapp/databinding/FragmentDevModeBinding;", "buildExampleNotification", "Landroid/app/Notification;", "displayENIn10Seconds", "", "displayEveryMinute", "displayExampleNotification", "displayMulti", "displayMultiRecommendation", "displayOnSpecificTime", "hour", "", "minute", "displayRecommendation", "extractNotificationInfo", "", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class DevModeFragment extends androidx.fragment.app.Fragment {
    private com.philk7.ppaprojectapp.databinding.FragmentDevModeBinding binding;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    /**
     * Extracts the information given by the spinners.
     * @return a Map that contains the necessary information to build a decision notification
     */
    private final java.util.Map<java.lang.String, java.lang.String> extractNotificationInfo() {
        return null;
    }
    
    /**
     * Builds an example privacy decision notification based on the fragment's spinners.
     * @return the built notification
     */
    private final android.app.Notification buildExampleNotification() {
        return null;
    }
    
    /**
     * Displays an example notification, including device and data type.
     *    A showcase for the real notification assistant.
     *    The artifical decision is inserted by the backend.
     * NOTE: Does not increase the daily counter.
     */
    private final void displayExampleNotification() {
    }
    
    /**
     * Displays an example notification with a delay of 10 seconds (or does decision automatically).
     * NOTE: Increases the daily counter!
     */
    private final void displayENIn10Seconds() {
    }
    
    /**
     * Displays an example notification about every minute.
     * NOTE: Increases the daily counter!
     */
    private final void displayEveryMinute() {
    }
    
    /**
     * Displays an example notification on a specific time (of the same day), or does it
     * autonomously if required.
     * NOTE: Increases the daily counter!
     * @param hour: the clock hour
     * @param minute: the clock minute
     */
    private final void displayOnSpecificTime(int hour, int minute) {
    }
    
    /**
     * Displays a multi request notification.
     * NOTE: Does not increase the daily counter.
     */
    private final void displayMulti() {
    }
    
    /**
     * Displays a recommendation privacy notification.
     * Note: Does not increase the daily counter.
     */
    private final void displayRecommendation() {
    }
    
    /**
     * Displays a multi request recommendation privacy notification.
     * NOTE: Does not increase the daily counter.
     */
    private final void displayMultiRecommendation() {
    }
    
    public DevModeFragment() {
        super();
    }
}