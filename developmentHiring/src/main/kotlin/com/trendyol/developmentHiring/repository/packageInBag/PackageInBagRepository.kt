package com.trendyol.developmentHiring.repository.packageInBag

import com.trendyol.developmentHiring.entity.packageInBag.PackageInBag
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PackageInBagRepository  : CrudRepository<PackageInBag, Long>{
    fun findByBarcode(barcode : String) :PackageInBag?
    fun findByBagBarcode(bagBarcode: String): List<PackageInBag>?
}