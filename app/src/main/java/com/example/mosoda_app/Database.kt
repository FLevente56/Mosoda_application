package com.example.mosoda_app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mosoda_app.entities.*
import java.security.AccessControlContext

@Database(
    entities = [
        Carpets :: class,
        Employees :: class,
        Orders :: class,
        People :: class,
        Profils :: class
    ],
    version = 1
)
abstract class Database : RoomDatabase(){
    abstract val dao: DAO

    companion object{
        @Volatile
        private var INSTANCE: com.example.mosoda_app.Database? = null

        fun getInstance(context: Context): com.example.mosoda_app.Database{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    com.example.mosoda_app.Database::class.java,
                    "mosodapp"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}