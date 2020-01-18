package com.diver6ty.chargetapbackend.model

data class PowerUnit(
    val id: Int,
    val stationId: Int,
    val powerKw: Int,
    val priceKwh: Double,
    val totalNrOutlets: Int,
    var busyNrOutlets: Int
)