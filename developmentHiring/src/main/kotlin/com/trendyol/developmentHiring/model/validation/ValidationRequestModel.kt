package com.trendyol.developmentHiring.model.validation

import com.trendyol.developmentHiring.enum.ShipmentType

data class ValidationRequestModel(
    val shipmentType: ShipmentType,
    val barcode: String,
    val deliveryPoint: Int
)
