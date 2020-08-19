package com.svees.workit.roomWorkout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Workout::class], version = 1, exportSchema = false)
abstract class WorkoutDatabase: RoomDatabase() {

    abstract fun workoutDao(): WorkoutDao

    companion object{
        private var instance: WorkoutDatabase? = null

        fun getInstance(context: Context): WorkoutDatabase?{
            if (instance == null) {

                instance = Room.databaseBuilder(context, WorkoutDatabase::class.java, "workout_table")
                    .fallbackToDestructiveMigration().build()
            }
            return instance
        }
    }
}