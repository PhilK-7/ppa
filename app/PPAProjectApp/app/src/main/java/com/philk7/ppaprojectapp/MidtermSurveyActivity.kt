package com.philk7.ppaprojectapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MidtermSurveyActivity : AppCompatActivity() {

    // Put the link/content for the mid-term survey here.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_midterm_survey)

        this.supportActionBar?.title = "Midterm Survey"
    }

}