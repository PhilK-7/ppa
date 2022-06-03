package com.philk7.ppaprojectapp;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 (2\u00020\u0001:\u0001(B\u0005\u00a2\u0006\u0002\u0010\u0002J!\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00052\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u0004\u00a2\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002\u00a2\u0006\u0002\u0010\u001aJ)\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002\u00a2\u0006\u0002\u0010\u001fJ$\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0004X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/philk7/ppaprojectapp/PersonaResultFragment;", "Landroidx/fragment/app/Fragment;", "()V", "HMHKVector", "", "", "[Ljava/lang/Integer;", "HMLKVector", "HMMKVector", "LMHKVector", "LMLKVector", "LMMKVector", "MMHKVector", "MMLKVector", "MMMKVector", "binding", "Lcom/philk7/ppaprojectapp/databinding/FragmentPersonaResultBinding;", "personaDescriptionSents", "", "[Ljava/lang/String;", "resultingPersonaIndex", "assemblePersonaDescription", "personaIndex", "descrSents", "(I[Ljava/lang/String;)Ljava/lang/String;", "buildPersonaVector", "()[Ljava/lang/Integer;", "calculateEuclideanDistance", "", "v1", "v2", "([Ljava/lang/Integer;[Ljava/lang/Integer;)D", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_debug"})
public final class PersonaResultFragment extends androidx.fragment.app.Fragment {
    private com.philk7.ppaprojectapp.databinding.FragmentPersonaResultBinding binding;
    private final java.lang.Integer[] HMHKVector = {3, 3, 5, 7, 7, 1, 3, 3, 5};
    private final java.lang.Integer[] HMLKVector = {3, 1, 5, 7, 7, 3, 3, 1, 3};
    private final java.lang.Integer[] HMMKVector = {3, 2, 5, 7, 7, 3, 3, 3, 3};
    private final java.lang.Integer[] LMHKVector = {1, 3, 1, 7, 4, 5, 3, 1, 3};
    private final java.lang.Integer[] LMLKVector = {1, 1, 5, 4, 4, 5, 3, 3, 1};
    private final java.lang.Integer[] LMMKVector = {1, 2, 5, 4, 4, 5, 3, 5, 3};
    private final java.lang.Integer[] MMHKVector = {2, 3, 5, 7, 7, 3, 3, 5, 5};
    private final java.lang.Integer[] MMLKVector = {2, 1, 5, 7, 7, 3, 3, 3, 1};
    private final java.lang.Integer[] MMMKVector = {2, 2, 5, 7, 7, 3, 3, 3, 3};
    private int resultingPersonaIndex = -1;
    private java.lang.String[] personaDescriptionSents;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PERSONA_KEY = "assigned_persona_nr";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PERSONA_FIT_KEY = "persona_fit";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String OVERWRITE_KEY = "overwrite";
    @org.jetbrains.annotations.NotNull()
    public static final com.philk7.ppaprojectapp.PersonaResultFragment.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    /**
     * Composes the persona description, depending on the resulted persona.
     * @param personaIndex: index of resulted persona, between 0 and persona amount - 1
     * @param descrSents: the description sentences (previously assigned string-array)
     * @return description string to be shown for persona
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String assemblePersonaDescription(int personaIndex, @org.jetbrains.annotations.NotNull()
    java.lang.String[] descrSents) {
        return null;
    }
    
    /**
     * Computes the Euclidean distance (aka L2 norm) between two (integer) vectors of same length.
     * @param v1: an array of type Int, same length as v2
     * @param v2: an array of type Int, same length as v1
     * @return the Euclidean distance between v1 and v2 as Double value
     */
    private final double calculateEuclideanDistance(java.lang.Integer[] v1, java.lang.Integer[] v2) {
        return 0.0;
    }
    
    /**
     * Builds the persona vector from the persona question answers written to shared preferences.
     * @return the persona vector containing all persona question answers
     */
    private final java.lang.Integer[] buildPersonaVector() {
        return null;
    }
    
    public PersonaResultFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/philk7/ppaprojectapp/PersonaResultFragment$Companion;", "", "()V", "OVERWRITE_KEY", "", "PERSONA_FIT_KEY", "PERSONA_KEY", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}