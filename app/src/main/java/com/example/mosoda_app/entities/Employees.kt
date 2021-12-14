package com.example.mosoda_app.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employees(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val profil: String,
    //beosztas
    val rank: String
)