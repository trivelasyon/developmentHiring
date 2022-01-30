package com.trendyol.developmentHiring.converter.vehicle

import com.trendyol.developmentHiring.entity.vehicle.Vehicle
import com.trendyol.developmentHiring.model.vehicle.VehicleModel
import com.trendyol.developmentHiring.util.DtoConverter
import org.springframework.stereotype.Component

@Component
class VehicleDtoConverter : DtoConverter<Vehicle, VehicleModel> {
    override fun fromDto(model: VehicleModel): Vehicle {
        return Vehicle(
            id = null,
            licensePlate = model.licensePlate
        )
    }

    override fun toDto(entity: Vehicle): VehicleModel {
        return VehicleModel(
            licensePlate = entity.licensePlate
        )
    }
}