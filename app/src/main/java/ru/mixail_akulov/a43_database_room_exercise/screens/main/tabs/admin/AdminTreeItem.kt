package ru.mixail_akulov.a43_database_room_exercise.screens.main.tabs.admin

enum class ExpansionStatus {
    EXPANDED,
    COLLAPSED,
    NOT_EXPANDABLE
}

data class AdminTreeItem(
    val id: Long,
    val level: Int,
    val expansionStatus: ExpansionStatus,
    val attributes: Map<String, String>
)