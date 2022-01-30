package com.trendyol.developmentHiring.service.api

import com.trendyol.developmentHiring.enum.ShipmentType
import com.trendyol.developmentHiring.enum.Status
import com.trendyol.developmentHiring.exception.ApiException
import com.trendyol.developmentHiring.model.api.RequestModel
import com.trendyol.developmentHiring.model.update.UpdateStatusModel
import com.trendyol.developmentHiring.model.validation.ValidationRequestModel
import com.trendyol.developmentHiring.service.bag.BagService
import com.trendyol.developmentHiring.service.log.LogService
import com.trendyol.developmentHiring.service.packageInBag.PackageInBagService
import com.trendyol.developmentHiring.service.shipment.ShipmentService
import com.trendyol.developmentHiring.validation.PackageBagValidation
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ApiService(
    private val packageBagValidation: PackageBagValidation,
    private val shipmentService: ShipmentService,
    private val bagService: BagService,
    private val logService: LogService,
    private val packageInBagService: PackageInBagService
) {

    fun decideShipmentType(barcode: String): ShipmentType {
        val shipment = shipmentService.getShipment(barcode)
        val bag = bagService.getBag(barcode)
        if( shipment == null &&  bag == null)
            throw ApiException.InvalidBarcode()

        if (shipment == null)
            return ShipmentType.Bag
        else
            return ShipmentType.Shipment
    }

    @Transactional
    fun loadShipmentToVehicle(requestModel: RequestModel) : RequestModel {
        if (packageBagValidation.isVehicleNotRegisterInSystem(requestModel.licensePlate))
            throw ApiException.VehicleNotFound()
        requestModel.route.forEach { routeModel ->
            routeModel.deliveries.forEach {
                val shipmentType = decideShipmentType(it.barcode)
                when (shipmentType) {
                    ShipmentType.Shipment -> {
                        shipmentService.updateShipmentStatus(UpdateStatusModel(it.barcode, Status.Loaded))
                    }
                    ShipmentType.Bag -> {
                        updateStatusOfBagAndRelatedPackages(it.barcode, Status.Loaded)
                    }
                }
            }
        }
        return requestModel
    }

    @Transactional
    fun unloadShipmentFromVehicle(requestModel: RequestModel): RequestModel {
        requestModel.route.forEach { routeModel ->
            val deliveryPoint = routeModel.deliveryPoint
            routeModel.deliveries.forEach {
                val barcode = it.barcode
                val shipmentType = decideShipmentType(barcode)
                it.state = Status.Loaded
                val flag = packageBagValidation.isValidForUnload(
                    ValidationRequestModel(
                        barcode = barcode,
                        shipmentType = shipmentType,
                        deliveryPoint = deliveryPoint
                    )
                )
                if (flag.isValid) {
                    when (shipmentType) {
                        ShipmentType.Shipment -> {
                            shipmentService.updateShipmentStatus(UpdateStatusModel(barcode, Status.UnLoaded))
                            it.state = Status.UnLoaded
                        }
                        ShipmentType.Bag -> {
                            updateStatusOfBagAndRelatedPackages(barcode, Status.UnLoaded)
                            it.state = Status.UnLoaded
                        }
                    }
                } else {
                    // skip this delivery and insert log table
                    it.state = Status.CannotUnload
                    logService.insertLogTable(barcode, flag)
                }
            }
        }
        return requestModel
    }


    private fun updateStatusOfBagAndRelatedPackages(barcode: String, status: Status) {
        val relatedPackages = packageInBagService.getPackagesRelatedBag(barcode)
        relatedPackages?.let {
            it.forEach {
                shipmentService.updateShipmentStatus(UpdateStatusModel(it.barcode, status))
            }
        }
        bagService.updateBagStatus(UpdateStatusModel(barcode, status))
    }
}