package com.trendyol.developmentHiring.repository.bag

import com.trendyol.developmentHiring.entity.bag.Bag
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BagRepository  : CrudRepository<Bag, Long>{
    fun findByBarcode(barcode:String) : Bag?
}