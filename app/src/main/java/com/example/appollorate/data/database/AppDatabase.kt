package com.example.appollorate.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appollorate.data.inventoryfield.DbInventoryField
import com.example.appollorate.data.inventoryfield.InventoryFieldDao

@Database(entities = [DbInventoryField::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun inventoryFieldDao(): InventoryFieldDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDataBase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "AppolloRate_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
