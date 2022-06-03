package com.philk7.ppaprojectapp;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0016\u0018\u0000 \u001e2\u00020\u0001:\u0003\u001e\u001f B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J \u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0084.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006!"}, d2 = {"Lcom/philk7/ppaprojectapp/DecisionPanelFragment;", "Landroidx/fragment/app/Fragment;", "()V", "ab", "Landroidx/appcompat/app/ActionBar;", "binding", "Lcom/philk7/ppaprojectapp/databinding/FragmentDecisionPanelBinding;", "currentPid", "", "sharedPref", "Landroid/content/SharedPreferences;", "getSharedPref", "()Landroid/content/SharedPreferences;", "setSharedPref", "(Landroid/content/SharedPreferences;)V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setupDecisionCardRecycler", "", "Lcom/philk7/ppaprojectapp/DecisionPanelFragment$Decision;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "filterToday", "", "Companion", "Decision", "DecisionsAdapter", "app_debug"})
public class DecisionPanelFragment extends androidx.fragment.app.Fragment {
    private com.philk7.ppaprojectapp.databinding.FragmentDecisionPanelBinding binding;
    private androidx.appcompat.app.ActionBar ab;
    private java.lang.String currentPid = "";
    protected android.content.SharedPreferences sharedPref;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DAILY_NOTF_TIME_KEY = "daily_overview_time";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DAILY_NOTF_TOGGLE_KEY = "daily_overview_toggle";
    @org.jetbrains.annotations.NotNull()
    public static final com.philk7.ppaprojectapp.DecisionPanelFragment.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    protected final android.content.SharedPreferences getSharedPref() {
        return null;
    }
    
    protected final void setSharedPref(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    /**
     * Receives and processes the current user's decisions from the backend,
     * and sets the RecyclerView properties accordingly.
     * @param recyclerView: the current layout's recycler view
     * @param filterToday: whether to only keep today's decisions
     * @return list of loaded decisions
     */
    @org.jetbrains.annotations.Nullable()
    protected final java.util.List<com.philk7.ppaprojectapp.DecisionPanelFragment.Decision> setupDecisionCardRecycler(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, boolean filterToday) {
        return null;
    }
    
    public DecisionPanelFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0016\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\nH\u00c6\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001f\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f\u00a8\u0006 "}, d2 = {"Lcom/philk7/ppaprojectapp/DecisionPanelFragment$Decision;", "", "did", "", "timestamp", "", "place", "device", "data", "access", "", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getAccess", "()Z", "getData", "()Ljava/lang/String;", "getDevice", "getDid", "()I", "getPlace", "getTimestamp", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
    public static final class Decision {
        private final int did = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String timestamp = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String place = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String device = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String data = null;
        private final boolean access = false;
        
        public final int getDid() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTimestamp() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPlace() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDevice() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getData() {
            return null;
        }
        
        public final boolean getAccess() {
            return false;
        }
        
        public Decision(int did, @org.jetbrains.annotations.NotNull()
        java.lang.String timestamp, @org.jetbrains.annotations.NotNull()
        java.lang.String place, @org.jetbrains.annotations.NotNull()
        java.lang.String device, @org.jetbrains.annotations.NotNull()
        java.lang.String data, boolean access) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component5() {
            return null;
        }
        
        public final boolean component6() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.philk7.ppaprojectapp.DecisionPanelFragment.Decision copy(int did, @org.jetbrains.annotations.NotNull()
        java.lang.String timestamp, @org.jetbrains.annotations.NotNull()
        java.lang.String place, @org.jetbrains.annotations.NotNull()
        java.lang.String device, @org.jetbrains.annotations.NotNull()
        java.lang.String data, boolean access) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object p0) {
            return false;
        }
    }
    
