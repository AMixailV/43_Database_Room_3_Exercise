package ru.mixail_akulov.a43_database_room_exercise.screens.main.tabs.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.mixail_akulov.a43_database_room_exercise.model.boxes.BoxesRepository
import ru.mixail_akulov.a43_database_room_exercise.model.boxes.entities.Box
import ru.mixail_akulov.a43_database_room_exercise.utils.share

class DashboardViewModel(
    private val boxesRepository: BoxesRepository
) : ViewModel() {

    private val _boxes = MutableLiveData<List<Box>>()
    val boxes = _boxes.share()

    init {
        viewModelScope.launch {
            boxesRepository.getBoxesAndSettings(onlyActive = true).collect { list ->
                _boxes.value = list.map { it.box }
            }
        }
    }

}