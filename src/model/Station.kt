package com.diver6ty.chargetapbackend.model

data class Station(
    val id: Int,
    val name: String,
    val address: String,
    val photoUrl: String,
    val latitude: Double,
    val longitude: Double,
    val rating: Int,
    val nrReviews: Int
)