package com.example.mosoda_app.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mosoda_app.entities.Carpets
import com.example.mosoda_app.entities.People

data class PeopleWithCarpets(
    @Embedded val people: People,
    @Relation(
        parentColumn = "id",
        entityColumn = "personId"
    )
    val carpets: List<Carpets>
)
