package com.philk7.ppaprojectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class QuestionnaireActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)
        supportActionBar?.hide()
    }
}