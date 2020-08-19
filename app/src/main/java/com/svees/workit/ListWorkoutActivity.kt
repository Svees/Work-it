package com.svees.workit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.svees.workit.adapter.MeasurementAdapter
import com.svees.workit.adapter.WorkoutAdapter
import com.svees.workit.roomMeasurement.Measurement
import com.svees.workit.roomWorkout.Workout
import com.svees.workit.viewmodel.MeasurementViewModel
import com.svees.workit.viewmodel.WorkoutViewModel

class ListWorkoutActivity : AppCompatActivity() {

    private lateinit var viewModel: WorkoutViewModel
    private lateinit var daoAdapter: WorkoutAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var listOfWorkout: LiveData<List<Workout>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_workout)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(WorkoutViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        listOfWorkout = viewModel.getAllWorkout()
        listOfWorkout.observe(this, Observer {
            if (it.isNotEmpty()) {
                daoAdapter = WorkoutAdapter(it)
                recyclerView.adapter = daoAdapter
            }
        })
    }

    }