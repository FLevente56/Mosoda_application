package com.example.mosoda_app.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Carpets(
    @PrimaryKey(autoGenerate = false)
    val cod: String,
    val size: Float,
    @ColumnInfo(defaultValue = "'false'")
    val done: String,
    val personId: Int
)