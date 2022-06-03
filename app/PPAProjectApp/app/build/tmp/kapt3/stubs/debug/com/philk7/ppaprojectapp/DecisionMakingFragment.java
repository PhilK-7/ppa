package com.philk7.ppaprojectapp;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/philk7/ppaprojectapp/DecisionMakingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "Lcom/philk7/ppaprojectapp/databinding/FragmentDecisionMakingBinding;", "comment", "", "receiveDMN", "", "sp", "Landroid/content/SharedPreferences;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "DecisionRequestAdapter", "app_debug"})
public final class DecisionMakingFragment extends androidx.fragment.app.Fragment {
    private com.philk7.ppaprojectapp.databinding.FragmentDecisionMakingBinding binding;
    private android.content.SharedPreferences sp;
    private boolean receiveDMN = true;
    private java.lang.String comment = "";
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    public DecisionMakingFragment() {
        super();
    }
    
    /**
     * Adapter for showing decision requests in a recycler view.
     * @param mDecisionRequests: the read decision requests to answer
     * @param theFrag: reference to this fragment
     * @param pid: current user's ID
     */
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001!B#\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u001c\u0010\u0010\u001a\u00020\u000f2\n\u0010\u0011\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0012\u001a\u00020\fH\u0016J\u001c\u0010\u0013\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\fH\u0016J@\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020 H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/philk7/ppaprojectapp/DecisionMakingFragment$DecisionRequestAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/philk7/ppaprojectapp/DecisionMakingFragment$DecisionRequestAdapter$ViewHolder;", "mDecisionRequests", "", "Lcom/philk7/ppaprojectapp/utils/DecisionRequest;", "theFrag", "Lcom/philk7/ppaprojectapp/DecisionMakingFragment;", "pid", "", "(Ljava/util/List;Lcom/philk7/ppaprojectapp/DecisionMakingFragment;Ljava/lang/String;)V", "remainingDecisions", "", "getItemCount", "onAllDecisionsMade", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onPressDecisionButton", "context", "Landroid/content/Context;", "card", "Landroidx/cardview/widget/CardView;", "place", "device", "data", "access", "", "ViewHolder", "app_debug"})
    public static final class DecisionRequestAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.philk7.ppaprojectapp.DecisionMakingFragment.DecisionRequestAdapter.ViewHolder> {
        private int remainingDecisions;
        private final java.util.List<com.philk7.ppaprojectapp.utils.DecisionRequest> mDecisionRequests = null;
        private final com.philk7.ppaprojectapp.DecisionMakingFragment theFrag = null;
        private final java.lang.String pid = null;
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.philk7.ppaprojectapp.DecisionMakingFragment.DecisionRequestAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.philk7.ppaprojectapp.DecisionMakingFragment.DecisionRequestAdapter.ViewHolder holder, int position) {
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        /**
         * Inserts the decision, and removes the decision card from the panel.
         * @param context: (outer) context
         * @param card: the current card view
         * @param pid: user ID
         * @param place: currently indicated place by the fragment
         * @param device: the decision's device type
         * @param data: the decision's data type
         * @param access: whether the decision request is allowed / denied
         */
        private final void onPressDecisionButton(android.content.Context context, androidx.cardview.widget.CardView card, java.lang.String pid, java.lang.String place, java.lang.String device, java.lang.String data, boolean access) {
        }
        
        /**
         * Called when all decision cards are gone. Writes the (last entered) place to the
         * location cache, sends remaining collected information to the server,
         * and returns to the home screen.
         */
        private final void onAllDecisionsMade() {
        }
        
        public DecisionRequestAdapter(@org.jetbrains.annotations.NotNull()
        java.util.List<com.philk7.ppaprojectapp.utils.DecisionRequest> mDecisionRequests, @org.jetbrains.annotations.NotNull()
        com.philk7.ppaprojectapp.DecisionMakingFragment theFrag, @org.jetbrains.annotations.NotNull()
        java.lang.String pid) {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"Lcom/philk7/ppaprojectapp/DecisionMakingFragment$DecisionRequestAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "decisionRequestView", "Landroid/view/View;", "(Lcom/philk7/ppaprojectapp/DecisionMakingFragment$DecisionRequestAdapter;Landroid/view/View;)V", "allowButton", "Landroid/widget/ImageButton;", "getAllowButton", "()Landroid/widget/ImageButton;", "dataText", "Landroid/widget/TextView;", "getDataText", "()Landroid/widget/TextView;", "denyButton", "getDenyButton", "deviceText", "getDeviceText", "theCard", "Landroidx/cardview/widget/CardView;", "getTheCard", "()Landroidx/cardview/widget/CardView;", "app_debug"})
        public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull()
            private final androidx.cardview.widget.CardView theCard = null;
            @org.jetbrains.annotations.NotNull()
            private final android.widget.ImageButton allowButton = null;
            @org.jetbrains.annotations.NotNull()
            private final android.widget.ImageButton denyButton = null;
            @org.jetbrains.annotations.NotNull()
            private final android.widget.TextView deviceText = null;
            @org.jetbrains.annotations.NotNull()
            private final android.widget.TextView dataText = null;
            
            @org.jetbrains.annotations.NotNull()
            public final androidx.cardview.widget.CardView getTheCard() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.ImageButton getAllowButton() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.ImageButton getDenyButton() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.TextView getDeviceText() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.TextView getDataText() {
                return null;
            }
            
            public ViewHolder(@org.jetbrains.annotations.NotNull()
            android.view.View decisionRequestView) {
                super(null);
            }
        }
    }
}