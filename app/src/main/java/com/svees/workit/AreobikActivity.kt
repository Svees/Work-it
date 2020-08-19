package com.svees.workit

import android.media.MediaPlayer
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
import kotlinx.android.synthetic.main.activity_areobik.*
import kotlinx.android.synthetic.main.activity_areobik.btn_add_rep
import kotlinx.android.synthetic.main.activity_areobik.btn_add_workout
import kotlinx.android.synthetic.main.activity_areobik.chronometer
import kotlinx.android.synthetic.main.activity_areobik.chronometer_start
import kotlinx.android.synthetic.main.activity_trening.*
import kotlinx.android.synthetic.main.alertdialog_custom_view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AreobikActivity : AppCompatActivity() {

    lateinit var option: Spinner
    lateinit var tv_workout: TextView
    lateinit var linearLayout: LinearLayout

    private lateinit var viewModel: WorkoutViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_areobik)

        option = findViewById(R.id.spinner)
        tv_workout = findViewById(R.id.tv_workout)
        linearLayout = findViewById(R.id.linearLayout)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(WorkoutViewModel::class.java)

        var bg_song: MediaPlayer? = null
        var timeWhenStopped = 0L

        var options = arrayOf("Workout 1", "Workout 2")

        option.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 0) {
                    var myArray1 = listOf(
                        "Leg hold\n",
                        "Knee to chest\n",
                        "Plank\n",
                        "Roll up\n",
                        "Pull up\n",
                        "Hip lift\n",
                        "Mountain climbers\n",
                        "Plank"
                    )

                    tv_workout.text = myArray1.toString()

                } else {
                    var myArray2 = listOf(
                        "Mountain climbers\n",
                        "Jumping jacks\n",
                        "Wykroki jumps\n",
                        "Jumping jacks\n",
                        "brzuch pilka\n",
                        "Jumping jacks\n",
                        "przysiady\n",
                        "Jumping jacks"
                    )

                    tv_workout.text = myArray2.toString()
                }

            }

        }

        chronometer.setOnChronometerTickListener {
            if (SystemClock.elapsedRealtime() - chronometer.base >= 20000) {
                chronometer.base = SystemClock.elapsedRealtime()
                bg_song = MediaPlayer.create(this@AreobikActivity, R.raw.bing)
                bg_song!!.start()
                Toast.makeText(applicationContext, "Bing!", Toast.LENGTH_SHORT).show()


            }
        }

        chronometer_start.setOnClickListener {
            chronometer.base = SystemClock.elapsedRealtime() + timeWhenStopped
            chronometer.start()

            btn_add_rep.visibility = View.VISIBLE
            btn_add_workout.visibility = View.VISIBLE
        }

        chronometer_stop.setOnClickListener {
            timeWhenStopped = chronometer.base - SystemClock.elapsedRealtime()
            chronometer.stop()
        }

        chronometer_reset.setOnClickListener {
            chronometer.base = SystemClock.elapsedRealtime()
            timeWhenStopped = 0
        }

        val currnetDay = LocalDateTime.now()

        btn_add_workout.setOnClickListener {
            val builder =
                AlertDialog.Builder(this@AreobikActivity)

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

    }

    fun createAndAddView(View: View) {

        val imageView = ImageView(this)

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        imageView.layoutParams = params

        imageView.setImageDrawable(resources.getDrawable(R.drawable.block))

        linearLayout.addView(imageView)


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