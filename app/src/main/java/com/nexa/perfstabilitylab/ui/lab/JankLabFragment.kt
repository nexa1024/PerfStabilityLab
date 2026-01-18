package com.nexa.perfstabilitylab.ui.lab

import com.nexa.perfstabilitylab.core.LabId
import com.nexa.perfstabilitylab.core.LabItem
import com.nexa.perfstabilitylab.core.LabRegistry

/**
 * 卡顿实验页面
 */
class JankLabFragment : BaseLabFragment() {
    override val labItem: LabItem get() = LabRegistry.getLabById(LabId.JANK)!!
}
