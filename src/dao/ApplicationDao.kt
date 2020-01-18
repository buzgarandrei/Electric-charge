package com.diver6ty.chargetapbackend.dao

import com.diver6ty.chargetapbackend.model.*
import com.diver6ty.chargetapbackend.model.responses.CarOfUserResponse
import com.diver6ty.chargetapbackend.model.responses.PowerUnitOfStationResponse
import com.diver6ty.chargetapbackend.model.responses.UserAppointmentResponse
import kotlinx.io.core.Closeable

interface ApplicationDao : Closeable {
    fun init()

    fun addAppointment(appointment: Appointment)
    fun addCar(car: Car)
    fun addPowerUnit(powerUnit: PowerUnit)
    fun addStation(station: Station)
    fun addUser(user: User)

    fun getAllCars(): List<Car>
    fun getCarsOfUser(userId: Int): List<CarOfUserResponse>

    fun getAllAppointments(): List<Appointment>
    fun getAppointmentsOfPowerUnit(powerUnitId: Int): List<Appointment>
    fun getAppointmentsOfUser(userId: Int): List<UserAppointmentResponse>
    fun deleteAppointmentById(id: Int)

    fun getAllPowerUnits(): List<PowerUnit>
    fun getPowerUnitById(id: Int): PowerUnit?
    fun getPowerUnitsOfStation(stationId: Int): List<PowerUnitOfStationResponse>
    fun updatePowerUnit(id: Int, powerUnit: PowerUnit)

    fun getAllUsers(): List<User>
    fun getUserByEmail(email: String): User?

    fun getAllStations(): List<Station>
    fun getStationsByKeyword(keyword: String): List<Station>
}