    /**
     * The adapter for the decision recycler view.
     * @param mDecisions: the loaded decisions
     * @param outerContext: fragment's context
     * @param outerThis: direct reference to fragment namespace
     * @param pid: user's ID
     */
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001bB+\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u001c\u0010\u0013\u001a\u00020\u00142\n\u0010\u0015\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0012H\u0016J\u001c\u0010\u0017\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/philk7/ppaprojectapp/DecisionPanelFragment$DecisionsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/philk7/ppaprojectapp/DecisionPanelFragment$DecisionsAdapter$ViewHolder;", "mDecisions", "", "Lcom/philk7/ppaprojectapp/DecisionPanelFragment$Decision;", "outerContext", "Landroid/content/Context;", "outerThis", "Lcom/philk7/ppaprojectapp/DecisionPanelFragment;", "pid", "", "(Ljava/util/List;Landroid/content/Context;Lcom/philk7/ppaprojectapp/DecisionPanelFragment;Ljava/lang/String;)V", "changePrivacyDecision", "", "oldDecision", "newAccess", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_debug"})
    public static final class DecisionsAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.philk7.ppaprojectapp.DecisionPanelFragment.DecisionsAdapter.ViewHolder> {
        private final java.util.List<com.philk7.ppaprojectapp.DecisionPanelFragment.Decision> mDecisions = null;
        private final android.content.Context outerContext = null;
        private final com.philk7.ppaprojectapp.DecisionPanelFragment outerThis = null;
        private final java.lang.String pid = null;
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.philk7.ppaprojectapp.DecisionPanelFragment.DecisionsAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.philk7.ppaprojectapp.DecisionPanelFragment.DecisionsAdapter.ViewHolder holder, int position) {
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        /**
         * Changes a privacy decision, by deleting its old instance, and inserting the new one
         * into the table pdecision. Uses a new decision ID.
         * @param oldDecision: the Decision that is modified / replaced
         * @param newAccess: whether the decision allows or denies access
         * @return whether both parts of the request were successful
         */
        private final boolean changePrivacyDecision(com.philk7.ppaprojectapp.DecisionPanelFragment.Decision oldDecision, boolean newAccess) {
            return false;
        }
        
        public DecisionsAdapter(@org.jetbrains.annotations.NotNull()
        java.util.List<com.philk7.ppaprojectapp.DecisionPanelFragment.Decision> mDecisions, @org.jetbrains.annotations.NotNull()
        android.content.Context outerContext, @org.jetbrains.annotations.NotNull()
        com.philk7.ppaprojectapp.DecisionPanelFragment outerThis, @org.jetbrains.annotations.NotNull()
        java.lang.String pid) {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0011\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\f\u00a8\u0006\u0019"}, d2 = {"Lcom/philk7/ppaprojectapp/DecisionPanelFragment$DecisionsAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "decisionItemView", "Landroid/view/View;", "(Lcom/philk7/ppaprojectapp/DecisionPanelFragment$DecisionsAdapter;Landroid/view/View;)V", "accessIndicator", "Landroid/widget/ImageView;", "getAccessIndicator", "()Landroid/widget/ImageView;", "accessView", "Landroid/widget/TextView;", "getAccessView", "()Landroid/widget/TextView;", "dataView", "getDataView", "deviceView", "getDeviceView", "placeView", "getPlaceView", "theCard", "Landroidx/cardview/widget/CardView;", "getTheCard", "()Landroidx/cardview/widget/CardView;", "timeView", "getTimeView", "app_debug"})
        public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull()
            private final android.widget.TextView timeView = null;
            @org.jetbrains.annotations.NotNull()
            private final android.widget.TextView placeView = null;
            @org.jetbrains.annotations.NotNull()
            private final android.widget.TextView deviceView = null;
            @org.jetbrains.annotations.NotNull()
            private final android.widget.TextView dataView = null;
            @org.jetbrains.annotations.NotNull()
            private final android.widget.TextView accessView = null;
            @org.jetbrains.annotations.NotNull()
            private final android.widget.ImageView accessIndicator = null;
            @org.jetbrains.annotations.NotNull()
            private final androidx.cardview.widget.CardView theCard = null;
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.TextView getTimeView() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.TextView getPlaceView() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.TextView getDeviceView() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.TextView getDataView() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.TextView getAccessView() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final android.widget.ImageView getAccessIndicator() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final androidx.cardview.widget.CardView getTheCard() {
                return null;
            }
            
            public ViewHolder(@org.jetbrains.annotations.NotNull()
            android.view.View decisionItemView) {
                super(null);
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u001e\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/philk7/ppaprojectapp/DecisionPanelFragment$Companion;", "", "()V", "DAILY_NOTF_TIME_KEY", "", "DAILY_NOTF_TOGGLE_KEY", "cancelAlarm", "", "context", "Landroid/content/Context;", "scheduleReminderNotification", "hour", "", "minute", "app_debug"})
    public static final class Companion {
        
        /**
         * Schedules the reminder notification that opens the daily decisions overview
         * for a specific selected time.
         * @param context: caller context, either this fragment, or home activity
         * @param hour: the time's clock hour
         * @param minute: the time's clock minute
         */
        public final void scheduleReminderNotification(@org.jetbrains.annotations.NotNull()
        android.content.Context context, int hour, int minute) {
        }
        
        /**
         * Cancels a set daily reminder alarm instance.
         * @param context: caller context
         */
        public final void cancelAlarm(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
        
        private Companion() {
            super();
        }
    }
}