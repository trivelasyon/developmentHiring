package com.trendyol.developmentHiring.controller.packageInBag

import com.trendyol.developmentHiring.model.packageInBag.AssignPackageToBagModel
import com.trendyol.developmentHiring.service.packageInBag.PackageInBagService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/packageInBag")
class PackageInBagController(private val packageInBagService: PackageInBagService) {

    @PostMapping("/loadPackagesToBag")
    fun loadPackagesToBag(@RequestBody assignPackageToBagModel: AssignPackageToBagModel) {
        packageInBagService.loadPackagesToBag(assignPackageToBagModel)
    }
    @GetMapping("/unloadWrongLoadedPackage")
    fun unloadWrongLoadedPackage(@PathVariable barcode : String){
        packageInBagService.unloadWrongLoadedPackage(barcode)
    }
}