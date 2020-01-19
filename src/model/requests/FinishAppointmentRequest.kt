package com.diver6ty.chargetapbackend.model.requests

data class FinishAppointmentRequest(
    val id: Int,
    val endTime: String
)