package com.svees.workit.roomMeasurement

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class MeasurementRepository(application: Application) {

    private var measurementDao: MeasurementDao

    init {
        val database = MeasurementDatabase.getInstance(application.applicationContext)
        measurementDao = database!!.measurementDao()
    }

    fun insertMeasurement(measurement: Measurement) =
        CoroutineScope(Dispatchers.IO).launch { measurementDao.insert(measurement) }

    fun updateMeasurement(measurement: Measurement) =
        CoroutineScope(Dispatchers.IO).launch { measurementDao.update(measurement) }

    fun deleteMeasurement(measurement: Measurement) =
        CoroutineScope(Dispatchers.IO).launch { measurementDao.delete(measurement) }

    fun getAllMeasurementAsync(): Deferred<LiveData<List<Measurement>>> =
        CoroutineScope(Dispatchers.IO).async { measurementDao.getAllMeasurements() }

    fun deleteAllRows() =
        CoroutineScope(Dispatchers.IO).launch {
            measurementDao.deleteAllRows()
        }

}