package com.pabsdl.domain.model

data class BeerItem(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val firstBrewed: String,
    val imageUrl: String?
) {
}