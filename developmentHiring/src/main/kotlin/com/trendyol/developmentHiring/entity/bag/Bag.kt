package com.trendyol.developmentHiring.entity.bag

import com.trendyol.developmentHiring.enum.Status
import com.trendyol.developmentHiring.util.AuditableEntity
import org.hibernate.annotations.SQLDelete
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
@SQLDelete(sql = "UPDATE bag SET active = 0 WHERE id = ?")
open class Bag(
    id: Long?,
    open val deliveryPoint: Int,
    open val barcode: String,
    @Enumerated(EnumType.STRING)
    open var status: Status = Status.Created
) : AuditableEntity(id = id , createdDate = Instant.now())