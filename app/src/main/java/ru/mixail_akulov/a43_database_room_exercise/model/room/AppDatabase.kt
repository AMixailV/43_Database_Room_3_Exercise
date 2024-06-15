package ru.mixail_akulov.a43_database_room_exercise.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mixail_akulov.a43_database_room_exercise.model.accounts.room.AccountsDao
import ru.mixail_akulov.a43_database_room_exercise.model.accounts.room.entities.AccountDbEntity
import ru.mixail_akulov.a43_database_room_exercise.model.boxes.room.BoxesDao
import ru.mixail_akulov.a43_database_room_exercise.model.boxes.room.entities.AccountBoxSettingDbEntity
import ru.mixail_akulov.a43_database_room_exercise.model.boxes.room.entities.BoxDbEntity
import ru.mixail_akulov.a43_database_room_exercise.model.boxes.room.views.SettingDbView

@Database(
    version = 1,    // todo #1: install the app to your device/emulator; then increment DB version by 1: from 1 to 2.
                    // todo #13: now let's increment DB version again by 1: from 2 to 3; we will
                    //           add 'phone' column to the 'accounts' table by hands
    entities = [
        AccountDbEntity::class,
        BoxDbEntity::class,
        AccountBoxSettingDbEntity::class
    ],
    views = [
        SettingDbView::class
    ]
    // todo #2: add autoMigration argument for auto-migrating from the 1st to the 2nd DB version

    // todo #9: specify 'spec' argument in the AutoMigration and assign your spec class.
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAccountsDao(): AccountsDao

    abstract fun getBoxesDao(): BoxesDao

}