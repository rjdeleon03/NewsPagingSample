package com.pabsdl.domain.model

sealed class BeerItem {

    data class Item(
        val id: Int,
        val name: String,
        val tagline: String,
        val description: String,
        val firstBrewed: String,
        val imageUrl: String?
    )

    class Separator()
}