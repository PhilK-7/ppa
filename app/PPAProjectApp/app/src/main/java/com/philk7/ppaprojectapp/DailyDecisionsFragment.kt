package com.philk7.ppaprojectapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.philk7.ppaprojectapp.databinding.FragmentDailyDecisionsBinding


class DailyDecisionsFragment : DecisionPanelFragment() {

    private lateinit var binding: FragmentDailyDecisionsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_daily_decisions,
            container, false)

        // set action bar
        val ab = (activity as AppCompatActivity).supportActionBar!!
        ab.title = "Today's Decisions"
        ab.setHomeAsUpIndicator(R.drawable.ic_home)

        // get sharedPref and set globally
        val sp = this.activity?.getSharedPreferences(
            resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        if (sp != null) {
            this.sharedPref = sp
        }

        // get and set decisions, and also amount for header: uses adapter from decision panel
        val dl = this.setupDecisionCardRecycler(binding.todayDecisionsRecycler, true)
        if (dl != null) {
            binding.todayDecisionAmount.text = "${dl.size}"
        }

        return binding.root
    }

    // go to home menu again when pressing back
    override fun onPause() {
        super.onPause()
        val homeIntent = Intent(this.requireContext(), HomeActivity::class.java)
        startActivity(homeIntent)
    }
}