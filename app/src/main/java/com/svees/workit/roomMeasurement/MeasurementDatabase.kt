package com.svees.workit.roomMeasurement

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Measurement::class], version = 1, exportSchema = false)
abstract class MeasurementDatabase: RoomDatabase() {

    abstract fun measurementDao(): MeasurementDao

    companion object{
        private var instance: MeasurementDatabase? = null

        fun getInstance(context: Context): MeasurementDatabase?{
            if (instance == null) {

                instance = Room.databaseBuilder(context, MeasurementDatabase::class.java, "measurement_table")
                    .fallbackToDestructiveMigration().build()
            }
            return instance
        }
    }
}