package com.philk7.ppaprojectapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.philk7.ppaprojectapp.databinding.FragmentIotQuestionsBinding

class IotQuestionsFragment : Fragment() {
    private lateinit var binding: FragmentIotQuestionsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_iot_questions,
            container, false)

        binding.iotqCard.startAnimation(
            AnimationUtils.loadAnimation(this.context, R.anim.show_delay)
        )

        binding.iq2YesButton.setOnClickListener {
            binding.iotDoneButton.visibility = View.VISIBLE
            binding.iotListingField.visibility = View.VISIBLE
        }
        binding.iq2NoButton.setOnClickListener {
            binding.iotListingField.visibility = View.INVISIBLE
            binding.iotDoneButton.visibility = View.VISIBLE
        }
        binding.iotDoneButton.setOnClickListener {

            // when radio button set to "Yes", text field must not be empty
            if(binding.iq2YesButton.isChecked && binding.iotListingField.text.isEmpty()){
                val t = Toast.makeText(this.requireContext(), "Please list your IoT devices!",
                    Toast.LENGTH_SHORT)
                t.show()

                return@setOnClickListener
            }

            // write information to shared preferences
            val sharedPref = this.activity?.getSharedPreferences(
                resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
            )
            with(sharedPref?.edit()){
                this?.putBoolean(IOT_OWN_KEY,
                when(binding.iq2YesButton.isChecked){
                    true -> true
                    else -> false
                })
                this?.putString(IOT_DEVICE_KEY, binding.iotListingField.text.toString())
                this?.apply()
            }

            // go to persona assignment questions
            this.activity?.supportFragmentManager?.commit {
                replace(R.id.dqNavHostFragment, PersonaQuestionsFragment())
            }
        }

        // update progress bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            this.activity?.findViewById<ProgressBar>(R.id.surveyProgressBar)
                ?.setProgress(50, true)
        else
            this.activity?.findViewById<ProgressBar>(R.id.surveyProgressBar)?.progress = 50


        return binding.root
    }


    companion object {

        const val IOT_OWN_KEY = "iot_own"
        const val IOT_DEVICE_KEY = "iot_owned_devices"
    }
}