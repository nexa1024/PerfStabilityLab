package com.nexa.perfstabilitylab.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nexa.perfstabilitylab.R
import com.nexa.perfstabilitylab.core.LabItem

class LabListFragment : Fragment(R.layout.fragment_lab_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rv = view.findViewById<RecyclerView>(R.id.rv_labs)
        rv.layoutManager = LinearLayoutManager(requireContext())

        val items = listOf(
            LabItem("startup", "启动优化", "冷/温/热启动、初始化治理、首帧直觉"),
            LabItem("jank", "卡顿 / Jank", "主线程阻塞、IO、锁等待、UI/GC"),
            LabItem("leak", "内存泄漏", "引用链、生命周期注销"),
            LabItem("oom", "OOM", "峰值、Bitmap、缓存上限、降级"),
            LabItem("anr", "ANR", "无响应、主线程卡死/等待"),
            LabItem("crash", "Crash", "栈+上下文、定位与闭环")
        )

        rv.adapter = LabListAdapter(items)
    }
}