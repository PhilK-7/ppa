package com.philk7.ppaprojectapp

import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.text.bold
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.philk7.ppaprojectapp.databinding.FragmentIotExplainBinding


class IotExplainFragment : Fragment() {

    private lateinit var binding: FragmentIotExplainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_iot_explain,
            container, false)

        binding.ioteLl.startAnimation(
            AnimationUtils.loadAnimation(this.context, R.anim.show_delay)
        )

        val sb = SpannableStringBuilder()
            .append(resources.getString(R.string.iot_explanation_text_part1))
            .append(" ")
            .bold { append(resources.getString(R.string.iot_explanation_bold_part)) }
            .append(resources.getString(R.string.iot_explanation_text_part2))
        binding.iotExplanation.text = sb

        binding.proceedReadButton.setOnClickListener {
            if(!binding.iotReadConfirm.isChecked){
                val t = Toast.makeText(this.requireContext(),
                    "Please read the IoT explanation first!", Toast.LENGTH_SHORT)
                t.show()
            }
            else {
                // go to iot questions
                this.activity?.supportFragmentManager?.commit {
                    replace(R.id.dqNavHostFragment, IotQuestionsFragment())
                }
            }
        }

        // update progress bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            this.activity?.findViewById<ProgressBar>(R.id.surveyProgressBar)
                ?.setProgress(45, true)
        else
            this.activity?.findViewById<ProgressBar>(R.id.surveyProgressBar)?.progress = 45

        return binding.root
    }

}