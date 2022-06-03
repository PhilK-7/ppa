package com.philk7.ppaprojectapp.databinding;
import com.philk7.ppaprojectapp.R;
import com.philk7.ppaprojectapp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentDecisionMakingBindingImpl extends FragmentDecisionMakingBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.placeChoiceCard, 1);
        sViewsWithIds.put(R.id.pqLl, 2);
        sViewsWithIds.put(R.id.placeQuestion, 3);
        sViewsWithIds.put(R.id.placeSpinner2, 4);
        sViewsWithIds.put(R.id.receiveQuestionCard, 5);
        sViewsWithIds.put(R.id.receiveQuestion, 6);
        sViewsWithIds.put(R.id.twoButtonLl, 7);
        sViewsWithIds.put(R.id.rqYesButton, 8);
        sViewsWithIds.put(R.id.rqNoButton, 9);
        sViewsWithIds.put(R.id.commentCard, 10);
        sViewsWithIds.put(R.id.commentField, 11);
        sViewsWithIds.put(R.id.commentDoneButton, 12);
        sViewsWithIds.put(R.id.decisionMakingHeader, 13);
        sViewsWithIds.put(R.id.decisionRequestsRecycler, 14);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentDecisionMakingBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private FragmentDecisionMakingBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.cardview.widget.CardView) bindings[10]
            , (android.widget.Button) bindings[12]
            , (android.widget.EditText) bindings[11]
            , (android.widget.TextView) bindings[13]
            , (androidx.recyclerview.widget.RecyclerView) bindings[14]
            , (androidx.cardview.widget.CardView) bindings[1]
            , (android.widget.TextView) bindings[3]
            , (android.widget.Spinner) bindings[4]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.TextView) bindings[6]
            , (androidx.cardview.widget.CardView) bindings[5]
            , (android.widget.Button) bindings[9]
            , (android.widget.Button) bindings[8]
            , (android.widget.LinearLayout) bindings[7]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}