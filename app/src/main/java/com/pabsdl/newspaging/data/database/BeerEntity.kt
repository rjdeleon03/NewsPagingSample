package com.pabsdl.newspaging.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pabsdl.newspaging.data.model.BeerItem

@Entity(tableName = "beers")
data class BeerEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val firstBrewed: String,
    val imageUrl: String?
) {
    fun toBeerItem(): BeerItem {
        return BeerItem(
            id,
            name,
            tagline,
            description,
            firstBrewed,
            imageUrl
        )
    }
}