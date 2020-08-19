package com.svees.workit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.svees.workit.roomWorkout.Workout
import com.svees.workit.roomWorkout.WorkoutRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {
    private var workoutRepository: WorkoutRepository = WorkoutRepository(application)
    private var allWorkout: Deferred<LiveData<List<Workout>>> = workoutRepository.getAllWorkoutAsync()

    fun insertWorkout(workout: Workout) {
        workoutRepository.insertWorkout(workout)
    }

    fun updateWorkout(workout: Workout) {
        workoutRepository.updateWorkout(workout)
    }

    fun deleteWorkout(workout: Workout) {
        workoutRepository.deleteWorkout(workout)
    }

    fun getAllWorkout(): LiveData<List<Workout>> = runBlocking { allWorkout.await() }

    fun deleteAllRows() {
        workoutRepository.deleteAllRows()
    }
}