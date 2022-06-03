package com.philk7.ppaprojectapp;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/philk7/ppaprojectapp/PersonQuestionsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "Lcom/philk7/ppaprojectapp/databinding/FragmentPersonQuestionsBinding;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "saveDemoInfo", "", "validateForm", "", "Companion", "app_release"})
public final class PersonQuestionsFragment extends androidx.fragment.app.Fragment {
    private com.philk7.ppaprojectapp.databinding.FragmentPersonQuestionsBinding binding;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GENDER_KEY = "gender";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String AGE_KEY = "age";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String COUNTRY_KEY = "country";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EMPLOYMENT_KEY = "empl";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USAGE_KEY = "usage";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String IOTQ_KEY = "iot_heard";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String IOTQE_KEY = "iot_heard_how";
    public static final boolean IGNORE_INFO_UPDATE = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.philk7.ppaprojectapp.PersonQuestionsFragment.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    /**
     * Validates the person questions form by checking that all needed answers are given.
     * @return true if everything (needed) is answered, else false
     */
    private final boolean validateForm() {
        return false;
    }
    
    /**
     * Saves all information of the complete, valid person questions formular to sharedPref.
     */
    private final void saveDemoInfo() {
    }
    
    public PersonQuestionsFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/philk7/ppaprojectapp/PersonQuestionsFragment$Companion;", "", "()V", "AGE_KEY", "", "COUNTRY_KEY", "EMPLOYMENT_KEY", "GENDER_KEY", "IGNORE_INFO_UPDATE", "", "IOTQE_KEY", "IOTQ_KEY", "USAGE_KEY", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}