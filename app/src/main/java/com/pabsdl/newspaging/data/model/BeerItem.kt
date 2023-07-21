package com.pabsdl.newspaging.data.model

import com.google.gson.annotations.SerializedName

data class BeerItem(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    @SerializedName("first_brewed")
    val firstBrewed: String,
    @SerializedName("image_url")
    val imageUrl: String?
)