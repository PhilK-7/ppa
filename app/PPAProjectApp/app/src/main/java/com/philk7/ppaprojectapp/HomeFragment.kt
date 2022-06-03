package com.philk7.ppaprojectapp

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.philk7.ppaprojectapp.databinding.FragmentHomeBinding


private lateinit var ab: ActionBar


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        // setup action bar state
        ab = (activity as AppCompatActivity).supportActionBar!!
        ab.setDisplayHomeAsUpEnabled(false)
        // set color
        ab.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.accent_color)))


        // on-click listeners: go to the respective fragments
        binding.cfCard.setOnClickListener {
            addHomeToStack(ConsentFormReadFragment())
        }

        binding.ppCard.setOnClickListener {
            addHomeToStack(PersonaShowFragment())
        }

        binding.decisionCard.setOnClickListener {
            addHomeToStack(DecisionPanelFragment())
        }

        binding.dmCard.setOnClickListener {
            addHomeToStack(DevModeFragment())
        }

        binding.mvCard.setOnClickListener {
            addHomeToStack(MovementTestingFragment())
        }

        binding.spCard.setOnClickListener {
            addHomeToStack(SharedPrefViewerFragment())
        }


        // for dev TESTING; disable for production build
        if (SHOW_HIDDEN_CARDS){
            binding.dmCard.visibility = View.VISIBLE
            binding.mvCard.visibility = View.VISIBLE
            binding.spCard.visibility = View.VISIBLE
        }

        return binding.root
    }

    // go to the fragment
    private fun addHomeToStack(fragment: Fragment) {
        activity?.supportFragmentManager?.commit {
            addToBackStack(null)
            setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
            replace(R.id.homeNavHostFragment, fragment)
        }
    }


    companion object {

        const val SHOW_HIDDEN_CARDS = true  // enable to show hidden cards
    }
}