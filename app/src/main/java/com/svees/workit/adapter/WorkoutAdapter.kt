package com.svees.workit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.svees.workit.R
import com.svees.workit.roomWorkout.Workout

class WorkoutAdapter(private val listOfWorkout: List<Workout>) : RecyclerView.Adapter<WorkoutAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val row = layoutInflater.inflate(R.layout.workout_history, parent, false)
        return MyViewHolder(row)
    }

    override fun getItemCount(): Int {
        return listOfWorkout.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.dayTextView.text = listOfWorkout[position].day
        holder.workoutTextView.text = listOfWorkout[position].workouts
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val dayTextView: TextView = view.findViewById(R.id.tv_date)
        val workoutTextView: TextView = view.findViewById(R.id.tv_workout)

    }

}