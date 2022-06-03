package com.philk7.ppaprojectapp

import android.content.Context
import android.os.Build.VERSION
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.philk7.ppaprojectapp.databinding.FragmentLikertQuestionsBinding
import com.philk7.ppaprojectapp.enums.QuestionsProgressStepEnum


class LikertQuestionsFragment : Fragment() {

    private lateinit var binding: FragmentLikertQuestionsBinding

    data class Question(var sent: String, var num: Int)

    private lateinit var currentQuestion: Question
    private lateinit var answers: MutableList<Int>  // save answers (Likert scala 1-6)


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment, use data binding
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_likert_questions, container, false)

        // initialize question logic members
        currentQuestion = Question(resources.getStringArray(R.array.likert_questions)[0], 1)
        binding.question = currentQuestion  // register instance for data binding

        answers = MutableList(resources
                .getStringArray(R.array.likert_questions).size) { -1 }

        binding.nextButton.setOnClickListener {
            // likert question amount can be varied, because events/parameters depend on array size

            // set different button color manually when on last question
            if(this.currentQuestion.num ==
                resources.getStringArray(R.array.likert_questions).size - 1){
                binding.nextButton.setBackgroundColor(resources.getColor(R.color.dark_primary_color))
                binding.invalidateAll()
            }

            // go to next question
            if (this.currentQuestion.num < resources.getStringArray(R.array.likert_questions).size)
                gotoPreviousOrNextQuestion(QuestionsProgressStepEnum.NEXT)
            else {  // last question answered

                // save answer for last Likert question
                this.answers[currentQuestion.num - 1] = binding.seekBar.progress
                // save this question section's answers
                val answerVectorSb = StringBuilder()
                for (answer in this.answers) answerVectorSb.append(answer + 1)

                // write likert questions answer vector as string to shared preferences
                val sharedPref = this.activity?.getSharedPreferences(
                        resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE)
                with(sharedPref?.edit()) {
                    this?.putString(LIKERT_ANSWERS_KEY, answerVectorSb.toString())
                    this?.apply()
                }

                // goto demographic questions
                activity?.supportFragmentManager?.commit {
                    replace(R.id.dqNavHostFragment, PersonQuestionsFragment())
                }

            }

        }
        binding.previousButton.setOnClickListener {
            // reset next button color
            if(this.currentQuestion.num ==
                resources.getStringArray(R.array.likert_questions).size){
                binding.nextButton.setBackgroundColor(resources.getColor(R.color.accent_color))
                binding.invalidateAll()
            }

            if (this.currentQuestion.num > 1)
                gotoPreviousOrNextQuestion(QuestionsProgressStepEnum.PREVIOUS)
        }
        binding.previousButton.visibility = View.INVISIBLE

        return binding.root
    }


    /**
     * Updates state to proceed to the previous / next question of the fragment.
     *  Saves the answer (Likert scale) of the current question,
     *  sets the seekbar progress to the current value of the question to switch to,
     *  and also updates the question itself.
     * @param questionsProgress: enum type that represents which type of question progression
     */
    private fun gotoPreviousOrNextQuestion(questionsProgress: QuestionsProgressStepEnum) {

        this.answers[currentQuestion.num - 1] = binding.seekBar.progress  // save current answer
        currentQuestion.num = currentQuestion.num + questionsProgress.prog  // variant

        // set seekbar state to saved value
        if (VERSION.SDK_INT < 24)  // animation available for API 24+
            binding.seekBar.progress = this.answers[currentQuestion.num - 1]
        else binding.seekBar.setProgress(this
                .answers[currentQuestion.num - 1], true)

        // handle previous / back button visibilities
        if (currentQuestion.num == 1) binding.previousButton.visibility = View.INVISIBLE
        else if (binding.previousButton.visibility == View.INVISIBLE)
            binding.previousButton.visibility = View.VISIBLE

        // set correct question
        this.currentQuestion.sent =
                resources.getStringArray(R.array.likert_questions)[currentQuestion.num - 1]
        binding.invalidateAll()  // update view
    }


    companion object {

        const val LIKERT_ANSWERS_KEY = "likert_answers"
    }

}