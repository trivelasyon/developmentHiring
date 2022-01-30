package com.trendyol.developmentHiring.model.api

data class RouteModel(
    val deliveryPoint: Int,
    val deliveries: List<BarcodeModel>
)
