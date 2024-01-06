package com.example.appollorate.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appollorate.data.inventory.DbInventorySummary
import com.example.appollorate.data.inventory.InventoryDao
import com.example.appollorate.data.inventoryfield.DbInventoryField
import com.example.appollorate.data.inventoryfield.InventoryFieldDao
import com.example.appollorate.data.inventorystep.DbInventoryStep
import com.example.appollorate.data.inventorystep.InventoryStepDao

@Database(entities = [DbInventoryField::class, DbInventoryStep::class, DbInventorySummary::class], version = 14, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun inventoryFieldDao(): InventoryFieldDao
    abstract fun inventoryStepDao(): InventoryStepDao
    abstract fun inventoryDao(): InventoryDao

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
