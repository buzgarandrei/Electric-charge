package com.diver6ty.chargetapbackend.model.responses

data class StationAvailableResponse(
    val id: Int,
    val name: String,
    val address: String,
    val photoUrl: String,
    val latitude: Double,
    val longitude: Double,
    val rating: Int,
    val nrReviews: Int,
    val availablePowerUnits: Int,
    val totalPowerUnits: Int
)