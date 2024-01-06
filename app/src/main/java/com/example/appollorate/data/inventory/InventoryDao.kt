package com.example.appollorate.data.inventory

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(inventorySummary: DbInventorySummary)

    @Query("SELECT * FROM inventories ORDER BY dateTime ASC")
    fun getInventorySummaries(): Flow<List<DbInventorySummary>>
}
