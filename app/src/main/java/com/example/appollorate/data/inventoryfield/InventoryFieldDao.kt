package com.example.appollorate.data.inventoryfield

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryFieldDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(inventoryField: DbInventoryField)

    @Query("SELECT * FROM inventory_fields WHERE inventoryStep_id = :inventoryStepId")
    fun getIdentificationInventoryFieldsByInventoryStepId(inventoryStepId: String): Flow<List<DbInventoryField>>
}
