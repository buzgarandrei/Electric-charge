package com.diver6ty.chargetapbackend.dao

import com.diver6ty.chargetapbackend.model.*
import com.diver6ty.chargetapbackend.model.responses.CarOfUserResponse
import com.diver6ty.chargetapbackend.model.responses.PowerUnitOfStationResponse
import com.diver6ty.chargetapbackend.model.responses.UserAppointmentResponse
import kotlinx.io.core.Closeable

interface ApplicationDao: Closeable {
    fun init()

    fun addAppointment(appointment: Appointment)
    fun addCar(car: Car)
    fun addPowerUnit(powerUnit: PowerUnit)
    fun addStation(station: Station)
    fun addUser(user: User)

    //fun getAllCars(): List<Car>
    fun getCarsOfUser(userId: Int): List<CarOfUserResponse>

    //fun getAllAppointments(): List<Appointment>
    fun getAppointmentsOfUser(userId: Int): List<UserAppointmentResponse>
    //fun deleteAppointmentById(id: Int)

    //fun getAllPowerUnits(): List<PowerUnit>
    fun getPowerUnitsOfStation(stationId: Int): List<PowerUnitOfStationResponse>
    //fun deletePowerUnitById(id: Int)

    //fun getAllUsers(): List<User>
    fun deleteUserById(id: Int)

    //fun getAllStations(): List<Station>
    fun getAllStationsByKeyword(keyword: String): List<Station>
}