package com.example.appollorate.fake

import com.example.appollorate.data.inventoryfield.DbInventoryField
import com.example.appollorate.data.inventoryfield.InventoryFieldDao
import kotlinx.coroutines.flow.Flow

class FakeInventoryFieldDao : InventoryFieldDao {
    override suspend fun insert(inventoryField: DbInventoryField) {
        TODO("Not yet implemented")
    }

    override fun getInventoryFieldsByInventoryStepId(inventoryStepId: String): Flow<List<DbInventoryField>> {
        TODO("Not yet implemented")
    }
}
