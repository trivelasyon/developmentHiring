package com.trendyol.developmentHiring.util

import org.hibernate.annotations.Where
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@Where(clause = "active = true")
abstract class AuditableEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    open var id: Long?,

    @Column(nullable = false, updatable = false)
    open var active: Boolean = true,

    @CreatedBy
    @Column(nullable = false, updatable = false)
    open var createdBy: String = "admin",

    @CreatedDate
    @Column(nullable = false, updatable = false)
    open var createdDate: Instant = Instant.now()
)
