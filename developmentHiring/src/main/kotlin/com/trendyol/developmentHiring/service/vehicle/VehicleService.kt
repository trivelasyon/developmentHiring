package com.trendyol.developmentHiring.service.vehicle

import com.trendyol.developmentHiring.converter.vehicle.VehicleDtoConverter
import com.trendyol.developmentHiring.exception.ApiException
import com.trendyol.developmentHiring.model.vehicle.VehicleModel
import com.trendyol.developmentHiring.repository.vehicle.VehicleRepository
import com.trendyol.developmentHiring.validation.VehicleValidation
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
@CacheConfig(cacheNames = ["expense"])
class VehicleService(
    private val vehicleRepository: VehicleRepository,
    private val vehicleDtoConverter: VehicleDtoConverter,
    private val vehicleValidation: VehicleValidation
) {
    fun createVehicle(vehicleModel: VehicleModel) {
        if (vehicleValidation.isLicensePlateValid(vehicleModel.licensePlate))
            vehicleRepository.save(vehicleDtoConverter.fromDto(vehicleModel))
        else
            throw ApiException.LicensePlateNotValid()
    }
    @Cacheable("licesePlate")
    fun getVehicle(licensePlate: String) : VehicleModel? {
        return vehicleRepository.findByLicensePlate(licensePlate)?.let { vehicleDtoConverter.toDto(it) }
    }
}