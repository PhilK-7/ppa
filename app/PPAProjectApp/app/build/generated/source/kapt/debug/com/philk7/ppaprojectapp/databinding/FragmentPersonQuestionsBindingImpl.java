package com.philk7.ppaprojectapp.databinding;
import com.philk7.ppaprojectapp.R;
import com.philk7.ppaprojectapp.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentPersonQuestionsBindingImpl extends FragmentPersonQuestionsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.linLay, 1);
        sViewsWithIds.put(R.id.genderQuestion, 2);
        sViewsWithIds.put(R.id.gqRadioGroup, 3);
        sViewsWithIds.put(R.id.gqR1, 4);
        sViewsWithIds.put(R.id.gqR2, 5);
        sViewsWithIds.put(R.id.gqR3, 6);
        sViewsWithIds.put(R.id.customGenderText, 7);
        sViewsWithIds.put(R.id.gqR4, 8);
        sViewsWithIds.put(R.id.ageQuestion, 9);
        sViewsWithIds.put(R.id.ageEditField, 10);
        sViewsWithIds.put(R.id.countryQuestion, 11);
        sViewsWithIds.put(R.id.countryTextField, 12);
        sViewsWithIds.put(R.id.employmentQuestion, 13);
        sViewsWithIds.put(R.id.eqRadioGroup, 14);
        sViewsWithIds.put(R.id.eqR1, 15);
        sViewsWithIds.put(R.id.eqR2, 16);
        sViewsWithIds.put(R.id.eqR3, 17);
        sViewsWithIds.put(R.id.eqR4, 18);
        sViewsWithIds.put(R.id.eqR5, 19);
        sViewsWithIds.put(R.id.eqR6, 20);
        sViewsWithIds.put(R.id.eqR7, 21);
        sViewsWithIds.put(R.id.eqR8, 22);
        sViewsWithIds.put(R.id.eqR9, 23);
        sViewsWithIds.put(R.id.eqR10, 24);
        sViewsWithIds.put(R.id.employmentOtherField, 25);
        sViewsWithIds.put(R.id.eqR11, 26);
        sViewsWithIds.put(R.id.internetQuestion, 27);
        sViewsWithIds.put(R.id.iqRadioGroup, 28);
        sViewsWithIds.put(R.id.iqR1, 29);
        sViewsWithIds.put(R.id.iqR2, 30);
        sViewsWithIds.put(R.id.iqR3, 31);
        sViewsWithIds.put(R.id.iqR4, 32);
        sViewsWithIds.put(R.id.iotQuestion, 33);
        sViewsWithIds.put(R.id.iotqRadioGroup, 34);
        sViewsWithIds.put(R.id.iotHeardButton, 35);
        sViewsWithIds.put(R.id.iotNotheardButton, 36);
        sViewsWithIds.put(R.id.howIotExplanation, 37);
        sViewsWithIds.put(R.id.nextQuestionSectionButton, 38);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentPersonQuestionsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 39, sIncludes, sViewsWithIds));
    }
    private FragmentPersonQuestionsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.EditText) bindings[10]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[11]
            , (android.widget.EditText) bindings[12]
            , (android.widget.EditText) bindings[7]
            , (android.widget.EditText) bindings[25]
            , (android.widget.TextView) bindings[13]
            , (android.widget.RadioButton) bindings[15]
            , (android.widget.RadioButton) bindings[24]
            , (android.widget.RadioButton) bindings[26]
            , (android.widget.RadioButton) bindings[16]
            , (android.widget.RadioButton) bindings[17]
            , (android.widget.RadioButton) bindings[18]
            , (android.widget.RadioButton) bindings[19]
            , (android.widget.RadioButton) bindings[20]
            , (android.widget.RadioButton) bindings[21]
            , (android.widget.RadioButton) bindings[22]
            , (android.widget.RadioButton) bindings[23]
            , (android.widget.RadioGroup) bindings[14]
            , (android.widget.TextView) bindings[2]
            , (android.widget.RadioButton) bindings[4]
            , (android.widget.RadioButton) bindings[5]
            , (android.widget.RadioButton) bindings[6]
            , (android.widget.RadioButton) bindings[8]
            , (android.widget.RadioGroup) bindings[3]
            , (android.widget.EditText) bindings[37]
            , (android.widget.TextView) bindings[27]
            , (android.widget.RadioButton) bindings[35]
            , (android.widget.RadioButton) bindings[36]
            , (android.widget.TextView) bindings[33]
            , (android.widget.RadioGroup) bindings[34]
            , (android.widget.RadioButton) bindings[29]
            , (android.widget.RadioButton) bindings[30]
            , (android.widget.RadioButton) bindings[31]
            , (android.widget.RadioButton) bindings[32]
            , (android.widget.RadioGroup) bindings[28]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.Button) bindings[38]
            );
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
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