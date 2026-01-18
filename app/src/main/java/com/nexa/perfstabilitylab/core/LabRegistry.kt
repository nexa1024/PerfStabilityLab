package com.nexa.perfstabilitylab.core

/**
 * 实验注册表，管理所有可用的实验项
 */
object LabRegistry {

    val allLabs: List<LabItem> = listOf(
        LabItem(
            labId = LabId.STARTUP,
            title = "启动优化",
            desc = "冷/温/热启动、初始化治理、首帧直觉",
            tags = listOf("冷启动", "温启动", "热启动", "首帧", "初始化")
        ),
        LabItem(
            labId = LabId.JANK,
            title = "卡顿 / Jank",
            desc = "主线程阻塞、IO、锁等待、UI/GC",
            tags = listOf("主线程", "IO", "锁", "布局", "GC")
        ),
        LabItem(
            labId = LabId.LEAK,
            title = "内存泄漏",
            desc = "引用链、生命周期注销",
            tags = listOf("引用链", "生命周期", "LeakCanary")
        ),
        LabItem(
            labId = LabId.OOM,
            title = "OOM",
            desc = "峰值、Bitmap、缓存上限、降级",
            tags = listOf("Bitmap", "缓存", "内存抖动", "降级")
        ),
        LabItem(
            labId = LabId.ANR,
            title = "ANR",
            desc = "无响应、主线程卡死/等待",
            tags = listOf("主线程", "锁", "Binder", "Trace")
        ),
        LabItem(
            labId = LabId.CRASH,
            title = "Crash",
            desc = "栈+上下文、定位与闭环",
            tags = listOf("异常", "日志", "混淆", "监控")
        )
    )

    fun getLabById(id: LabId): LabItem? {
        return allLabs.find { it.labId == id }
    }

    fun getLabById(id: String): LabItem? {
        return LabId.fromId(id)?.let { getLabById(it) }
    }
}
