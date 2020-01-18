package com.diver6ty.chargetapbackend.model.responses

import com.diver6ty.chargetapbackend.model.Station

data class PowerUnitOfStationResponse(
    val id: Int,
    val powerKw: Int,
    val priceKwh: Double,
    val totalNrOutlets: Int,
    val busyNrOutlets: Int
)