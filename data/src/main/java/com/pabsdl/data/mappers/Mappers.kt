package com.pabsdl.data.mappers

import com.pabsdl.data.local.BeerEntity
import com.pabsdl.data.remote.BeerDTO
import com.pabsdl.domain.model.BeerItem

object Mappers {

    fun BeerEntity.toDomain(): BeerItem.Item {
        return BeerItem.Item(
            id,
            name,
            tagline,
            description,
            firstBrewed,
            imageUrl
        )
    }

    fun BeerDTO.toLocal(): BeerEntity {
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