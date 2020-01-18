package com.diver6ty.chargetapbackend.model

import org.joda.time.DateTime

data class Appointment(
    val id: Int,
    val userId: Int,
    val powerUnitId: Int,
    val startTime: DateTime,
    val endTime: DateTime
)