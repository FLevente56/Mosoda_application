package com.example.mosoda_app.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class People(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val address: String,
    val teNum: String
    )