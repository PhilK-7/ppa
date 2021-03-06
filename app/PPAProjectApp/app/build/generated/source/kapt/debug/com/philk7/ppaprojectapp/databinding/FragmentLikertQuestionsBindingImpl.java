package com.philk7.ppaprojectapp.databinding;
import com.philk7.ppaprojectapp.R;
import com.philk7.ppaprojectapp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentLikertQuestionsBindingImpl extends FragmentLikertQuestionsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.likertQuestionCard, 2);
        sViewsWithIds.put(R.id.seekBarCard, 3);
        sViewsWithIds.put(R.id.seekBar, 4);
        sViewsWithIds.put(R.id.likertLabel1, 5);
        sViewsWithIds.put(R.id.likertLabel2, 6);
        sViewsWithIds.put(R.id.nextButton, 7);
        sViewsWithIds.put(R.id.previousButton, 8);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentLikertQuestionsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private FragmentLikertQuestionsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[6]
            , (androidx.cardview.widget.CardView) bindings[2]
            , (android.widget.TextView) bindings[1]
            , (android.widget.Button) bindings[7]
            , (android.widget.Button) bindings[8]
            , (android.widget.SeekBar) bindings[4]
            , (androidx.cardview.widget.CardView) bindings[3]
            );
        this.likertQuestionStatementText.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
        if (BR.question == variableId) {
            setQuestion((com.philk7.ppaprojectapp.LikertQuestionsFragment.Question) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setQuestion(@Nullable com.philk7.ppaprojectapp.LikertQuestionsFragment.Question Question) {
        this.mQuestion = Question;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.question);
        super.requestRebind();
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
        java.lang.String questionSent = null;
        com.philk7.ppaprojectapp.LikertQuestionsFragment.Question question = mQuestion;

        if ((dirtyFlags & 0x3L) != 0) {



                if (question != null) {
                    // read question.sent
                    questionSent = question.getSent();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.likertQuestionStatementText, questionSent);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): question
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}