package com.philk7.ppaprojectapp

import android.content.Context
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.philk7.ppaprojectapp.databinding.FragmentMovementTestingBinding
import com.philk7.ppaprojectapp.utils.LocationProcessing
import kotlin.math.roundToInt


class MovementTestingFragment : Fragment() {

    private lateinit var binding: FragmentMovementTestingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // adjust action bar
        val ab = (activity as AppCompatActivity).supportActionBar!!
        ab.setDisplayHomeAsUpEnabled(true)
        ab.title = "Movement Testing"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movement_testing,
            container, false)

        val currentStepCount: Int
        val currentStepCache: Int
        val currentLocation: String
        val previousLocation: String
        // set values
        val sharedPref = this.activity?.getSharedPreferences(resources.getString(R.string.main_sharedpref_name),
            Context.MODE_PRIVATE)
        with(sharedPref?.edit()){
            currentStepCount = sharedPref?.getInt(LocationProcessing.STEPS_KEY, 0) ?: 0
            currentLocation = sharedPref?.getString(LocationProcessing.LOCATION_KEY, "") ?: ""
            currentStepCache = sharedPref?.getInt(LocationProcessing.STEPS_CACHE_KEY, 0) ?: 0
            previousLocation = sharedPref?.getString(LocationProcessing.PREV_LOCATION_KEY, "") ?: ""
            this?.apply()
        }
        binding.stepsValue.text = currentStepCount.toString()
        binding.stepsCacheValue.text = "( ${currentStepCache} )"
        val nextEventVal =
            (currentStepCache + LocationProcessing.STEPS_PLACE_CHANGED_THRESHOLD).roundToInt() + 1
        binding.nextEventValue.text = "next change @ ${nextEventVal}"
        binding.locationValue.text = currentLocation
        binding.prevLocationValue.text = previousLocation

        return binding.root
    }

}