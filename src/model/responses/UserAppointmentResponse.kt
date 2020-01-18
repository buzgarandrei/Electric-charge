package com.diver6ty.chargetapbackend.model.responses

import com.diver6ty.chargetapbackend.model.Station
import org.joda.time.DateTime

data class UserAppointmentResponse(
    val id: Int,
    val startTime: DateTime,
    val endTime: DateTime,
    val powerUnitInfo: UserAppointmentPowerUnitResponse
) {
    data class UserAppointmentPowerUnitResponse(
        val powerUnit: PowerUnitOfStationResponse,
        val station: Station
    )
}