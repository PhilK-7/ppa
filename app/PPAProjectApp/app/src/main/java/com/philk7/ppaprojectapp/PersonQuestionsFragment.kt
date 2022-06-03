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
import com.philk7.ppaprojectapp.databinding.FragmentPersonQuestionsBinding

class PersonQuestionsFragment : Fragment() {

    private lateinit var binding: FragmentPersonQuestionsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_person_questions, container, false)

        // delay view
        binding.linLay.startAnimation(
            AnimationUtils.loadAnimation(this.context, R.anim.show_delay)
        )

        // show / hide last EditText accordingly
        binding.iotHeardButton.setOnClickListener {
            binding.howIotExplanation.visibility = View.VISIBLE
        }
        binding.iotNotheardButton.setOnClickListener {
            binding.howIotExplanation.visibility = View.INVISIBLE
        }

        binding.nextQuestionSectionButton.setOnClickListener {
            if (!validateForm() && !IGNORE_INFO_UPDATE) {  // form validation
                Toast.makeText(
                    this.context,
                    "Please answer all questions!", Toast.LENGTH_SHORT
                ).show()

            } else {  // valid form; or ignore mode
                if (!IGNORE_INFO_UPDATE)  // valid form data that is saved
                    saveDemoInfo()  // save form information in sharedPref

                // goto next fragment
                activity?.supportFragmentManager?.commit {
                    replace(R.id.dqNavHostFragment, IotExplainFragment())
                }
            }
        }

        // update progress bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            this.activity?.findViewById<ProgressBar>(R.id.surveyProgressBar)
                ?.setProgress(20, true)
        else
            this.activity?.findViewById<ProgressBar>(R.id.surveyProgressBar)?.progress = 20

        return binding.root
    }


    /**
     * Validates the person questions form by checking that all needed answers are given.
     * @return true if everything (needed) is answered, else false
     */
    private fun validateForm(): Boolean {

        // check for each radio group that one radio button is checked
        val genderChosen = listOf(
            binding.gqR1.isChecked, binding.gqR2.isChecked,
            binding.gqR3.isChecked, binding.gqR4.isChecked
        ).reduce { acc, b -> acc || b }
        // also, for specific radio buttons, respective text field must be filled
        var customGender = true
        if (binding.gqR3.isChecked) customGender = binding.customGenderText.text.isNotEmpty()

        // non-optional text fields must be answered
        val ageChosen = binding.ageEditField.text.isNotEmpty()
        val countryChosen = binding.countryTextField.text.isNotEmpty()

        val employmentChosen = listOf(
            binding.eqR1.isChecked,
            binding.eqR2.isChecked,
            binding.eqR3.isChecked,
            binding.eqR4.isChecked,
            binding.eqR5.isChecked,
            binding.eqR6.isChecked,
            binding.eqR7.isChecked,
            binding.eqR8.isChecked,
            binding.eqR9.isChecked,
            binding.eqR10.isChecked,
            binding.eqR11.isChecked
        ).reduce { acc, b -> acc || b }
        var customEmployment = true
        if (binding.eqR10.isChecked)
            customEmployment = binding.employmentOtherField.text.isNotEmpty()

        val internetUsage = listOf(
            binding.iqR1.isChecked,
            binding.iqR2.isChecked,
            binding.iqR3.isChecked,
            binding.iqR4.isChecked
        ).reduce { acc, b -> acc || b }

        val iotHeard = binding.iotHeardButton.isChecked || binding.iotNotheardButton.isChecked
        var iotHeardHow = true
        if (binding.iotHeardButton.isChecked)
            iotHeardHow = binding.howIotExplanation.text.isNotEmpty()

        // check that all conditions satisfied
        return genderChosen && customGender && ageChosen && countryChosen && employmentChosen
                && customEmployment && internetUsage && iotHeard && iotHeardHow
    }


    /**
     * Saves all information of the complete, valid person questions formular to sharedPref.
     */
    private fun saveDemoInfo() {
        val sharedPref = this.activity?.getSharedPreferences(
            resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        with(sharedPref?.edit()) {

            // write gender
            val gender = when (listOf(
                binding.gqR1.isChecked, binding.gqR2.isChecked,
                binding.gqR3.isChecked, binding.gqR4.isChecked
            ).indexOf(true)) {
                0 -> "female"
                1 -> "male"
                2 -> binding.customGenderText.text.toString()
                3 -> "unspecified"
                else ->
                    throw IllegalArgumentException("checkedRadioButtonId is not in correct range!")
            }
            this?.putString(GENDER_KEY, gender)

            // write age
            val age = Integer.parseInt(binding.ageEditField.text.toString())
            this?.putInt(AGE_KEY, age)

            // write country
            val country = binding.countryTextField.text.toString()
            this?.putString(COUNTRY_KEY, country)

            val employment = when (listOf(
                binding.eqR1.isChecked,
                binding.eqR2.isChecked,
                binding.eqR3.isChecked,
                binding.eqR4.isChecked,
                binding.eqR5.isChecked,
                binding.eqR6.isChecked,
                binding.eqR7.isChecked,
                binding.eqR8.isChecked,
                binding.eqR9.isChecked,
                binding.eqR10.isChecked,
                binding.eqR11.isChecked
            ).indexOf(true)) {
                0 -> "full-time"
                1 -> "part-time"
                2 -> "unemployed & in search"
                3 -> "unemployed & not in search"
                4 -> "student"
                5 -> "retired"
                6 -> "house man / wife"
                7 -> "self-employed"
                8 -> "unfit for work"
                9 -> binding.employmentOtherField.text.toString()
                10 -> "unspecified"
                else ->
                    throw IllegalArgumentException("checkedRadioButtonId is not in correct range!")
            }
            this?.putString(EMPLOYMENT_KEY, employment)

            // write internet usage
            val inetUsg = when (listOf(
                binding.iqR1.isChecked,
                binding.iqR2.isChecked,
                binding.iqR3.isChecked,
                binding.iqR4.isChecked
            ).indexOf(true)) {
                0 -> "daily"  // is 1 in SQL table
                1 -> "every other day"  // 2
                2 -> "once a week"  // 3
                3 -> "less than once a week"  // 4
                else ->
                    throw IllegalArgumentException("checkedRadioButtonId is not in correct range!")
            }
            this?.putString(USAGE_KEY, inetUsg)

            // write first IoT info
            val iotq = when (listOf(binding.iotHeardButton.isChecked,
                binding.iotNotheardButton.isChecked).indexOf(
                true
            )) {
                0 -> true
                1 -> false
                else ->
                    throw IllegalArgumentException("checkedRadioButtonId is not in correct range!")
            }
            this?.putBoolean(IOTQ_KEY, iotq)
            val iotqe = binding.howIotExplanation.text.toString()
            this?.putString(IOTQE_KEY, iotqe)

            this?.apply()
        }

        println("Person questions information written to sharedPref.")
    }


    companion object {

        // keys for person question answers
        const val GENDER_KEY = "gender"
        const val AGE_KEY = "age"
        const val COUNTRY_KEY = "country"
        const val EMPLOYMENT_KEY = "empl"
        const val USAGE_KEY = "usage"
        const val IOTQ_KEY = "iot_heard"
        const val IOTQE_KEY = "iot_heard_how"

        // configuration
        /*
        Set whether the last entered data should be ignored. Then the form input is neither
        checked (because then unnecessary) nor saved. Hence, the values in sharedPref are also not
        manipulated.
        Set to true for just showing the fragment / quickly going through the survey.
        For actual production build, set to false.
         */
        const val IGNORE_INFO_UPDATE = false
    }

}