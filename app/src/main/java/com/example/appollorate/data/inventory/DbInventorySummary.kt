package com.example.appollorate.data.inventory

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appollorate.data.model.InventorySummary

@Entity(tableName = "inventories")
data class DbInventorySummary(
    @PrimaryKey
    val id: String = "",
    val title: String = "",
    val imagePath: String = "",
    val dateTime: String = "",
    val rating: Int = 0,
)

fun DbInventorySummary.asDomainInventorySummary(): InventorySummary {
    return InventorySummary(
        this.id,
        this.title,
        this.imagePath,
        this.dateTime,
        this.rating,
    )
}

fun InventorySummary.asDbInventorySummary(): DbInventorySummary {
    return DbInventorySummary(
        id = this.id,
        title = this.title,
        imagePath = this.imagePath,
        dateTime = this.dateTime,
        rating = this.rating,
    )
}

fun List<DbInventorySummary>.asDomainInventorySummaries(): List<InventorySummary> {
    var inventorySummaryList = this.map {
        InventorySummary(
            it.id,
            it.title,
            it.imagePath,
            it.dateTime,
            it.rating,
        )
    }
    return inventorySummaryList
}
