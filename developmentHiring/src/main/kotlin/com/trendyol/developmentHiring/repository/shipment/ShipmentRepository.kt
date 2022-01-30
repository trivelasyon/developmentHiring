package com.trendyol.developmentHiring.repository.shipment

import com.trendyol.developmentHiring.entity.shipment.Shipment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ShipmentRepository  : CrudRepository<Shipment, Long>{
    fun findByBarcode(barcode:String) : Shipment?
}