package ru.mixail_akulov.a43_database_room_exercise.model.boxes.room

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import ru.mixail_akulov.a43_database_room_exercise.model.AuthException
import ru.mixail_akulov.a43_database_room_exercise.model.accounts.AccountsRepository
import ru.mixail_akulov.a43_database_room_exercise.model.boxes.BoxesRepository
import ru.mixail_akulov.a43_database_room_exercise.model.boxes.entities.Box
import ru.mixail_akulov.a43_database_room_exercise.model.boxes.entities.BoxAndSettings
import ru.mixail_akulov.a43_database_room_exercise.model.boxes.room.entities.AccountBoxSettingDbEntity
import ru.mixail_akulov.a43_database_room_exercise.model.boxes.room.entities.SettingsTuple
import ru.mixail_akulov.a43_database_room_exercise.model.room.wrapSQLiteException

class RoomBoxesRepository(
    private val accountsRepository: AccountsRepository,
    private val boxesDao: BoxesDao,
    private val ioDispatcher: CoroutineDispatcher
) : BoxesRepository {

    override suspend fun getBoxesAndSettings(onlyActive: Boolean): Flow<List<BoxAndSettings>> {
        return accountsRepository.getAccount()
            .flatMapLatest { account ->
                if (account == null) return@flatMapLatest flowOf(emptyList())
                queryBoxesAndSettings(account.id)
            }
            .mapLatest { boxAndSettings ->
                if (onlyActive) {
                    boxAndSettings.filter { it.isActive }
                } else {
                    boxAndSettings
                }
            }
    }

    override suspend fun activateBox(box: Box) = wrapSQLiteException(ioDispatcher) {
        setActiveFlagForBox(box, true)
    }

    override suspend fun deactivateBox(box: Box) = wrapSQLiteException(ioDispatcher) {
        setActiveFlagForBox(box, false)
    }

    private fun queryBoxesAndSettings(accountId: Long): Flow<List<BoxAndSettings>> {
        return boxesDao.getBoxesAndSettings(accountId)
            .map { entities ->
                entities.map {
                    val boxEntity = it.boxDbEntity
                    val settingEntity = it.settingDbEntity
                    BoxAndSettings(
                        box = boxEntity.toBox(),
                        isActive = settingEntity.settings.isActive
                    )
                }
            }
    }

    private suspend fun setActiveFlagForBox(box: Box, isActive: Boolean) {
        val account = accountsRepository.getAccount().first() ?: throw AuthException()
        boxesDao.setActiveFlagForBox(
            AccountBoxSettingDbEntity(
                accountId = account.id,
                boxId = box.id,
                settings = SettingsTuple(isActive = isActive)
            )
        )
    }
}