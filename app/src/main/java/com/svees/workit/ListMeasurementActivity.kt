package com.svees.workit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.svees.workit.adapter.MeasurementAdapter
import com.svees.workit.roomMeasurement.Measurement
import com.svees.workit.viewmodel.MeasurementViewModel

class ListMeasurementActivity : AppCompatActivity() {

    private lateinit var viewModel: MeasurementViewModel
    private lateinit var daoAdapter: MeasurementAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var listOfMeasurement: LiveData<List<Measurement>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_measurement)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(MeasurementViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        listOfMeasurement = viewModel.getAllMeasurement()
        listOfMeasurement.observe(this, Observer {
            if (it.isNotEmpty()) {
                daoAdapter = MeasurementAdapter(it)
                recyclerView.adapter = daoAdapter
            }
        })
    }
}