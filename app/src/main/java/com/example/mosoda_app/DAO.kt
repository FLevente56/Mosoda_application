package com.example.mosoda_app

import androidx.room.*
import com.example.mosoda_app.entities.*

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPeople(people: People)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCarpet(carpet: Carpets)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(order: Orders)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfile(profile: Profils)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employee: Employees)

    @Transaction
    @Query("SELECT * FROM carpets WHERE personId = :personId")
    fun getCarpets(personId: Int): List<Carpets>

    @Transaction
    @Query("SELECT * FROM orders WHERE id = :id")
    fun getOrders(id: Int): List<Orders>
}