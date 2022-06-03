package com.philk7.ppaprojectapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.philk7.ppaprojectapp.databinding.FragmentSharedPrefViewerBinding


class SharedPrefViewerFragment : Fragment() {
    private lateinit var binding: FragmentSharedPrefViewerBinding


    // start of adapter classes

    /**
     * class for key-value pairs in sharedPref
     * @param key: a key in shared preferences
     * @param value: the value that belongs to the key, is of type String, Boolean, Int ...
     */
    data class SharedPrefValue(val key: String, val value: Any?)

    // the recycler view adapter
    class SharedPrefAdapter(private val mPairs: List<SharedPrefValue>):
        RecyclerView.Adapter<SharedPrefAdapter.ViewHolder>() {

        // access view elements
        inner class ViewHolder(pairItemView: View): RecyclerView.ViewHolder(pairItemView) {
            val keyView: TextView = pairItemView.findViewById(R.id.keyNameField)
            val valueView: TextView = pairItemView.findViewById(R.id.valueField)
        }

        // build view holder
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val parContext = parent.context
            val inflater = LayoutInflater.from(parContext)
            val pairView = inflater.inflate(R.layout.item_spkv, parent, false)

            return ViewHolder(pairView)
        }

        // fill the layout's content
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val property = this.mPairs[position]
            holder.keyView.text = property.key
            holder.valueView.text = property.value.toString()
        }

        override fun getItemCount(): Int {
            return mPairs.size
        }
    }

    // end of adapter classes


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shared_pref_viewer,
            container, false)

        // adjust action bar
        val ab =(activity as AppCompatActivity).supportActionBar!!
        ab.setDisplayHomeAsUpEnabled(true)
        ab.title = resources.getString(R.string.home_frag5_title)

        // fill content from sharedPref
        val sharedPref = this.activity?.getSharedPreferences(
            resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE)
        val properties = sharedPref?.all
        var pairs = mutableListOf<SharedPrefValue>()
        for(k in properties?.keys!!) pairs.add(SharedPrefValue(k, properties[k]))
        pairs = pairs.sortedBy { it.key } as MutableList<SharedPrefValue>

        val adapter = SharedPrefAdapter(pairs)
        val rv = binding.spRecycler
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this.requireContext())

        return binding.root
    }

}