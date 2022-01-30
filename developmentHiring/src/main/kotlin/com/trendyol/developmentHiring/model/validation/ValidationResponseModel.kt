package com.trendyol.developmentHiring.model.validation

data class ValidationResponseModel(
    val isDeliveryPointValid: Boolean = false,
    val isBarcodeStatusValid: Boolean = false,
    val isDeliveryPointTypeValid: Boolean = false,
    val isValid: Boolean = false
)
