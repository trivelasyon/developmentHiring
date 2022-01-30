package com.trendyol.developmentHiring.entity.vehicle

import com.trendyol.developmentHiring.util.AuditableEntity
import org.hibernate.annotations.SQLDelete
import java.time.Instant
import javax.persistence.Entity

@Entity
@SQLDelete(sql = "UPDATE vehicle SET active = 0 WHERE id = ?")
open class Vehicle(
    id: Long?,
    open val licensePlate: String
) : AuditableEntity(id = id , createdDate = Instant.now())