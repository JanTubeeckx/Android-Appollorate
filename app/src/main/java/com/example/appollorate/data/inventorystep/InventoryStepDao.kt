package com.example.appollorate.data.inventorystep

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryStepDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(inventoryStep: DbInventoryStep)

    @Query("SELECT * FROM inventory_steps WHERE isDamage = 0 AND isBound = 0")
    fun getIdentificationInventorySteps(): Flow<List<DbInventoryStep>>

    @Query("SELECT * FROM inventory_steps WHERE isDamage = 1 AND isBound = 1")
    fun getDamageInventoryStepsBound(): Flow<List<DbInventoryStep>>

    @Query("SELECT * FROM inventory_steps WHERE isDamage = 1 AND isBound = 0")
    fun getDamageInventoryStepsLoose(): Flow<List<DbInventoryStep>>

    @Query("SELECT * FROM inventory_steps WHERE isDamage = 0 AND inventoryStepId = null")
    fun getFormCharInventorySteps(): Flow<List<DbInventoryStep>>
}
