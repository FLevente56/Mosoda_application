package com.example.mosoda_app

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mosoda_app.entities.*
import java.security.AccessControlContext
import java.util.*

@Database(
    entities = [
        Carpets :: class,
        Employees :: class,
        Orders :: class,
        People :: class,
        Profils :: class,
        Price :: class
    ],
    version = 4,
    exportSchema = true
)
abstract class Database() : RoomDatabase() {
    abstract val dao: DAO

    companion object {
        @Volatile
        private var INSTANCE: com.example.mosoda_app.Database? = null
        val migration_2_3: Migration = object: Migration(2, 3){
            override fun migrate(database: SupportSQLiteDatabase){
                database.execSQL("ALTER TABLE carpets ADD COLUMN done TEXT NOT NULL DEFAULT 'false'")
            }
        }
        val migration_3_4: Migration = object: Migration(3, 4){
            override fun migrate(database: SupportSQLiteDatabase){
                database.execSQL("CREATE TABLE price (carpet_price REAL PRIMARY KEY NOT NULL DEFAULT 7)")
            }
        }
        fun getInstance(context: Context): com.example.mosoda_app.Database {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    com.example.mosoda_app.Database::class.java,
                    "mosodapp"
                ).addMigrations(migration_3_4).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}