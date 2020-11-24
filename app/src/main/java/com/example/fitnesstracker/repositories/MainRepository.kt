package com.example.fitnesstracker.repositories

import com.example.fitnesstracker.database.Run
import com.example.fitnesstracker.database.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDAO: RunDAO
) {
    suspend fun insertRun(run: Run) = runDAO.insertRun(run)

    suspend fun deleteRun(run: Run) = runDAO.deleteRun(run)

    fun getAllRunSortedByDate() = runDAO.getAllRunsSortedByDate()
    fun getAllRunSortedByDistance() = runDAO.getAllRunsSortedByDistance()
    fun getAllRunSortedByCaloriesBurned() = runDAO.getAllRunsSortedByCaloriesBurned()
    fun getAllRunSortedByTimeInMillis() = runDAO.getAllRunsSortedByTimeInMillis()
    fun getAllRunSortedByAvgSpeed() = runDAO.getAllRunsSortedByAvgSpeed()

    fun getTotalAvgSpeed() = runDAO.getTotalAvgSpeed()
    fun getTotalTimeInMillis() = runDAO.getTotalTimeInMillis()
    fun getTotalDistance() = runDAO.getTotalDistance()
    fun getTotalCaloriesBurned() = runDAO.getTotalCaloriesBurned()
}