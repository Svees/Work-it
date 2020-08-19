package com.svees.workit.roomWorkout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_table")
data class Workout(
    var workouts: String,
    var day: String
) {
    @PrimaryKey(autoGenerate = true)
    var user_id: Int = 0
}