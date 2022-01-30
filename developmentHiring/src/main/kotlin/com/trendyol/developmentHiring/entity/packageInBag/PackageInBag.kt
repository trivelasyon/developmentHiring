package com.trendyol.developmentHiring.entity.packageInBag

import com.trendyol.developmentHiring.util.AuditableEntity
import org.hibernate.annotations.SQLDelete
import java.time.Instant
import javax.persistence.Entity

@Entity
@SQLDelete(sql = "UPDATE package_in_bag SET active = 0 WHERE id = ?")
open class PackageInBag(
    id: Long?,
    open val bagBarcode: String,
    open val barcode: String
) : AuditableEntity(id = id , createdDate = Instant.now())