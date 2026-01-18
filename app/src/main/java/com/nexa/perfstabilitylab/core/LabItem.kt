package com.nexa.perfstabilitylab.core

data class LabItem(
    val labId: LabId,
    val title: String,
    val desc: String,
    val tags: List<String> = emptyList()
) {
    val id: String get() = labId.id
}