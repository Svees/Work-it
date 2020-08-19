package com.svees.workit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.svees.workit.roomMeasurement.Measurement
import com.svees.workit.roomMeasurement.MeasurementRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class MeasurementViewModel(application: Application) : AndroidViewModel(application) {

    private var measurementRepository: MeasurementRepository = MeasurementRepository(application)
    private var allMeasurement: Deferred<LiveData<List<Measurement>>> = measurementRepository.getAllMeasurementAsync()

    fun insertMeasurement(measurement: Measurement) {
        measurementRepository.insertMeasurement(measurement)
    }

    fun updateMeasurement(measurement: Measurement) {
        measurementRepository.updateMeasurement(measurement)
    }

    fun deleteMeasurement(measurement: Measurement) {
        measurementRepository.deleteMeasurement(measurement)
    }

    fun getAllMeasurement(): LiveData<List<Measurement>> = runBlocking { allMeasurement.await() }

    fun deleteAllRows() {
        measurementRepository.deleteAllRows()
    }
}