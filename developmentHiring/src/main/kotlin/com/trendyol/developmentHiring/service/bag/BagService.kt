package com.trendyol.developmentHiring.service.bag

import com.trendyol.developmentHiring.converter.bag.BagDtoConverter
import com.trendyol.developmentHiring.model.bag.BagModel
import com.trendyol.developmentHiring.model.update.UpdateStatusModel
import com.trendyol.developmentHiring.repository.bag.BagRepository
import org.springframework.stereotype.Service

@Service
class BagService(
    private val bagRepository: BagRepository,
    private val bagDtoConverter: BagDtoConverter
) {
    fun createBag(bagModel: BagModel) {
        bagRepository.save(bagDtoConverter.fromDto(bagModel))
    }

    fun updateBagStatus(updateBarcodeStatusModel: UpdateStatusModel) {
        val entity = bagRepository.findByBarcode(updateBarcodeStatusModel.barcode)
        entity?.let {
            it.status = updateBarcodeStatusModel.status
            bagRepository.save(it)
        }
    }

    fun getBag(barcode : String) : BagModel? {
       return bagRepository.findByBarcode(barcode)?.let { bagDtoConverter.toDto(it) }
    }
}