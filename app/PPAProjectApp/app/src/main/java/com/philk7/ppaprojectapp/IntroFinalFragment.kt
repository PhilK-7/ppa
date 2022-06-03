package com.philk7.ppaprojectapp

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.philk7.ppaprojectapp.databinding.FragmentIntroFinalBinding

class IntroFinalFragment : Fragment() {

    private lateinit var binding: FragmentIntroFinalBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_intro_final, container, false)

        val sharedPref = this.activity?.getSharedPreferences(
            resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        val ppIndex = sharedPref?.getInt(PersonaResultFragment.PERSONA_KEY, 0) ?: 0
        binding.showPersonaText.text = "The persona is Nr. $ppIndex"

        binding.introFinishButton.setOnClickListener {

            // goto assistant choice
            activity?.supportFragmentManager?.commit {
                replace(R.id.dqNavHostFragment, AssistantChoiceFragment())
            }
        }

        // some extra animations
        binding.introFinishButton.visibility = View.INVISIBLE
        binding.showPersonaText.startAnimation(
            AnimationUtils.loadAnimation(this.context, R.anim.grow)
        )
        binding.ppAssignedText.startAnimation(
            AnimationUtils.loadAnimation(this.context, R.anim.shift)
        )
        // show DONE button at end of animations
        Handler(Looper.getMainLooper()).postDelayed({
            binding.introFinishButton.visibility = View.VISIBLE
        }, this.resources.getInteger(R.integer.slow_hide_duration).toLong())


        return binding.root
    }


}