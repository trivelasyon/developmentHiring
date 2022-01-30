package com.trendyol.developmentHiring.converter.packageInBag

import com.trendyol.developmentHiring.entity.packageInBag.PackageInBag
import com.trendyol.developmentHiring.model.packageInBag.PackageInBagModel
import com.trendyol.developmentHiring.util.DtoConverter
import org.springframework.stereotype.Component

@Component
class PackageInBagDtoConverter : DtoConverter<PackageInBag, PackageInBagModel> {
    override fun fromDto(model: PackageInBagModel): PackageInBag {
        return PackageInBag(
            id = null,
            bagBarcode = model.bagBarcode,
            barcode= model.barcode
        )
    }

    override fun toDto(entity: PackageInBag): PackageInBagModel {
        return PackageInBagModel(
            bagBarcode = entity.bagBarcode,
            barcode= entity.barcode
        )
    }
}