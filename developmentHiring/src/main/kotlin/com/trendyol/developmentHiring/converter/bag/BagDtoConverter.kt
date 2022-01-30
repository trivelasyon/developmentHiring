package com.trendyol.developmentHiring.converter.bag

import com.trendyol.developmentHiring.entity.bag.Bag
import com.trendyol.developmentHiring.model.bag.BagModel
import com.trendyol.developmentHiring.util.DtoConverter
import org.springframework.stereotype.Component

@Component
class BagDtoConverter : DtoConverter<Bag, BagModel> {
    override fun fromDto(model: BagModel): Bag {
        return Bag(
            id = null,
            deliveryPoint = model.deliveryPoint,
            barcode= model.barcode,
            status = model.status
        )
    }

    override fun toDto(entity: Bag): BagModel {
        return BagModel(
            deliveryPoint = entity.deliveryPoint,
            barcode= entity.barcode,
            status = entity.status
        )
    }
}