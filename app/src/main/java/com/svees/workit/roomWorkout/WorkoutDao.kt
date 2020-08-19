package com.svees.workit.roomWorkout

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WorkoutDao {
    @Insert
    fun insert(workout: Workout)

    @Update
    fun update(workout: Workout)

    @Delete
    fun delete(workout: Workout)

    @Query("SELECT * FROM workout_table")
    fun getAllWorkouts(): LiveData<List<Workout>>

    @Query("DELETE FROM workout_table")
    fun deleteAllRows()
}