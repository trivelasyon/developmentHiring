package com.trendyol.developmentHiring.entity.shipment

import com.trendyol.developmentHiring.enum.Status
import com.trendyol.developmentHiring.util.AuditableEntity
import org.hibernate.annotations.SQLDelete
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
@SQLDelete(sql = "UPDATE shipment SET active = 0 WHERE id = ?")
open class Shipment(
    id: Long?,
    open val deliveryPoint: Int,
    open val barcode: String,
    open val volumetricWeight: Int,
    @Enumerated(EnumType.STRING)
    open var status: Status = Status.Created
) : AuditableEntity(id = id , createdDate = Instant.now())