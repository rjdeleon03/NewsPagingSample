package com.pabsdl.newspaging.data.model

import com.google.gson.annotations.SerializedName
import com.pabsdl.newspaging.data.database.BeerEntity

data class BeerItem(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    @SerializedName("first_brewed")
    val firstBrewed: String,
    @SerializedName("image_url")
    val imageUrl: String?
) {
    fun toBeerEntity(): BeerEntity {
        return BeerEntity(
            id,
            name,
            tagline,
            description,
            firstBrewed,
            imageUrl
        )
    }
}