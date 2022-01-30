package com.trendyol.developmentHiring.repository.deliveryPoint

import com.trendyol.developmentHiring.entity.deliveryPoint.DeliveryPoint
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeliveryPointRepository : CrudRepository<DeliveryPoint, Long>