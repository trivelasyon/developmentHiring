package com.trendyol.developmentHiring.controller.api

import com.trendyol.developmentHiring.model.api.RequestModel
import com.trendyol.developmentHiring.service.api.ApiService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController(private val apiService: ApiService) {

    @PostMapping("/unload")
    fun unload(@RequestBody unloadRequestModel: RequestModel) : RequestModel {
        return apiService.unloadShipmentFromVehicle(unloadRequestModel)
    }

    @PostMapping("/load")
    fun loadShipmentToVehicle(@RequestBody loadRequestModel: RequestModel) : RequestModel {
        return apiService.loadShipmentToVehicle(loadRequestModel)
    }
}