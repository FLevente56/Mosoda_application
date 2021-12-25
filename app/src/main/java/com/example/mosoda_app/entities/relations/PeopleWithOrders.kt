package com.example.mosoda_app.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mosoda_app.entities.Orders
import com.example.mosoda_app.entities.People

data class PeopleWithOrders(
    @Embedded val people: People,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val orders: List<Orders>
)
