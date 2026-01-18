package com.nexa.perfstabilitylab.ui.lab

import com.nexa.perfstabilitylab.core.LabId
import com.nexa.perfstabilitylab.core.LabItem
import com.nexa.perfstabilitylab.core.LabRegistry

/**
 * Crash实验页面
 */
class CrashLabFragment : BaseLabFragment() {
    override val labItem: LabItem get() = LabRegistry.getLabById(LabId.CRASH)!!
}
