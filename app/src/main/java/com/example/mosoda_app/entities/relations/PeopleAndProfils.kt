package com.example.mosoda_app.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mosoda_app.entities.People
import com.example.mosoda_app.entities.Profils

data class PeopleAndProfils(
    @Embedded val people: People,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val profil: Profils
)