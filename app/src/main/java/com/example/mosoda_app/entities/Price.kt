package com.example.mosoda_app.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Price(
    @PrimaryKey(autoGenerate = false)
    val carpet_price: Float
)
