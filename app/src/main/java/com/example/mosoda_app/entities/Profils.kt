package com.example.mosoda_app.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profils(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userName: String,
    val password: String
)