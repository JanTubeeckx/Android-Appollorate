package com.example.appollorate.data.inventorystep

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appollorate.data.model.InventoryStep

@Entity(tableName = "inventory_steps")
data class DbInventoryStep(
    @PrimaryKey
    val id: String = "",
    val description: String = "",
    val icon: String = "",
    val order: Int = 0,
    val isDamage: Boolean = false,
    val isBound: Boolean = false,
    val inventoryStepId: String? = "",
)

fun DbInventoryStep.asDomainInventoryStep(): InventoryStep {
    return InventoryStep(
        this.id,
        this.description,
        this.icon,
        this.order,
        this.isDamage,
        this.isBound,
        this.inventoryStepId,
    )
}

fun InventoryStep.asDbInventoryStep(): DbInventoryStep {
    return DbInventoryStep(
        id = this.id,
        description = this.description,
        icon = this.icon,
        order = this.order,
        isDamage = this.isDamage,
        isBound = this.isBound,
        inventoryStepId = this.inventoryStepId,
    )
}

fun List<DbInventoryStep>.asDomainInventorySteps(): List<InventoryStep> {
    var inventoryStepList = this.map {
        InventoryStep(
            it.id,
            it.description,
            it.icon,
            it.order,
            it.isDamage,
            it.isBound,
            it.inventoryStepId,
        )
    }
    return inventoryStepList
}
