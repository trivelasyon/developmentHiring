package com.trendyol.developmentHiring.model.bag

import com.trendyol.developmentHiring.enum.Status

data class BagModel(
    val deliveryPoint: Int,
    val barcode: String,
    val status: Status  = Status.Created
)