package com.nexa.perfstabilitylab.core

/**
 * 实验ID枚举，用于类型安全地标识每个实验
 */
enum class LabId(val id: String) {
    STARTUP("startup"),
    JANK("jank"),
    LEAK("leak"),
    OOM("oom"),
    ANR("anr"),
    CRASH("crash");

    companion object {
        fun fromId(id: String): LabId? {
            return values().find { it.id == id }
        }
    }
}
