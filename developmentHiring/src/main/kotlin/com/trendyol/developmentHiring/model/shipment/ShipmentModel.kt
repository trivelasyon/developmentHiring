package com.trendyol.developmentHiring.model.shipment

import com.trendyol.developmentHiring.enum.Status

data class ShipmentModel(
    val deliveryPoint: Int,
    val barcode: String,
    val volumetricWeight: Int,
    val status: Status  = Status.Created
)