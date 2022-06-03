package com.philk7.ppaprojectapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class StartActivity : AppCompatActivity() {

    // This activity is transparent. It is the starting point of this app.
    // Use the static object for (global) configurations.

    private var introWasFinished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // check if survey has already been completed before
        val sharedPref = this.getSharedPreferences(
            resources.getString(R.string.main_sharedpref_name), MODE_PRIVATE
        )
        with(sharedPref?.edit()) {
            val savedAssignedAssistant = sharedPref
                ?.getInt(AssistantChoiceFragment.ASSISTANT_KEY, -1)
            if (savedAssignedAssistant != -1) introWasFinished = true  // survey completed before
            this?.apply()
        }

        // crash when wrong configuration
        if (BuildConfig.DEBUG && (FORCE_HOME_MENU && FORCE_START_SCREEN)) {
            error("Wrong configuration of parameters in StartActivity static object!")
        }
        // overriding for introWasFinished
        if(FORCE_HOME_MENU) introWasFinished = true
        else if(FORCE_START_SCREEN) introWasFinished = false

        // select actual starting activity
        if (this.introWasFinished) {  // go straight to home
            val homeIntent = Intent(this, HomeActivity::class.java)
            startActivity(homeIntent)
        } else {  // start survey (AP1 part)
            val introIntent = Intent(this, FirstIDActivity::class.java)
            startActivity(introIntent)
        }
    }

    // configuration parameters
    companion object {

        // next two parameters for overriding introWasFinished, to manipulate the start activity
        // cannot be both true at the same time!
        // std: false for both
        const val FORCE_HOME_MENU = false
        const val FORCE_START_SCREEN = false
    }
}