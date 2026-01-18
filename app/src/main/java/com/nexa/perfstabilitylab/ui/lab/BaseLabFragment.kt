package com.nexa.perfstabilitylab.ui.lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.nexa.perfstabilitylab.R
import com.nexa.perfstabilitylab.core.LabItem

/**
 * 实验页面基类
 */
abstract class BaseLabFragment : Fragment() {

    protected abstract val labItem: LabItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<android.widget.TextView>(R.id.tv_lab_title).text = labItem.title
        view.findViewById<android.widget.TextView>(R.id.tv_lab_desc).text = labItem.desc

        val chipGroup = view.findViewById<com.google.android.material.chip.ChipGroup>(R.id.cg_tags)
        labItem.tags.forEach { tag ->
            val chip = Chip(requireContext()).apply {
                text = tag
                isClickable = false
                chipBackgroundColor = null
            }
            chipGroup.addView(chip)
        }
    }
}
