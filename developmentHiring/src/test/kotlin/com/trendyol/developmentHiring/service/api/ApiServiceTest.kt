package com.trendyol.developmentHiring.service.api

import com.trendyol.developmentHiring.entity.shipment.Shipment
import com.trendyol.developmentHiring.enum.ShipmentType
import com.trendyol.developmentHiring.enum.Status
import com.trendyol.developmentHiring.exception.ApiException
import com.trendyol.developmentHiring.model.api.BarcodeModel
import com.trendyol.developmentHiring.model.api.RequestModel
import com.trendyol.developmentHiring.model.api.RouteModel
import com.trendyol.developmentHiring.model.bag.BagModel
import com.trendyol.developmentHiring.model.shipment.ShipmentModel
import com.trendyol.developmentHiring.model.update.UpdateStatusModel
import com.trendyol.developmentHiring.service.bag.BagService
import com.trendyol.developmentHiring.service.log.LogService
import com.trendyol.developmentHiring.service.packageInBag.PackageInBagService
import com.trendyol.developmentHiring.service.shipment.ShipmentService
import com.trendyol.developmentHiring.validation.PackageBagValidation
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.InjectMocks
import org.mockito.Mockito.times


@ExtendWith(MockitoExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApiServiceTest {
    private lateinit var shipmentModel : ShipmentModel
    private lateinit var bagModel : BagModel
    private lateinit var requestModel : RequestModel
    private lateinit var updatedModel : RequestModel
    private lateinit var shipment : Shipment
    @InjectMocks
    lateinit var apiService: ApiService
    @Mock
    lateinit var logService: LogService
    @Mock
    lateinit var bagService: BagService
    @Mock
    lateinit var shipmentService: ShipmentService
    @Mock
    lateinit var packageInBagService: PackageInBagService
    @Mock
    lateinit var packageBagValidation: PackageBagValidation

    @BeforeEach
fun initiliazeModels(){
        shipment = Shipment(
            deliveryPoint = 1,
            barcode = "P7988000121",
            volumetricWeight = 33,
            status = Status.Created,
            id = 1
        )
        shipmentModel = ShipmentModel(
            deliveryPoint = 1,
            barcode = "P7988000121",
            volumetricWeight = 33,
            status = Status.Created
        )

        bagModel = BagModel(
            deliveryPoint = 1,
            barcode = "C725799",
            status = Status.Created
        )

        requestModel =  RequestModel(
            licensePlate = "34 NR 2530",
            route = listOf(
                RouteModel(
                    1,
                    deliveries = listOf(BarcodeModel("P7988000121", state = Status.Created),BarcodeModel("C725799", state = Status.Created))
                )
            )
        )
        updatedModel = RequestModel(
            licensePlate = "34 NR 2530",
            route = listOf(
                RouteModel(
                    1,
                    deliveries = listOf(BarcodeModel("P7988000121", state = Status.Loaded))
                )
            )
        )


}
    @Test
    fun verifyThrowErrorFromDecideShipmentType() {
        Mockito.`when`(shipmentService.getShipment(ArgumentMatchers.anyString())).thenReturn(null)
        Mockito.`when`(bagService.getBag(ArgumentMatchers.anyString())).thenReturn(null)
        assertThrows(
            ApiException.InvalidBarcode::class.java,
            { apiService.decideShipmentType(ArgumentMatchers.anyString()) }
        )
    }

    @Test
    fun getShipmentTypeBag() {

        Mockito.`when`(shipmentService.getShipment(ArgumentMatchers.anyString())).thenReturn(shipmentModel)
        Mockito.`when`(bagService.getBag(ArgumentMatchers.anyString())).thenReturn(null)
        assertEquals(apiService.decideShipmentType(ArgumentMatchers.anyString()), ShipmentType.Shipment)
    }

    @Test
    fun getShipmentTypePackage() {

        Mockito.`when`(shipmentService.getShipment(ArgumentMatchers.anyString())).thenReturn(null)
        Mockito.`when`(bagService.getBag(ArgumentMatchers.anyString())).thenReturn(bagModel)
        assertEquals(apiService.decideShipmentType(ArgumentMatchers.anyString()), ShipmentType.Bag)

    }

    @Test
    fun vehicleNotRegisterSystemThrowException() {

        Mockito.`when`(packageBagValidation.isVehicleNotRegisterInSystem(ArgumentMatchers.anyString())).thenReturn(true)
        assertThrows(
            ApiException.VehicleNotFound::class.java,
            { apiService.loadShipmentToVehicle(requestModel) }
        )
    }

    @Test
    fun shipmentStatusToLoadedAfterLoadToVehicle(){
        Mockito.`when`(shipmentService.getShipment(ArgumentMatchers.anyString())).thenReturn(shipmentModel)
        Mockito.`when`(bagService.getBag(ArgumentMatchers.anyString())).thenReturn(null)
        apiService.loadShipmentToVehicle(requestModel)
        Mockito.verify(shipmentService, times(1)).updateShipmentStatus(UpdateStatusModel(barcode = "P7988000121",
            status = Status.Loaded))
    }

    @Test
    fun bagStatusToLoadedAfterLoadToVehicle(){
        Mockito.`when`(shipmentService.getShipment(ArgumentMatchers.anyString())).thenReturn(null)
        Mockito.`when`(bagService.getBag(ArgumentMatchers.anyString())).thenReturn(bagModel)
        apiService.loadShipmentToVehicle(requestModel)
        Mockito.verify(bagService, times(1)).updateBagStatus(UpdateStatusModel(barcode = "C725799",
            status = Status.Loaded))
    }

    @Test
    fun unloadShipmentFromVehicle() {


    }
}