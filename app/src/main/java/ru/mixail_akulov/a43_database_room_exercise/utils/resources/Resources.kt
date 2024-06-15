package ru.mixail_akulov.a43_database_room_exercise.utils.resources

import androidx.annotation.StringRes

interface Resources {
    fun getString(@StringRes stringRes: Int): String
}