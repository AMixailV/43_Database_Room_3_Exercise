package ru.mixail_akulov.a43_database_room_exercise.model.accounts.entities

import ru.mixail_akulov.a43_database_room_exercise.model.boxes.entities.BoxAndSettings

/**
 * Account info with all boxes and their settings
 */
data class AccountFullData(
    val account: Account,
    val boxesAndSettings: List<BoxAndSettings>
)