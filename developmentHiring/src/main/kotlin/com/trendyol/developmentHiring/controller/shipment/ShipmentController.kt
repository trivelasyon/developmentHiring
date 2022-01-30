package com.trendyol.developmentHiring.controller.shipment

import com.trendyol.developmentHiring.model.shipment.ShipmentModel
import com.trendyol.developmentHiring.service.shipment.ShipmentService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/shipment")
class ShipmentController(private val shipmentService: ShipmentService) {

    @PostMapping("/createShipment")
    fun createShipment(@RequestBody shipmentModel: ShipmentModel) {
        shipmentService.createShipment(shipmentModel)
    }
}