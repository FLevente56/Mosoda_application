package com.example.mosoda_app.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profils(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val userName: String,
    val password: String,
    val rank: String
)