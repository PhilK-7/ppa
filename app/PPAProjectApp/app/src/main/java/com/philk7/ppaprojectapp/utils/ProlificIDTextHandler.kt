package com.philk7.ppaprojectapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class ProlificIDTextHandler(val et: EditText, val imm: InputMethodManager) : TextWatcher {


    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        return
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // when pressing back on '-' char, delete it and previous char
        if (p1 != 0 && p2 == 1 && p3 == 0)
            when ((p1+1) % 5) {
                0 -> {
                    et.setText(et.text.toString().subSequence(0, p1 - 1))
                    et.setSelection(p1 - 1)
                }
                else -> return
            }
    }


    override fun afterTextChanged(p0: Editable?) {

        when (val len = et.text.toString().length) {
            4, 9, 14, 19, 24 -> {  // add '-' in between every four characters
                et.setText(et.text.toString() + "-")
                et.setSelection(len + 1)
            }
            29 -> {  // close keyboard when PID complete
                imm.hideSoftInputFromWindow(et.windowToken, 0)
            }
            else -> return
        }

    }

}