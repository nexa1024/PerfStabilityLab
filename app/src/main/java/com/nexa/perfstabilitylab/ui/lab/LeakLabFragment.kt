package com.nexa.perfstabilitylab.ui.lab

import com.nexa.perfstabilitylab.core.LabId
import com.nexa.perfstabilitylab.core.LabItem
import com.nexa.perfstabilitylab.core.LabRegistry

/**
 * 内存泄漏实验页面
 */
class LeakLabFragment : BaseLabFragment() {
    override val labItem: LabItem get() = LabRegistry.getLabById(LabId.LEAK)!!
}
