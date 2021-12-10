package com.example.mosoda_app.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Carpets(
    @PrimaryKey(autoGenerate = false)
    val cod: String,
    val size: Float,
    val personId: Int
)