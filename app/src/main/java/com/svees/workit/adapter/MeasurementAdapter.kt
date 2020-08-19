package com.svees.workit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.svees.workit.R
import com.svees.workit.roomMeasurement.Measurement

class MeasurementAdapter(private val listOfMeasurement: List<Measurement>) : RecyclerView.Adapter<MeasurementAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val row = layoutInflater.inflate(R.layout.measurement_history, parent, false)
        return MyViewHolder(row)
    }

    override fun getItemCount(): Int {
       return listOfMeasurement.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.dayTextView.text = listOfMeasurement[position].day
        holder.shouldersTextView.text = listOfMeasurement[position].shoulders.toString()
        holder.leftArmTextView.text = listOfMeasurement[position].left_arm.toString()
        holder.rightArmTextView.text = listOfMeasurement[position].right_arm.toString()
        holder.chestTextView.text = listOfMeasurement[position].chest.toString()
        holder.leftThighTextView.text = listOfMeasurement[position].left_thigh.toString()
        holder.rightThighTextView.text = listOfMeasurement[position].right_thigh.toString()
        holder.waistTextView.text = listOfMeasurement[position].waist.toString()
        holder.leftCalfTextView.text = listOfMeasurement[position].left_calf.toString()
        holder.rightCalfTextView.text = listOfMeasurement[position].right_calf.toString()

    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val dayTextView: TextView = view.findViewById(R.id.row_day)
        val shouldersTextView: TextView = view.findViewById(R.id.tv_scholders)
        val leftArmTextView: TextView = view.findViewById(R.id.tv_leftArm)
        val rightArmTextView: TextView = view.findViewById(R.id.tv_rightArm)
        val chestTextView: TextView = view.findViewById(R.id.tv_chest)
        val leftThighTextView: TextView = view.findViewById(R.id.tv_leftThigh)
        val rightThighTextView: TextView = view.findViewById(R.id.tv_rightThigh)
        val waistTextView: TextView = view.findViewById(R.id.tv_waist)
        val leftCalfTextView: TextView = view.findViewById(R.id.tv_leftCalf)
        val rightCalfTextView: TextView = view.findViewById(R.id.tv_RightCalf)
    }

}