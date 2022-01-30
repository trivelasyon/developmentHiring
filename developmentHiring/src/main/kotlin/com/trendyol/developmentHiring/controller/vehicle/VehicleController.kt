package com.trendyol.developmentHiring.controller.vehicle

import com.trendyol.developmentHiring.model.vehicle.VehicleModel
import com.trendyol.developmentHiring.service.vehicle.VehicleService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vehicle")
class VehicleController(private val vehicleService: VehicleService) {

    @PostMapping("/createVehicle")
    fun createVehicle(@RequestBody vehicleModel: VehicleModel) {
        vehicleService.createVehicle(vehicleModel)
    }
}