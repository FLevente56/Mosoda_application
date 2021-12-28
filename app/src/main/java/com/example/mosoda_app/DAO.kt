package com.example.mosoda_app

import android.text.Editable
import androidx.room.*
import com.example.mosoda_app.entities.*
import com.example.mosoda_app.entities.relations.PeopleWithCarpets
import com.example.mosoda_app.entities.relations.PeopleWithOrders
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrice(price: Price)

    @Transaction
    @Query("SELECT * FROM people WHERE id = :id")
    suspend fun getPeopleWithCarpets(id: Int): List<PeopleWithCarpets>

    @Transaction
    @Query("SELECT * FROM people")
    suspend fun getPeopleWithOrders(): List<PeopleWithOrders>

    @Transaction
    @Query("SELECT * FROM people WHERE id = :id")
    suspend fun getPeopleWithOrdersById(id: Int): PeopleWithOrders

    @Transaction
    @Query("SELECT * FROM people")
    suspend fun getAllPeopleWithCarpets(): List<PeopleWithCarpets>

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

    @Transaction
    @Query("SELECT carpet_price FROM price")
    suspend fun getPrice(): Float

    @Transaction
    @Query("UPDATE price SET carpet_price = :price")
    suspend fun updatePrice(price: Float)

    @Transaction
    @Query("UPDATE carpets SET done = 'true' WHERE cod = :cod")
    suspend fun updateCarpetDone(cod: String)

    @Transaction
    @Query("SELECT id from people WHERE name = :name")
    suspend fun getPeopleId(name: String): Int
}