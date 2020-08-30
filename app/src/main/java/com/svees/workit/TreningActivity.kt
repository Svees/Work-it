package com.svees.workit

import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.svees.workit.roomWorkout.Workout
import com.svees.workit.viewmodel.WorkoutViewModel
import kotlinx.android.synthetic.main.activity_areobik.btn_add_rep
import kotlinx.android.synthetic.main.activity_areobik.chronometer
import kotlinx.android.synthetic.main.activity_areobik.chronometer_start
import kotlinx.android.synthetic.main.activity_trening.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class TreningActivity : AppCompatActivity() {

    var timeWhenStopped = 0L
    var workoutTime: String = ""
    var rep = 0;


    lateinit var tv_activity: TextView

    private lateinit var viewModel: WorkoutViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trening)

        tv_activity = findViewById(R.id.tv_activity)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(WorkoutViewModel::class.java)

        chronometer_start.setOnClickListener {

            tv_activity.text = "Active"

            chronometer.base = SystemClock.elapsedRealtime() + timeWhenStopped
            chronometer.start()
            chronometer_start.visibility = View.INVISIBLE
            btn_add_rep.visibility = View.VISIBLE
        }

        btn_add_rep.setOnClickListener {
            addRep()
        }

        val currnetDay = LocalDateTime.now()

        btn_add_workout.setOnClickListener {
            val builder =
                AlertDialog.Builder(this@TreningActivity)

            val inflater = layoutInflater
            val dialogView =
                inflater.inflate(R.layout.alertdialog_custom_view, null)

            builder.setCancelable(false)

            builder.setView(dialogView)
            val btn_save = dialogView.findViewById<Button>(R.id.btn_save)
            val btn_cancel = dialogView.findViewById<Button>(R.id.btn_cancel)
            val et_name = dialogView.findViewById<EditText>(R.id.et_name)
            val dialog = builder.create()

            btn_save.setOnClickListener {
                val workouts = et_name.text.toString()
                val day = currnetDay.format(DateTimeFormatter.ISO_DATE)
                val workoutsave = Workout(workouts, day)

                viewModel.insertWorkout(workoutsave)

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
            btn_cancel.setOnClickListener { dialog.cancel() }
            dialog.show()
        }
        btn_reset.setOnClickListener {
            val intent = intent
            finish()
            startActivity(intent)
        }

    }

    private fun addRep() {

        tv_activity.text = "Break"
        rep++
        workoutTime = chronometer.text.toString()

        chronometer_start.visibility = View.VISIBLE
        btn_add_rep.visibility = View.INVISIBLE

        // val imageView = ImageView(this)


        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val textView = TextView(this)

        // imageView.layoutParams = params
        textView.layoutParams = params
        textView.textSize = 40f

        // imageView.setImageDrawable(resources.getDrawable(R.drawable.block))
        textView.text = "$rep - $workoutTime"


        // linearLayout_reps.addView(imageView)
        linearLayout_reps.addView(textView)

        chronometer.base = SystemClock.elapsedRealtime() + timeWhenStopped
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }
    }

    private fun hideSystemUI() {
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}