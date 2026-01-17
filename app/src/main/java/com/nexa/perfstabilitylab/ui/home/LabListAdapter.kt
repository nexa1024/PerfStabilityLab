package com.nexa.perfstabilitylab.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexa.perfstabilitylab.R
import com.nexa.perfstabilitylab.core.LabItem

class LabListAdapter(
    private val items: List<LabItem>
) : RecyclerView.Adapter<LabListAdapter.VH>() {

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val desc: TextView = itemView.findViewById(R.id.tv_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_lab, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.desc.text = item.desc
    }

    override fun getItemCount(): Int = items.size
}