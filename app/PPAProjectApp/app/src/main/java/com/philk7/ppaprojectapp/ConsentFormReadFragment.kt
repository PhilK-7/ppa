package com.philk7.ppaprojectapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class ConsentFormReadFragment : Fragment() {
    private lateinit var ab: ActionBar


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // assimilate action bar
        ab = (activity as AppCompatActivity).supportActionBar!!
        ab.setDisplayHomeAsUpEnabled(true)
        ab.title = resources.getString(R.string.home_frag2_title)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consent_form_read, container, false)
    }

}