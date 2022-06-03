package com.philk7.ppaprojectapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.philk7.ppaprojectapp.databinding.FragmentPersonaShowBinding


class PersonaShowFragment : Fragment() {

    private lateinit var binding: FragmentPersonaShowBinding
    private lateinit var ab: ActionBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_persona_show, container, false
        )

        // assimilate action bar
        ab = (activity as AppCompatActivity).supportActionBar!!
        ab.setDisplayHomeAsUpEnabled(true)
        ab.title = resources.getString(R.string.home_frag1_title)

        this.setShownPersona()

        return binding.root
    }


    /**
     * Sets the two text views that show Privacy Persona: number and code.
     *  Also displays the choice of Privacy Assistant.
     */
    private fun setShownPersona() {
        // set persona info
        val sharedPref = this.activity?.getSharedPreferences(
            resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        with(sharedPref?.edit()) {
            val ppNumber = sharedPref?.getInt(PersonaResultFragment.PERSONA_KEY, -1) ?: -1
            binding.ppNumber.text = "Number $ppNumber"

            // set assistant info
            val assistantId = sharedPref?.getInt("assistant_id", 0) ?: 0
            if(assistantId in 1..3){
                binding.assistantChoice.text = resources
                    .getStringArray(R.array.assistant_choices)[assistantId-1]
                when(assistantId){
                    1 -> binding.assistantChoice.setTextColor(
                        resources.getColor(R.color.notification_assistant_color))
                    2 -> binding.assistantChoice.setTextColor(
                        resources.getColor(R.color.recommendation_assistant_color))
                    3 -> binding.assistantChoice.setTextColor(
                        resources.getColor(R.color.automatic_assistant_color))
                }
            }

            this?.apply()
        }
    }

}