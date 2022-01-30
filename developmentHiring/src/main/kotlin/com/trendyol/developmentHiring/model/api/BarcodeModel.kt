package com.trendyol.developmentHiring.model.api

import com.trendyol.developmentHiring.enum.Status

data class BarcodeModel(
    val barcode : String,
    var state: Status? = null
)
