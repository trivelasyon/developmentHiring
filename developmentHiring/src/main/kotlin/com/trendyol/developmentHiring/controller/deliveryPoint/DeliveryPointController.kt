package com.trendyol.developmentHiring.controller.deliveryPoint

import com.trendyol.developmentHiring.model.deliveryPoint.DeliveryPointModel
import com.trendyol.developmentHiring.service.deliveryPoint.DeliveryPointService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/deliveryPoint")
class DeliveryPointController(private val deliveryPointService: DeliveryPointService) {

    @PostMapping("/createDeliveryPoint")
    fun createDeliveryPoint(@RequestBody deliveryPointModel: DeliveryPointModel) {
        deliveryPointService.createDeliveryPoint(deliveryPointModel)
    }
}