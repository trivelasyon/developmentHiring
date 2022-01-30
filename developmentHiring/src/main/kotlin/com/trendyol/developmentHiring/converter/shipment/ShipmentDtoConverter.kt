package com.trendyol.developmentHiring.converter.shipment

import com.trendyol.developmentHiring.model.shipment.ShipmentModel
import com.trendyol.developmentHiring.entity.shipment.Shipment
import com.trendyol.developmentHiring.util.DtoConverter
import org.springframework.stereotype.Component

@Component
class ShipmentDtoConverter : DtoConverter<Shipment, ShipmentModel> {
    override fun fromDto(model: ShipmentModel): Shipment {
        return Shipment(
            id = null,
            deliveryPoint = model.deliveryPoint,
            barcode= model.barcode,
            volumetricWeight = model.volumetricWeight,
            status = model.status
        )
    }

    override fun toDto(entity: Shipment): ShipmentModel {
        return ShipmentModel(
            deliveryPoint = entity.deliveryPoint,
            barcode= entity.barcode,
            volumetricWeight = entity.volumetricWeight,
            status = entity.status
        )
    }
}