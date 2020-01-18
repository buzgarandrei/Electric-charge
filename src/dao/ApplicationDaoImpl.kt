package com.diver6ty.chargetapbackend.dao

import com.diver6ty.chargetapbackend.model.*
import com.diver6ty.chargetapbackend.model.responses.CarOfUserResponse
import com.diver6ty.chargetapbackend.model.responses.PowerUnitOfStationResponse
import com.diver6ty.chargetapbackend.model.responses.UserAppointmentResponse
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ApplicationDaoImpl(private val db: Database) : ApplicationDao {
    override fun init() = transaction(db) {
        SchemaUtils.create(StationEntity, PowerUnitEntity, AppointmentEntity, CarEntity, UserEntity)
    }

    override fun addAppointment(appointment: Appointment) = transaction(db) {
        AppointmentEntity.insert {
            it[id] = appointment.id
            it[userId] = appointment.userId
            it[powerUnitId] = appointment.powerUnitId
            it[startTime] = appointment.startTime
            it[endTime] = appointment.endTime
        }
        Unit
    }

    override fun addCar(car: Car) = transaction(db) {
        CarEntity.insert {
            it[id] = car.id
            it[year] = car.year
            it[brand] = car.brand
            it[model] = car.model
            it[extraInfo] = car.extraInfo
            it[plate] = car.plate
            it[powerKwh] = car.powerKwh
            it[autonomyKm] = car.autonomyKm
            it[photoUrl] = car.photoUrl
            it[userId] = car.userId
        }
        Unit
    }

    override fun addPowerUnit(powerUnit: PowerUnit) = transaction(db) {
        PowerUnitEntity.insert {
            it[id] = powerUnit.id
            it[stationId] = powerUnit.stationId
            it[powerKw] = powerUnit.powerKw
            it[priceKwh] = powerUnit.priceKwh
            it[totalNrOutlets] = powerUnit.totalNrOutlets
            it[busyNrOutlets] = powerUnit.busyNrOutlets
        }
        Unit
    }

    override fun addStation(station: Station) = transaction(db) {
        StationEntity.insert {
            it[id] = station.id
            it[name] = station.name
            it[address] = station.address
            it[photoUrl] = station.photoUrl
            it[latitude] = station.latitude
            it[longitude] = station.longitude
            it[rating] = station.rating
            it[nrReviews] = station.nrReviews
        }
        Unit
    }

    override fun addUser(user: User) = transaction(db) {
        UserEntity.insert {
            it[id] = user.id
            it[name] = user.name
            it[email] = user.email
            it[password] = user.password
            it[profilePictureUrl] = user.profilePictureUrl
        }
        Unit
    }

    override fun getCarsOfUser(userId: Int): List<CarOfUserResponse> = transaction(db) {
        CarEntity.select {
            CarEntity.userId eq userId
        }.map {
            CarOfUserResponse(
                it[CarEntity.id],
                it[CarEntity.year],
                it[CarEntity.brand],
                it[CarEntity.model],
                it[CarEntity.extraInfo],
                it[CarEntity.plate],
                it[CarEntity.powerKwh],
                it[CarEntity.autonomyKm],
                it[CarEntity.photoUrl]
            )
        }
    }

    override fun getAppointmentsOfPowerUnit(powerUnitId: Int): List<Appointment> = transaction(db) {
        AppointmentEntity.select {
            AppointmentEntity.powerUnitId eq powerUnitId
        }.map {
            Appointment(
                it[AppointmentEntity.id],
                it[AppointmentEntity.userId],
                it[AppointmentEntity.powerUnitId],
                it[AppointmentEntity.startTime],
                it[AppointmentEntity.endTime]
            )
        }
    }

    override fun getAppointmentsOfUser(userId: Int): List<UserAppointmentResponse> = transaction(db) {
        (AppointmentEntity innerJoin PowerUnitEntity innerJoin StationEntity).select {
            (AppointmentEntity.powerUnitId eq PowerUnitEntity.id) and
                    (PowerUnitEntity.stationId eq StationEntity.id) and
                    (AppointmentEntity.userId eq userId)
        }.map {
            UserAppointmentResponse(
                it[AppointmentEntity.id],
                it[AppointmentEntity.startTime],
                it[AppointmentEntity.endTime],
                UserAppointmentResponse.UserAppointmentPowerUnitResponse(
                    PowerUnitOfStationResponse(
                        it[PowerUnitEntity.id],
                        it[PowerUnitEntity.powerKw],
                        it[PowerUnitEntity.priceKwh],
                        it[PowerUnitEntity.totalNrOutlets],
                        it[PowerUnitEntity.busyNrOutlets]
                    ),
                    Station(
                        it[StationEntity.id],
                        it[StationEntity.name],
                        it[StationEntity.address],
                        it[StationEntity.photoUrl],
                        it[StationEntity.latitude],
                        it[StationEntity.longitude],
                        it[StationEntity.rating],
                        it[StationEntity.nrReviews]
                    )
                )
            )
        }
    }

    override fun deleteAppointmentById(id: Int) = transaction(db) {
        AppointmentEntity.deleteWhere {
            AppointmentEntity.id eq id
        }
        Unit
    }

    override fun getPowerUnitsOfStation(stationId: Int): List<PowerUnitOfStationResponse> = transaction(db) {
        PowerUnitEntity.select {
            PowerUnitEntity.stationId eq stationId
        }.map {
            PowerUnitOfStationResponse(
                it[PowerUnitEntity.id],
                it[PowerUnitEntity.powerKw],
                it[PowerUnitEntity.priceKwh],
                it[PowerUnitEntity.totalNrOutlets],
                it[PowerUnitEntity.busyNrOutlets]
            )
        }
    }

    override fun getStationsByKeyword(keyword: String): List<Station> = transaction(db) {
        StationEntity.select {
            StationEntity.address like "%$keyword%"
        }.map {
            Station(
                it[StationEntity.id],
                it[StationEntity.name],
                it[StationEntity.address],
                it[StationEntity.photoUrl],
                it[StationEntity.latitude],
                it[StationEntity.longitude],
                it[StationEntity.rating],
                it[StationEntity.nrReviews]
            )
        }
    }

    override fun close() {

    }
}