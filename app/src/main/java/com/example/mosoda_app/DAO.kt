package com.example.mosoda_app

import android.text.Editable
import androidx.room.*
import com.example.mosoda_app.entities.*

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(people: People)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCarpet(carpet: Carpets)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Orders)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: Profils)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: Employees)

    @Transaction
    @Query("SELECT * FROM carpets WHERE personId = :personId")
    suspend fun getCarpets(personId: Int): List<Carpets>

    @Transaction
    @Query("SELECT * FROM orders WHERE id = :id")
    suspend fun getOrders(id: Int): List<Orders>

    @Transaction
    @Query("SELECT * FROM profils WHERE userName = :username AND password = :password")
    suspend fun getProfile(username: String, password: String): List<Profils>
}