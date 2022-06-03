package com.philk7.ppaprojectapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.philk7.ppaprojectapp.databinding.FragmentPersonaResultBinding
import kotlin.math.sqrt

class PersonaResultFragment : Fragment() {

    private lateinit var binding: FragmentPersonaResultBinding

    // the Persona vectors (also see PersonaTypeEnum; adjust when changing personas)
    private val HMHKVector = arrayOf(3,3,5,7,7,1,3,3,5)
    private val HMLKVector = arrayOf(3,1,5,7,7,3,3,1,3)
    private val HMMKVector = arrayOf(3,2,5,7,7,3,3,3,3)
    private val LMHKVector = arrayOf(1,3,1,7,4,5,3,1,3)
    private val LMLKVector = arrayOf(1,1,5,4,4,5,3,3,1)
    private val LMMKVector = arrayOf(1,2,5,4,4,5,3,5,3)
    private val MMHKVector = arrayOf(2,3,5,7,7,3,3,5,5)
    private val MMLKVector = arrayOf(2,1,5,7,7,3,3,3,1)
    private val MMMKVector = arrayOf(2,2,5,7,7,3,3,3,3)

    private var resultingPersonaIndex: Int = -1
    private lateinit var personaDescriptionSents: Array<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_persona_result,
                    container, false)

        // delay view
        binding.linLay.startAnimation(
            AnimationUtils.loadAnimation(this.context, R.anim.show_delay)
        )

        // find out the resulting persona
        val personaTypeVectors = listOf(
                HMHKVector,
                HMLKVector,
                HMMKVector,
                LMHKVector,
                LMLKVector,
                LMMKVector,
                MMHKVector,
                MMLKVector,
                MMMKVector
        )
        val vectorDistancesToPersonaVector: Array<Double>
            = Array(personaTypeVectors.size){ Double.MAX_VALUE }
        val personaVector = this.buildPersonaVector()  // the persona vector resulting from PQ
        for(i in personaTypeVectors.indices){
            // compute Euclidean distance between persona vector and all persona type vectors
            vectorDistancesToPersonaVector[i] = calculateEuclideanDistance(
                    personaTypeVectors[i], personaVector)
        }

        // set resulting persona (its index)
        val indexOfVectorWithMinDistance: Int? = vectorDistancesToPersonaVector
                .indices.minByOrNull { vectorDistancesToPersonaVector[it] }
        if (indexOfVectorWithMinDistance != null) {
            this.resultingPersonaIndex = indexOfVectorWithMinDistance
        }
        println(this.resultingPersonaIndex)  // check in console

        this.personaDescriptionSents = resources
                .getStringArray(R.array.persona_results_description_sents)
        // set the description of the resulted persona
        binding.personaDescription.text =
                assemblePersonaDescription(this.resultingPersonaIndex, this.personaDescriptionSents)


        binding.continueForkingButton.setOnClickListener {
            // if persona fit is mediocre or worse, show persona adjustment
            if(binding.matchDegreeSeekbar.progress <= 2)
                // goto persona adjustment fragment
                activity?.supportFragmentManager?.commit {
                    replace(R.id.dqNavHostFragment, PersonaAdjustmentFragment())
                }

            else{  // save assigned persona and go to end of intro (survey)
                val sharedPref = this.activity
                        ?.getSharedPreferences(resources
                                .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE)
                with(sharedPref?.edit()){
                    this?.putInt(PERSONA_KEY, resultingPersonaIndex+1)  // 1-9
                    this?.putInt(PERSONA_FIT_KEY, binding.matchDegreeSeekbar.progress+1)
                    this?.putBoolean(OVERWRITE_KEY, false)
                    this?.apply()
                }

                activity?.supportFragmentManager?.commit {
                    replace(R.id.dqNavHostFragment, IntroFinalFragment())
                }
            }
        }

        // update progress bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            this.activity?.findViewById<ProgressBar>(R.id.surveyProgressBar)
                ?.setProgress(70, true)
        else
            this.activity?.findViewById<ProgressBar>(R.id.surveyProgressBar)?.progress = 70

        return binding.root
    }


    /**
     * Composes the persona description, depending on the resulted persona.
     * @param personaIndex: index of resulted persona, between 0 and persona amount - 1
     * @param descrSents: the description sentences (previously assigned string-array)
     * @return description string to be shown for persona
     */
    fun assemblePersonaDescription(personaIndex: Int, descrSents: Array<String>): String {

        /* Set indices of description points found in the string-array,
            see R.array.persona_results_description_sents.
            Adjust this when changing personas / descriptions
        */
        val descriptionSentsIndices: Array<Int> = when(personaIndex) {
            0 -> arrayOf(0, 3, 7, 9, 12, 15, 17, 20, 22)
            1 -> arrayOf(0, 6, 7, 10, 12, 15, 18, 20, 23)
            2 -> arrayOf(0, 4, 7, 10, 12, 15, 18, 20, 22)
            3 -> arrayOf(2, 6, 8, 10, 12, 16, 19, 20, 23)
            4 -> arrayOf(2, 5, 7, 11, 13, 16, 19, 20, 22)
            5 -> arrayOf(2, 4, 7, 10, 14, 16, 19, 20, 21)
            6 -> arrayOf(1, 3, 7, 9, 12, 15, 18, 20, 21)
            7 -> arrayOf(-1, 6, 7, 11, 12, 15, 18, 20, 22)
            8 -> arrayOf(1, 4, 7, 10, 12, 15, 18, 20, 22)
            else -> throw IllegalArgumentException("Persona index must be between 0 and 8!")
        }

        // build description text
        val description = StringBuilder()
        description.append("Persona Nr. ${personaIndex+1} \n\n")
        for(sentInd in descriptionSentsIndices){
            if(sentInd >= 0){  // ignore 'empty' sentences (example: persona nr. 8)
                description.append("\u2022 ")
                description.append(descrSents[sentInd])
                description.append("\n\n")
            }
        }

        return description.toString()
    }


    /**
     * Computes the Euclidean distance (aka L2 norm) between two (integer) vectors of same length.
     * @param v1: an array of type Int, same length as v2
     * @param v2: an array of type Int, same length as v1
     * @return the Euclidean distance between v1 and v2 as Double value
     */
    private fun calculateEuclideanDistance(v1: Array<Int>, v2: Array<Int>): Double {
        if (BuildConfig.DEBUG && v1.size != v2.size) {
            error("Assertion failed")
        }  // must be same dim

        var squaresSum = 0
        for(i in v1.indices) squaresSum += (v1[i]-v2[i])*(v1[i]-v2[i])  // square of difference

        return sqrt(squaresSum.toDouble())
    }


    /**
     * Builds the persona vector from the persona question answers written to shared preferences.
     * @return the persona vector containing all persona question answers
     */
    private fun buildPersonaVector(): Array<Int> {
        val vector: Array<Int> = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
        val activitySharedPreferences = this.activity?.getSharedPreferences(
                resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE)
        // read the written answer values
        with(activitySharedPreferences?.edit()){
            for(i in vector.indices){
                val currentAnswerString = "pqa_$i"
                vector[i] = activitySharedPreferences?.getInt(currentAnswerString, 0)!!
            }
            this?.apply()
        }

        return vector
    }


    companion object {

        const val PERSONA_KEY = "assigned_persona_nr"  // key for persona (final) result
        const val PERSONA_FIT_KEY = "persona_fit"  // between 1-5
        const val OVERWRITE_KEY = "overwrite"
    }

}