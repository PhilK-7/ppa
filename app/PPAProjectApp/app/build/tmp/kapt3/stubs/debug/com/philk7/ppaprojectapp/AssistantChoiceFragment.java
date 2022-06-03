package com.philk7.ppaprojectapp;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/philk7/ppaprojectapp/AssistantChoiceFragment;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "Lcom/philk7/ppaprojectapp/databinding/FragmentAssistantChoiceBinding;", "getQueryParamsFromSp", "", "", "qt", "Lcom/philk7/ppaprojectapp/enums/BackendInsertQueryEnum;", "onChoiceButtonClick", "", "assistant", "Lcom/philk7/ppaprojectapp/assistants/AssistantEnum;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_debug"})
public final class AssistantChoiceFragment extends androidx.fragment.app.Fragment {
    private com.philk7.ppaprojectapp.databinding.FragmentAssistantChoiceBinding binding;
    public static final boolean NO_SERVER_INFO_UPDATE = false;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ASSISTANT_KEY = "assistant_id";
    @org.jetbrains.annotations.NotNull()
    public static final com.philk7.ppaprojectapp.AssistantChoiceFragment.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    /**
     * Sets the appropriate text for the assistant card according to the pressed button,
     * and also manipulates button layout.
     * @param assistant: which type of AssistantEnum belongs to pressed button
     */
    private final void onChoiceButtonClick(com.philk7.ppaprojectapp.assistants.AssistantEnum assistant) {
    }
    
    /**
     * Reads the needed information saved to shared preferences (main_sharedpref_name),
     * and assembles the parameter map needed to perform the corresponding backend request.
     * @param qt: which table to insert into
     * @return a Map of pairs key: HTTP param name and value: param value
     */
    private final java.util.Map<java.lang.String, java.lang.String> getQueryParamsFromSp(com.philk7.ppaprojectapp.enums.BackendInsertQueryEnum qt) {
        return null;
    }
    
    public AssistantChoiceFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/philk7/ppaprojectapp/AssistantChoiceFragment$Companion;", "", "()V", "ASSISTANT_KEY", "", "NO_SERVER_INFO_UPDATE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}