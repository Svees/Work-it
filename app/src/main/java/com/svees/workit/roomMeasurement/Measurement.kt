package com.svees.workit.roomMeasurement

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "measurement_table")
data class Measurement(
    var shoulders: Int,
    var chest: Int,
    var left_arm: Int,
    var right_arm: Int,
    var waist: Int,
    var left_thigh: Int,
    var right_thigh: Int,
    var left_calf: Int,
    var right_calf: Int,
    var day: String
) {
    @PrimaryKey(autoGenerate = true)
    var user_id: Int = 0
}