package com.diver6ty.chargetapbackend.dao

import com.diver6ty.chargetapbackend.model.*
import com.diver6ty.chargetapbackend.model.requests.FinishAppointmentRequest
import com.diver6ty.chargetapbackend.model.responses.*
import kotlinx.io.core.Closeable

interface ApplicationDao : Closeable {
    fun init()

    fun addCar(car: Car)
    fun getAllCars(): List<Car>
    fun getCarsByEmail(email: String): List<CarOfUserResponse>
    fun updateCar(car: Car)
    fun deleteCarById(id: Int)

    fun addAppointment(appointment: Appointment)
    fun getAllAppointments(): List<Appointment>
    fun getAppointmentById(id: Int): Appointment?
    fun getAppointmentsOfPowerUnit(powerUnitId: Int): List<Appointment>
    fun getAppointmentsByEmail(email: String): List<UserAppointmentResponse>
    fun updateAppointment(appointment: Appointment)
    fun finishAppointment(finishAppointmentRequest: FinishAppointmentRequest): FinishAppointmentResponse
    fun deleteAppointmentById(id: Int)

    fun addPowerUnit(powerUnit: PowerUnit)
    fun getAllPowerUnits(): List<PowerUnit>
    fun getPowerUnitById(id: Int): PowerUnit?
    fun getPowerUnitsOfStation(stationId: Int): List<PowerUnitOfStationResponse>
    fun updatePowerUnit(powerUnit: PowerUnit)
    fun deletePowerUnitById(id: Int)

    fun addUser(user: User)
    fun getAllUsers(): List<User>
    fun getUserByEmail(email: String): User?
    fun updateUser(user: User)
    fun deleteUserById(id: Int)

    fun addStation(station: Station)
    fun getAllStations(): List<StationAvailableResponse>
    fun getStationsByKeyword(keyword: String): List<StationAvailableResponse>
    fun updateStation(station: Station)
    fun deleteStationById(id: Int)
}