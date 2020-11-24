package com.example.fitnesstracker.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.fitnesstracker.repositories.MainRepository
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.database.Run
import com.example.fitnesstracker.other.SortType
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
): ViewModel() {

    private val runsSortedByDate = mainRepository.getAllRunSortedByDate()
    private val runsSortedByAvgSpeed = mainRepository.getAllRunSortedByAvgSpeed()
    private val runsSortedByDistance = mainRepository.getAllRunSortedByDistance()
    private val runsSortedByCaloriesBurned = mainRepository.getAllRunSortedByCaloriesBurned()
    private val runsSortedByTimeInMillis = mainRepository.getAllRunSortedByTimeInMillis()

    val runs = MediatorLiveData<List<Run>>()

    var sortType = SortType.DATE

    init {
        runs.addSource(runsSortedByDate) {result ->
            if (sortType == SortType.DATE) { result?.let {runs.value = it }
            }
        }
        runs.addSource(runsSortedByDistance) {result ->
            if (sortType == SortType.DISTANCE) { result?.let {runs.value = it }
            }
        }
        runs.addSource(runsSortedByAvgSpeed) {result ->
            if (sortType == SortType.AVG_SPEED) { result?.let {runs.value = it }
            }
        }
        runs.addSource(runsSortedByCaloriesBurned) {result ->
            if (sortType == SortType.CALORIES_BURNED) { result?.let {runs.value = it }
            }
        }
        runs.addSource(runsSortedByTimeInMillis) {result ->
            if (sortType == SortType.RUNNING_TIME) { result?.let {runs.value = it }
            }
        }
    }

    fun sortRuns(sortType: SortType) = when(sortType) {
        SortType.DATE -> runsSortedByDate.value?.let { runs.value = it }
        SortType.DISTANCE -> runsSortedByDistance.value?.let { runs.value = it }
        SortType.AVG_SPEED -> runsSortedByAvgSpeed.value?.let { runs.value = it }
        SortType.CALORIES_BURNED -> runsSortedByCaloriesBurned.value?.let { runs.value = it }
        SortType.RUNNING_TIME -> runsSortedByTimeInMillis.value?.let { runs.value = it }
    }.also {
        this.sortType = sortType
    }

    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }

    fun deleteRun(run: Run) = viewModelScope.launch {
        mainRepository.deleteRun(run)
    }
}