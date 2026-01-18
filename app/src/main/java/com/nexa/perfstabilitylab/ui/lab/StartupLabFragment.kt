package com.nexa.perfstabilitylab.ui.lab

import com.nexa.perfstabilitylab.core.LabId
import com.nexa.perfstabilitylab.core.LabItem
import com.nexa.perfstabilitylab.core.LabRegistry

/**
 * 启动优化实验页面
 */
class StartupLabFragment : BaseLabFragment() {
    override val labItem: LabItem get() = LabRegistry.getLabById(LabId.STARTUP)!!
}
