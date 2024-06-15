package ru.mixail_akulov.a43_database_room_exercise.screens.main.tabs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.mixail_akulov.a43_database_room_exercise.model.accounts.AccountsRepository
import ru.mixail_akulov.a43_database_room_exercise.utils.share

class TabsViewModel(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    private val _showAdminTab = MutableLiveData<Boolean>()
    val showAdminTab = _showAdminTab.share()

    init {
        viewModelScope.launch {
            accountsRepository.getAccount().collect {
                _showAdminTab.value = it?.isAdmin() == true
            }
        }
    }
}