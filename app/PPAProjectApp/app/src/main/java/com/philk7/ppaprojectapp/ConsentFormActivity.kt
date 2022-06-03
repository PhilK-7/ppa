package com.philk7.ppaprojectapp

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import kotlin.random.Random

class ConsentFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consent_form)

        supportActionBar?.setTitle(R.string.cf_ab_title)
        supportActionBar
            ?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.accent_color)))

        // check passed (end of previous activity), now also write pid to sharedPref
        val pid = intent.getStringExtra(FirstIDActivity.PID_KEY)  // get (correct) pid
        val sharedPref = this.getSharedPreferences(
                resources.getString(R.string.main_sharedpref_name), MODE_PRIVATE)
        // registered user written to shared preferences
        with(sharedPref.edit()){
            this?.putString(FirstIDActivity.PID_KEY, pid)
            this?.apply()
        }


        findViewById<Button>(R.id.consentFormReadButton).setOnClickListener {
            val consentReadCheckBox = findViewById<CheckBox>(R.id.consentFormReadCheck)
            if(consentReadCheckBox.isChecked){  // proceed to questionnaire (survey) if checked

                // spot to also assign user to random group
                val dnaArray = this
                    .applicationContext.resources.getIntArray(R.array.dna_random_groups)
                val groupIndex = Random.nextInt(dnaArray.size)
                println("User assigned to random group ${groupIndex+1}")
                with(sharedPref.edit()){
                    this.putInt(DAILY_NOTIFICATION_AMOUNT_RANDOM_KEY, dnaArray[groupIndex])
                    this.apply()
                }

                // go to survey activity
                val i = Intent(this, QuestionnaireActivity::class.java)
                startActivity(i)
                overridePendingTransition(R.anim.slide_in_special, R.anim.slide_out_special)
            }
            else{  // warning toast
                Toast.makeText(this,
                        "Please agree to the terms of the study to proceed.",
                        Toast.LENGTH_SHORT).show()
            }

        }
    }

    companion object {
        const val DAILY_NOTIFICATION_AMOUNT_RANDOM_KEY = "daily_notification_amount_"
    }

}