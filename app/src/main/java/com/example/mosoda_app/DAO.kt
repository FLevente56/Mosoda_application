package com.example.mosoda_app

import android.text.Editable
import androidx.room.*
import com.example.mosoda_app.entities.*
import com.example.mosoda_app.entities.relations.PeopleWithCarpets
import java.util.*

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
    @Query("SELECT * FROM people WHERE id = :id")
    suspend fun getPeopleWithCarpets(id: Int): List<PeopleWithCarpets>

    @Transaction
    @Query("SELECT * FROM carpets WHERE personId = :personId")
    suspend fun getCarpets(personId: Int): List<Carpets>

    @Transaction
    @Query("SELECT * FROM orders WHERE id = :id")
    suspend fun getOrders(id: Int): List<Orders>

    @Transaction
    @Query("SELECT * FROM employees WHERE id = :id")
    suspend fun getEmployee(id: Int): Employees

    @Transaction
    @Query("SELECT * FROM profils WHERE userName = :username AND password = :password")
    suspend fun getProfile(username: String, password: String): List<Profils>

    @Transaction
    @Query("SELECT * FROM people")
    suspend fun getAllPeople(): List<People>

    @Transaction
    @Query("SELECT * FROM profils")
    suspend fun getAllProfils(): List<Profils>

    @Transaction
    @Query("SELECT * FROM orders")
    suspend fun getAllOrders(): List<Orders>

    @Transaction
    @Query("SELECT * FROM carpets")
    suspend fun getAllCarpets(): List<Carpets>

    @Transaction
    @Query("UPDATE orders SET deliveryDate = :deliveryDate WHERE id = :id")
    suspend fun updateDeliveryDate(id:  Int, deliveryDate: String)
}