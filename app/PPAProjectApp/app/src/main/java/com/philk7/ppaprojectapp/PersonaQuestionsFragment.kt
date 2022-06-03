package com.philk7.ppaprojectapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import com.philk7.ppaprojectapp.databinding.FragmentPersonaQuestionsBinding
import com.philk7.ppaprojectapp.enums.QuestionsProgressStepEnum

class PersonaQuestionsFragment : Fragment() {
    private lateinit var binding: FragmentPersonaQuestionsBinding
    private lateinit var personaAssignmentQuestions: Array<String>
    private var currentQuestionIndex: Int = -1


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_persona_questions, container, false)

        // delay view
        /* NOTE: Always use innermost view that should be delayed,
        because AnimationUtils seems to optimize animations by using views previously
         cached, which may lead to unexpected behavior. */
        binding.personaQuestionSwitcher.startAnimation(
            AnimationUtils.loadAnimation(this.context, R.anim.show_delay)
        )

        personaAssignmentQuestions = resources.getStringArray(R.array.persona_questions)

        // set first answer (if saved)
        handlePQAValues(QuestionsProgressStepEnum.NEXT)
        this.currentQuestionIndex++  // now 0

        binding.previousButton.setOnClickListener {
            // reset next button color
            if(this.currentQuestionIndex == personaAssignmentQuestions.size - 1){
                binding.nextButton.setBackgroundColor(resources.getColor(R.color.accent_color))
                binding.invalidateAll()
            }

            handlePQAValues(QuestionsProgressStepEnum.PREVIOUS)
            changeCurrentQuestion(QuestionsProgressStepEnum.PREVIOUS)
        }

        binding.nextButton.setOnClickListener {

            handlePQAValues(QuestionsProgressStepEnum.NEXT)  // save answer of each step immediately

            // change button color manually for last question
            if(this.currentQuestionIndex == personaAssignmentQuestions.size - 2){
                binding.nextButton.setBackgroundColor(resources.getColor(R.color.accent_color))
                binding.invalidateAll()
            }

            if (this.currentQuestionIndex < personaAssignmentQuestions.size - 1)
            // when pressing 'next' in last questions, continue to persona result fragment
                changeCurrentQuestion(QuestionsProgressStepEnum.NEXT)
            else activity?.supportFragmentManager?.commit {
                // show (suggested) persona result
                replace(R.id.dqNavHostFragment, PersonaResultFragment())
            }
        }

        binding.personaQuestionSwitcher.setCurrentText(personaAssignmentQuestions[0])
        binding.personaQuestionSwitcher.setInAnimation(context, android.R.anim.slide_in_left)
        binding.previousButton.visibility = View.INVISIBLE

        // update progress bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            this.activity?.findViewById<ProgressBar>(R.id.surveyProgressBar)
                ?.setProgress(55, true)
        else
            this.activity?.findViewById<ProgressBar>(R.id.surveyProgressBar)?.progress = 55

        return binding.root
    }


    /**
     * Swaps out the current persona assignment question.
     *  Also updates the current question index and sets visibility.
     * @param type: which kind of questions progress
     */
    private fun changeCurrentQuestion(type: QuestionsProgressStepEnum) {
        this.currentQuestionIndex = currentQuestionIndex + type.prog  // variant
        val setQuestion = this.personaAssignmentQuestions[currentQuestionIndex]
        binding.personaQuestionSwitcher.setText(setQuestion)  // switch text
        // manage previous button visibility
        if (currentQuestionIndex == 0) binding.previousButton.visibility = View.INVISIBLE
        else if (currentQuestionIndex == 1) binding.previousButton.visibility = View.VISIBLE

        binding.invalidateAll()  // update view
    }


    /**
     * Writes and reads persona question answers to/from shared preferences.
     *  Sets the seekbar appropriately.
     * @param type: either 'PREVIOUS' or 'NEXT'
     */
    private fun handlePQAValues(type: QuestionsProgressStepEnum) {
        val activitySharedPreferences = this.activity?.getSharedPreferences(
                resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE)
        val currentQuestionAnswerKey: String = "pqa_" + this.currentQuestionIndex

        // get key depending on which question is 'next' (as in previous or next question)
        val nextQuestionAnswerKey: String = if (type == QuestionsProgressStepEnum.PREVIOUS)
            "pqa_" + (this.currentQuestionIndex - 1)
        else "pqa_" + (this.currentQuestionIndex + 1)

        with(activitySharedPreferences?.edit()) {
            val nextQuestionAnswer: Int? = activitySharedPreferences
                    ?.getInt(nextQuestionAnswerKey, 0)
            // save answer of current question
            this?.putInt(currentQuestionAnswerKey, binding.personaQuestionSeekbar.progress + 1)
            // set scala appropriately
            if (type == QuestionsProgressStepEnum.PREVIOUS)
                binding.personaQuestionSeekbar.max =
                        (resources.getIntArray(
                                R.array.persona_questions_scalas)[currentQuestionIndex - 1] - 1)
            else if (currentQuestionIndex + 1 < personaAssignmentQuestions.size)
                binding.personaQuestionSeekbar.max = (resources.getIntArray(
                        R.array.persona_questions_scalas)[currentQuestionIndex + 1] - 1)

            // set 'next' question answer (animation requires API min 24)
            if (nextQuestionAnswer != null) {  // set next question seekbar progress
                if (Build.VERSION.SDK_INT < 24)
                    binding.personaQuestionSeekbar.progress = nextQuestionAnswer - 1
                else
                    binding.personaQuestionSeekbar
                            .setProgress(nextQuestionAnswer - 1, true)
            }
            this?.apply()
        }
    }


}