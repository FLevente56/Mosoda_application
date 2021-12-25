package com.example.mosoda_app.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mosoda_app.entities.Employees
import com.example.mosoda_app.entities.Profils

data class ProfilsAndEmployees(
    @Embedded val profil: Profils,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val employees: Employees
)
