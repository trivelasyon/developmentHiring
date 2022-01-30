package com.trendyol.developmentHiring.validation

import com.trendyol.developmentHiring.enum.ShipmentType
import com.trendyol.developmentHiring.enum.Status
import com.trendyol.developmentHiring.model.validation.ValidationRequestModel
import com.trendyol.developmentHiring.model.validation.ValidationResponseModel
import com.trendyol.developmentHiring.repository.bag.BagRepository
import com.trendyol.developmentHiring.repository.shipment.ShipmentRepository
import com.trendyol.developmentHiring.service.packageInBag.PackageInBagService
import com.trendyol.developmentHiring.service.vehicle.VehicleService
import org.springframework.stereotype.Component

@Component
class PackageBagValidation
    (
    private val packageInBagService: PackageInBagService,
    private val shipmentRepository: ShipmentRepository,
    private val bagRepository: BagRepository,
    private val vehicleService: VehicleService
) {

    fun isValidForUnload(validationModel: ValidationRequestModel): ValidationResponseModel {
        val isDeliveryPointValid: Boolean
        val isBarcodeStatusValid: Boolean
        val isDeliveryPointTypeValid: Boolean
        val isValid: Boolean

        val flagModel = when (validationModel.shipmentType) {
            ShipmentType.Shipment -> {
                val shipment = shipmentRepository.findByBarcode(validationModel.barcode)
                isDeliveryPointValid = shipment?.deliveryPoint == validationModel.deliveryPoint
                isBarcodeStatusValid = shipment?.status == Status.Created
                isDeliveryPointTypeValid = validateUnloadAccordingDeliveryPointType(validationModel)
                isValid = isDeliveryPointValid && isBarcodeStatusValid && isDeliveryPointTypeValid
                ValidationResponseModel(
                    isDeliveryPointValid, isBarcodeStatusValid,
                    isDeliveryPointTypeValid, isValid
                )
            }
            ShipmentType.Bag -> {
                val bag = bagRepository.findByBarcode(validationModel.barcode)
                isDeliveryPointValid = bag?.deliveryPoint == validationModel.deliveryPoint
                isBarcodeStatusValid = bag?.status == Status.Created
                isDeliveryPointTypeValid = validateUnloadAccordingDeliveryPointType(validationModel)
                isValid = isDeliveryPointValid && isBarcodeStatusValid && isDeliveryPointTypeValid
                ValidationResponseModel(
                    isDeliveryPointValid, isBarcodeStatusValid,
                    isDeliveryPointTypeValid, isValid
                )
            }
        }
        return flagModel
    }

    private fun isPackageInBag(valid: ValidationRequestModel) : Boolean {
        var flag = false
        if(valid.shipmentType == ShipmentType.Shipment) {
            val result = packageInBagService.getBagBarcodeFromBarcode(valid.barcode)
            result?.let {
                flag = true
            }
        }
        return flag
    }

    // branch - 1 : only package
    // distribution center - 2 : all
    // transfer center - 3 : bags and packages in bags
    private fun validateUnloadAccordingDeliveryPointType(validationModel: ValidationRequestModel): Boolean {
        val response = when (validationModel.deliveryPoint) {
            1 -> validationModel.shipmentType == ShipmentType.Shipment
            2 -> true
            3 -> validationModel.shipmentType == ShipmentType.Bag || isPackageInBag(validationModel)
            else -> false
        }
        return response
    }

    fun isVehicleNotRegisterInSystem(licensePlate : String) : Boolean {
        val vehicle = vehicleService.getVehicle(licensePlate)
        return vehicle == null
    }

}
