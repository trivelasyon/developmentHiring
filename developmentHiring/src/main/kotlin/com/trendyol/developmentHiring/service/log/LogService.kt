package com.trendyol.developmentHiring.service.log

import com.trendyol.developmentHiring.entity.log.Log
import com.trendyol.developmentHiring.model.validation.ValidationResponseModel
import com.trendyol.developmentHiring.repository.log.LogRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class LogService
    (
    @Value("\${barcodeStatusNotValid}")
    private val barcodeStatusNotValid: String,
    @Value("\${deliveryPointNotValid}")
    private val deliveryPointNotValid: String,
    @Value("\${deliveryPointTypeNotValid}")
    private val deliveryPointTypeNotValid: String,
    private val logRepository: LogRepository
) {
    fun insertLogTable(barcode: String, flag: ValidationResponseModel) {
        val logMessage = prepareLogMessage(flag)
        logRepository.save(
            Log(
                id = null,
                message = logMessage,
                barcode = barcode
            )
        )
    }

    private fun prepareLogMessage(flag: ValidationResponseModel): String {
        var message = "Barcode is not valid due to "
        if (!flag.isBarcodeStatusValid)
            message = message + barcodeStatusNotValid
        if (!flag.isDeliveryPointValid)
            message = message + deliveryPointNotValid
        if (!flag.isDeliveryPointTypeValid)
            message = message + deliveryPointTypeNotValid
        return message
    }
}