package com.trendyol.developmentHiring.entity.deliveryPoint

import com.trendyol.developmentHiring.util.AuditableEntity
import org.hibernate.annotations.SQLDelete
import java.time.Instant
import javax.persistence.Entity

@Entity
@SQLDelete(sql = "UPDATE delivery_point SET active = 0 WHERE id = ?")
open class DeliveryPoint(
    id: Long?,
    open val deliveryPoint: Int,
    open val name: String
) : AuditableEntity(id = id , createdDate = Instant.now())