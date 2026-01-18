package com.nexa.perfstabilitylab.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nexa.perfstabilitylab.MainActivity
import com.nexa.perfstabilitylab.R
import com.nexa.perfstabilitylab.core.LabRegistry

class LabListFragment : Fragment(R.layout.fragment_lab_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rv = view.findViewById<RecyclerView>(R.id.rv_labs)
        rv.layoutManager = LinearLayoutManager(requireContext())

        val items = LabRegistry.allLabs

        val adapter = LabListAdapter(items)
        adapter.onItemClickListener = { item ->
            (requireActivity() as MainActivity).openLab(item.labId)
        }
        rv.adapter = adapter
    }
}