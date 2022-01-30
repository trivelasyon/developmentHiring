package com.trendyol.developmentHiring.model.packageInBag

data class AssignPackageToBagModel(
    val bagBarcode: String,
    val barcodes: List<String>
)