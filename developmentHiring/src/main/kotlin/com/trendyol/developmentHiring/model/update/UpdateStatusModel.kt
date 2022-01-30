package com.trendyol.developmentHiring.model.update
import com.trendyol.developmentHiring.enum.Status

data class UpdateStatusModel(
    val barcode: String,
    val status: Status
)
