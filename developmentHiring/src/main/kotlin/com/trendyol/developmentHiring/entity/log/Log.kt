package com.trendyol.developmentHiring.entity.log

import com.trendyol.developmentHiring.util.AuditableEntity
import org.hibernate.annotations.SQLDelete
import java.time.Instant
import javax.persistence.Entity

@Entity
@SQLDelete(sql = "UPDATE log SET active = 0 WHERE id = ?")
open class Log(
    id: Long?,
    open val message: String,
    open val barcode: String
) : AuditableEntity(id = id , createdDate = Instant.now())