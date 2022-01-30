package com.trendyol.developmentHiring.util

interface DtoConverter<E,M> {
    fun fromDto(model: M) : E
    fun toDto(entity: E) : M
}