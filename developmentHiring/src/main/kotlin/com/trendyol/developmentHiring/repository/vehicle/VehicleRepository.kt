package com.trendyol.developmentHiring.repository.vehicle

import com.trendyol.developmentHiring.entity.vehicle.Vehicle
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository : CrudRepository<Vehicle, Long>{
    fun findByLicensePlate(licensePlate : String) : Vehicle?
}