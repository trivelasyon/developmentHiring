package com.trendyol.developmentHiring.service.deliveryPoint

import com.trendyol.developmentHiring.converter.deliveryPoint.DeliveryPointDtoConverter
import com.trendyol.developmentHiring.model.deliveryPoint.DeliveryPointModel
import com.trendyol.developmentHiring.repository.deliveryPoint.DeliveryPointRepository
import org.springframework.stereotype.Service

@Service
class DeliveryPointService(
    private val deliveryPointRepository: DeliveryPointRepository,
    private val deliveryPointDtoConverter: DeliveryPointDtoConverter
) {
    fun createDeliveryPoint(deliveryPointModel: DeliveryPointModel) {
        deliveryPointRepository.save(deliveryPointDtoConverter.fromDto(deliveryPointModel))
    }
}