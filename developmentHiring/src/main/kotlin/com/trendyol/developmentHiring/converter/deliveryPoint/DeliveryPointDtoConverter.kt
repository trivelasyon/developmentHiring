package com.trendyol.developmentHiring.converter.deliveryPoint

import com.trendyol.developmentHiring.entity.deliveryPoint.DeliveryPoint
import com.trendyol.developmentHiring.model.deliveryPoint.DeliveryPointModel
import com.trendyol.developmentHiring.util.DtoConverter
import org.springframework.stereotype.Component

@Component
class DeliveryPointDtoConverter : DtoConverter<DeliveryPoint, DeliveryPointModel> {
    override fun fromDto(model: DeliveryPointModel): DeliveryPoint {
        return DeliveryPoint(
            id = null,
            deliveryPoint = model.deliveryPoint,
            name= model.name
        )
    }

    override fun toDto(entity: DeliveryPoint): DeliveryPointModel {
        return DeliveryPointModel(
            deliveryPoint = entity.deliveryPoint,
            name= entity.name
        )
    }
}