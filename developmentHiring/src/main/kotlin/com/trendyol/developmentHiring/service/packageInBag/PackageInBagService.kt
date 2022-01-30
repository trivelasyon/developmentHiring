package com.trendyol.developmentHiring.service.packageInBag

import com.trendyol.developmentHiring.converter.packageInBag.PackageInBagDtoConverter
import com.trendyol.developmentHiring.entity.packageInBag.PackageInBag
import com.trendyol.developmentHiring.enum.Status
import com.trendyol.developmentHiring.exception.ApiException
import com.trendyol.developmentHiring.model.packageInBag.AssignPackageToBagModel
import com.trendyol.developmentHiring.model.packageInBag.PackageInBagModel
import com.trendyol.developmentHiring.model.update.UpdateStatusModel
import com.trendyol.developmentHiring.repository.packageInBag.PackageInBagRepository
import com.trendyol.developmentHiring.service.shipment.ShipmentService
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@CacheConfig(cacheNames = ["expense"])
class PackageInBagService(
    private val packageInBagRepository: PackageInBagRepository,
    private val shipmentService: ShipmentService,
    private val packageInBagDtoConverter: PackageInBagDtoConverter
) {
    @Transactional
    fun loadPackagesToBag(assignPackageToBagModel: AssignPackageToBagModel) {
        assignPackageToBagModel.barcodes.forEach { barcode ->
            shipmentService.getShipment(barcode)?.let {
                if (it.status != Status.Created) {
                    throw ApiException.PackageStatusNotValid()
                } else {
                    packageInBagRepository.save(
                        PackageInBag(
                            barcode = barcode,
                            bagBarcode = assignPackageToBagModel.bagBarcode,
                            id = null
                        )
                    )
                    shipmentService.updateShipmentStatus(
                        UpdateStatusModel(
                            barcode = barcode,
                            status = Status.LoadedIntoBag
                        )
                    )
                }
            } ?: run {
                throw ApiException.PackageNotFound()
            }
        }
    }

    @Transactional
    fun unloadWrongLoadedPackage(barcode : String){
        shipmentService.getShipment(barcode)?.let {
            if(it.status == Status.LoadedIntoBag){
                packageInBagRepository.findByBarcode(barcode)?.let {
                    packageInBagRepository.delete(it)
                    shipmentService.updateShipmentStatus(UpdateStatusModel(barcode=barcode, status = Status.Created))
                }
            }
        }
    }

    @Cacheable("bagBarcodeFromBarcode")
    fun getBagBarcodeFromBarcode(barcode: String): PackageInBagModel? {
        return packageInBagRepository.findByBarcode(barcode)?.let { packageInBagDtoConverter.toDto(it) }
    }

    @Cacheable("packagesRelatedBag")
    fun getPackagesRelatedBag(bagBarcode: String): List<PackageInBagModel>? {
        return packageInBagRepository.findByBagBarcode(bagBarcode)?.map { packageInBagDtoConverter.toDto(it) }
    }
}