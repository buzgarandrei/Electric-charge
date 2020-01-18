package com.diver6ty.chargetapbackend.model

data class Car(
    val id: Int,
    val year: Int,
    val brand: String,
    val model: String,
    val extraInfo: String,
    val plate: String,
    val powerKwh: Int,
    val autonomyKm: Int,
    val photoUrl: String,
    val userId: Int
)