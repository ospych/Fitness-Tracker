package com.example.fitnesstracker.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.fitnesstracker.repositories.MainRepository
import androidx.hilt.lifecycle.ViewModelInject
import javax.inject.Inject

class StatisticViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
): ViewModel() {

    val totalTimeRun = mainRepository.getTotalTimeInMillis()
    val totalDistance = mainRepository.getTotalDistance()
    val totalAvgSpeed = mainRepository.getTotalAvgSpeed()
    val totalCaloriesBurned = mainRepository.getTotalCaloriesBurned()

    val runsSortedByDate = mainRepository.getAllRunSortedByDate()
}