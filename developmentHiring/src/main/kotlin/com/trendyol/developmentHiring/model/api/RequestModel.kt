package com.trendyol.developmentHiring.model.api

data class RequestModel(
    val licensePlate: String,
    val route: List<RouteModel>
)
