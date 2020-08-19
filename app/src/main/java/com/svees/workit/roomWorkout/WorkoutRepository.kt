package com.svees.workit.roomWorkout

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class WorkoutRepository(application: Application) {
    private var workoutDao: WorkoutDao

    init {
        val database = WorkoutDatabase.getInstance(application.applicationContext)
        workoutDao = database!!.workoutDao()
    }

    fun insertWorkout(workout: Workout) =
        CoroutineScope(Dispatchers.IO).launch { workoutDao.insert(workout) }

    fun updateWorkout(workout: Workout) =
        CoroutineScope(Dispatchers.IO).launch { workoutDao.update(workout) }

    fun deleteWorkout(workout: Workout) =
        CoroutineScope(Dispatchers.IO).launch { workoutDao.delete(workout) }

    fun getAllWorkoutAsync(): Deferred<LiveData<List<Workout>>> =
        CoroutineScope(Dispatchers.IO).async { workoutDao.getAllWorkouts() }

    fun deleteAllRows() =
        CoroutineScope(Dispatchers.IO).launch {
            workoutDao.deleteAllRows()
        }
}