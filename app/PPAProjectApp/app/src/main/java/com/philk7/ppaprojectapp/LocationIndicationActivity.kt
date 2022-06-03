package com.philk7.ppaprojectapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.philk7.ppaprojectapp.assistants.ActionEnum
import com.philk7.ppaprojectapp.utils.LocationProcessing


class LocationIndicationActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_indication)

        this.supportActionBar?.title = "Choose your location"

        // set custom spinner
        val spinner = findViewById<Spinner>(R.id.placeSpinner3)
        val placeOptions = this.applicationContext.resources.getStringArray(R.array.place_examples)
        val spadapter = ArrayAdapter.createFromResource(
            this, R.array.place_examples, R.layout.spinner_custom
        )
        spadapter.setDropDownViewResource(R.layout.spinner_custom)
        spinner.adapter = spadapter

        // handle custom location elements
        val field = findViewById<EditText>(R.id.customLocationField)
        val button = findViewById<Button>(R.id.confirmCLButton)
        field.visibility = View.INVISIBLE

        // set current selected spinner item depending on saved location
        val currentPlace = LocationProcessing.readLastPlace(this.applicationContext)
        val currentItemIndex = placeOptions.indexOf(currentPlace)
        if (currentItemIndex != -1)
            spinner.setSelection(currentItemIndex)
        else {
            spinner.setSelection(placeOptions.indexOf("Other"))
            field.setText(currentPlace, TextView.BufferType.EDITABLE)
            field.visibility = View.VISIBLE
        }

        // show input field when "other" selected as place
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                if (spinner.selectedItem.toString().contains("other", true)) {
                    field.visibility = View.VISIBLE
                } else
                    field.visibility = View.INVISIBLE

            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                button.visibility = View.INVISIBLE
            }
        }

        // close keyboard when done button pressed
        field.setOnEditorActionListener { textView, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                val iman = textView.context
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                iman.hideSoftInputFromWindow(textView.windowToken, 0)
                true
            }
            false
        }

        // write either selected item, or custom input for "other"
        button.setOnClickListener {
            val selected = spinner.selectedItem.toString()
            if (selected.contains("other", true))
                LocationProcessing.writeLastPlace(this.applicationContext, field.text.toString())
            else
                LocationProcessing.writeLastPlace(this.applicationContext, selected)

            // check if location changed, maybe trigger privacy query
            if (LocationProcessing.checkIndicatedLocationChanged(this.applicationContext))
                HomeActivity.passHandleToAssistant(
                    this.applicationContext,
                    ActionEnum.PRIVACY_QUERY
                )

            // go back to home screen
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }


    }

}