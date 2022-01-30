package com.trendyol.developmentHiring.controller.bag

import com.trendyol.developmentHiring.model.bag.BagModel
import com.trendyol.developmentHiring.service.bag.BagService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bag")
class BagController(private val bagService: BagService) {

    @PostMapping("/createBag")
    fun createBag(@RequestBody bagModel: BagModel) {
        bagService.createBag(bagModel)
    }
}