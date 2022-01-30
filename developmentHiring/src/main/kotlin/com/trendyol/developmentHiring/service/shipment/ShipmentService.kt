package com.trendyol.developmentHiring.service.shipment

import com.trendyol.developmentHiring.converter.shipment.ShipmentDtoConverter
import com.trendyol.developmentHiring.model.shipment.ShipmentModel
import com.trendyol.developmentHiring.model.update.UpdateStatusModel
import com.trendyol.developmentHiring.repository.shipment.ShipmentRepository
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
@CacheConfig(cacheNames = ["expense"])
class ShipmentService(
    private val shipmentRepository: ShipmentRepository,
    private val shipmentDtoConverter: ShipmentDtoConverter
) {
    fun createShipment(shipmentModel: ShipmentModel) {
        shipmentRepository.save(shipmentDtoConverter.fromDto(shipmentModel))
    }

    fun updateShipmentStatus(updateBarcodeStatusModel: UpdateStatusModel) {
        val entity = shipmentRepository.findByBarcode(updateBarcodeStatusModel.barcode)
        entity?.let {
            it.status = updateBarcodeStatusModel.status
            shipmentRepository.save(it)
        }
    }
    @Cacheable("shipment")
    fun getShipment(barcode: String) : ShipmentModel? {
        val model =  shipmentRepository.findByBarcode(barcode)?.let {
            shipmentDtoConverter.toDto(it)
        }
        return model
    }

}