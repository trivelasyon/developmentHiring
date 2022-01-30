package com.trendyol.developmentHiring.repository.log

import com.trendyol.developmentHiring.entity.log.Log
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LogRepository  : CrudRepository<Log, Long>