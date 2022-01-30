package com.trendyol.developmentHiring.validation
import org.springframework.stereotype.Component
import java.util.regex.Matcher
import java.util.regex.Pattern

@Component
class VehicleValidation {
    fun isLicensePlateValid(licensePlate : String) : Boolean {
        val plateRegex = Regex(
            "^(0[1-9]|[1-7][0-9]|8[01])((\\s?[a-zA-Z]\\s?)(\\d{4,5})|(\\s?[a-zA-Z]{2}\\s?)(\\d{3,4})|(\\s?[a-zA-Z]{3}\\s?)(\\d{2,3}))\$"
        )
        return (licensePlate.matches(plateRegex))
    }
}
