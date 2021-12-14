package com.example.mosoda_app.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Orders(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val pickUpDate: String,
    val deliveryDate: String,
    val comment: String
)