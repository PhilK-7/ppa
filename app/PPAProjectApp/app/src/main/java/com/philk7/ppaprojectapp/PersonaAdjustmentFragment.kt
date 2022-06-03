package com.philk7.ppaprojectapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.philk7.ppaprojectapp.databinding.FragmentPersonaAdjustmentBinding

class PersonaAdjustmentFragment : Fragment() {

    private lateinit var binding: FragmentPersonaAdjustmentBinding
    private lateinit var personaDescriptionSents: Array<String>
    private var currentDescriptionIndex: Int = 0
    private lateinit var descriptionMatchDegrees: Array<Int>


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_persona_adjustment, container, false)

        // init array, adjusted to amount of personas
        this.descriptionMatchDegrees = Array(this.requireContext()
            .resources.getStringArray(R.array.pp_codes).size){-1}

        // set first description
        this.personaDescriptionSents = resources
                .getStringArray(R.array.persona_results_description_sents)
        binding.descriptionMatchDegreeText.text =
                PersonaResultFragment().assemblePersonaDescription(
                        this.currentDescriptionIndex, this.personaDescriptionSents)

        binding.nextDescriptionButton.setOnClickListener {
            if (this.currentDescriptionIndex < resources.getStringArray(R.array.pp_codes).size-1) {
                // save answer
                this.descriptionMatchDegrees[this.currentDescriptionIndex] =
                        binding.descriptionMatchSeekbar.progress
                binding.descriptionMatchSeekbar.progress = 2  // standard init value
                // proceed to next description
                binding.descriptionMatchDegreeText.text =
                        PersonaResultFragment().assemblePersonaDescription(
                                ++this.currentDescriptionIndex, this.personaDescriptionSents)
                binding.descriptionScroller.scrollTo(0, 0)  // scroll back to top

            } else {

                // save last answer
                this.descriptionMatchDegrees[resources.getStringArray(R.array.pp_codes).size-1] =
                    binding.descriptionMatchSeekbar.progress
                // final persona assignment
                val matchDegreesMaxIndex: Int? = descriptionMatchDegrees
                        .indices.maxByOrNull { descriptionMatchDegrees[it] }  // argmax of matching
                val sharedPref = this.activity?.getSharedPreferences(
                        resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE)
                with(sharedPref?.edit()) {  // write adjustment results
                    if (matchDegreesMaxIndex != null) {
                        this?.putInt(
                                PersonaResultFragment.PERSONA_KEY, matchDegreesMaxIndex+1)  // 1-9
                        this?.putInt(PersonaResultFragment.PERSONA_FIT_KEY,
                            descriptionMatchDegrees[matchDegreesMaxIndex]+1)
                        // indicate overwritten assignment
                        this?.putBoolean(PersonaResultFragment.OVERWRITE_KEY, true)
                    }
                    this?.apply()
                }

                // goto survey end: persona result, assistant choice
                activity?.supportFragmentManager?.commit {
                    replace(R.id.dqNavHostFragment, IntroFinalFragment())
                }
            }

        }

        // update progress bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            this.activity?.findViewById<ProgressBar>(R.id.surveyProgressBar)
                ?.setProgress(80, true)
        else
            this.activity?.findViewById<ProgressBar>(R.id.surveyProgressBar)?.progress = 80

        return binding.root
    }

}