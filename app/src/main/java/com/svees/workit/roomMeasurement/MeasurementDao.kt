package com.svees.workit.roomMeasurement

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MeasurementDao {

    @Insert
    fun insert(measurement: Measurement)

    @Update
    fun update(measurement: Measurement)

    @Delete
    fun delete(measurement: Measurement)

    @Query("SELECT * FROM measurement_table")
    fun getAllMeasurements(): LiveData<List<Measurement>>

    @Query("DELETE FROM measurement_table")
    fun deleteAllRows()

    }
