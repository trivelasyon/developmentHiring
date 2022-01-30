package com.trendyol.developmentHiring.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

class ApiException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    open class BadRequest(message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
        constructor(cause: Throwable) : this(null, cause)
    }

    class LicensePlateNotValid : BadRequest("License plate is not valid")
    class PackageStatusNotValid: BadRequest("Package status is not valid")
    class PackageNotFound:  BadRequest("Package is not found")
    class VehicleNotFound:  BadRequest("Vehicle is not registered into system")
    class InvalidBarcode:  BadRequest("Invalid Barcode Error")
}