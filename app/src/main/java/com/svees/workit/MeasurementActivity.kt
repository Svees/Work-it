package com.svees.workit

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.svees.workit.roomMeasurement.Measurement
import com.svees.workit.viewmodel.MeasurementViewModel
import kotlinx.android.synthetic.main.activity_measurement.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MeasurementActivity : AppCompatActivity() {

    private lateinit var viewModel: MeasurementViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measurement)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MeasurementViewModel::class.java)

        val currnetDay = LocalDateTime.now()

        btn_save.setOnClickListener {
            val shoulders: Int = et_shoulders.text.toString().toInt()
            val chest: Int = et_chest.text.toString().toInt()
            val left_arm: Int = et_left_arm.text.toString().toInt()
            val right_arm: Int = et_right_arm.text.toString().toInt()
            val waist: Int = et_waist.text.toString().toInt()
            val left_thigh: Int = et_left_thigh.text.toString().toInt()
            val right_thigh: Int = et_right_thigh.text.toString().toInt()
            val left_calf: Int = et_left_calf.text.toString().toInt()
            val right_calf: Int = et_right_calf.text.toString().toInt()
            val day: String = currnetDay.format(DateTimeFormatter.ISO_DATE)

            val measuremnt = Measurement(shoulders, chest, left_arm, right_arm, waist, left_thigh, right_thigh, left_calf, right_calf, day)

            viewModel.insertMeasurement(measuremnt)

            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
        }

        btn_measurement_list.setOnClickListener {
            startActivity(Intent(this, ListMeasurementActivity::class.java))
        }
    }
}