package com.diver6ty.chargetapbackend.dao

import com.diver6ty.chargetapbackend.exceptions.*
import com.diver6ty.chargetapbackend.model.*
import com.diver6ty.chargetapbackend.model.requests.FinishAppointmentRequest
import com.diver6ty.chargetapbackend.model.responses.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import java.util.concurrent.TimeUnit

@Suppress("DuplicatedCode")
class ApplicationDaoImpl(private val db: Database) : ApplicationDao {
    override fun init() = transaction(db) {
        SchemaUtils.create(StationEntity, PowerUnitEntity, AppointmentEntity, CarEntity, UserEntity)
    }

    override fun addCar(car: Car) = transaction(db) {
        CarEntity.insert {
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

    override fun getAllCars(): List<Car> = transaction(db) {
        CarEntity.selectAll().map {
            Car(
                it[CarEntity.id],
                it[CarEntity.year],
                it[CarEntity.brand],
                it[CarEntity.model],
                it[CarEntity.extraInfo],
                it[CarEntity.plate],
                it[CarEntity.powerKwh],
                it[CarEntity.autonomyKm],
                it[CarEntity.photoUrl],
                it[CarEntity.userId]
            )
        }
    }

    override fun getCarsByEmail(email: String): List<CarOfUserResponse> = transaction(db) {
        (CarEntity innerJoin UserEntity).select {
            (CarEntity.userId eq UserEntity.id) and
                    (UserEntity.email eq email)
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

    override fun updateCar(car: Car) = transaction(db) {
        CarEntity.update({ CarEntity.id eq car.id }) {
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

    override fun deleteCarById(id: Int) = transaction(db) {
        CarEntity.deleteWhere { CarEntity.id eq id }
        Unit
    }

    override fun addAppointment(appointment: Appointment) = transaction(db) {
        val powerUnit = getPowerUnitById(appointment.powerUnitId) ?: throw InvalidPowerUnitIDException()

        if (powerUnit.busyNrOutlets >= powerUnit.totalNrOutlets) {
            throw PowerUnitFullException()
        } else {
            updatePowerUnit(powerUnit.copy().apply { busyNrOutlets += 1 })
        }

        AppointmentEntity.insert {
            it[userId] = appointment.userId
            it[powerUnitId] = appointment.powerUnitId
            it[startTime] = appointment.startTime
            it[endTime] = appointment.endTime
        }
        Unit
    }

    override fun getAllAppointments(): List<Appointment> = transaction(db) {
        AppointmentEntity.selectAll().map {
            Appointment(
                it[AppointmentEntity.id],
                it[AppointmentEntity.userId],
                it[AppointmentEntity.powerUnitId],
                it[AppointmentEntity.startTime],
                it[AppointmentEntity.endTime]
            )
        }
    }

    override fun getAppointmentById(id: Int): Appointment? = transaction(db) {
        AppointmentEntity.select {
            AppointmentEntity.id eq id
        }.map {
            Appointment(
                it[AppointmentEntity.id],
                it[AppointmentEntity.userId],
                it[AppointmentEntity.powerUnitId],
                it[AppointmentEntity.startTime],
                it[AppointmentEntity.endTime]
            )
        }.singleOrNull()
    }

    override fun getAppointmentsOfPowerUnit(powerUnitId: Int): List<Appointment> = transaction(db) {
        (AppointmentEntity).select {
            (AppointmentEntity.powerUnitId eq powerUnitId)
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

    override fun getAppointmentsByEmail(email: String): List<UserAppointmentResponse> = transaction(db) {
        val user = getUserByEmail(email) ?: throw InvalidUserException()

        (AppointmentEntity innerJoin PowerUnitEntity innerJoin StationEntity).select {
            (AppointmentEntity.powerUnitId eq PowerUnitEntity.id) and
                    (PowerUnitEntity.stationId eq StationEntity.id) and
                    (AppointmentEntity.userId eq user.id)
        }.map {
            UserAppointmentResponse(
                it[AppointmentEntity.id],
                it[AppointmentEntity.startTime].toString(),
                it[AppointmentEntity.endTime].toString(),
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

    override fun updateAppointment(appointment: Appointment) = transaction(db) {
        AppointmentEntity.update({ AppointmentEntity.id eq appointment.id }) {
            it[userId] = appointment.userId
            it[powerUnitId] = appointment.powerUnitId
            it[startTime] = appointment.startTime
            it[endTime] = appointment.endTime
        }
        Unit
    }

    override fun finishAppointment(finishAppointmentRequest: FinishAppointmentRequest): FinishAppointmentResponse {
        if (finishAppointmentRequest.endTime.isBlank()) {
            throw EndTimeInvalidException()
        }

        val currentAppointment = getAppointmentById(finishAppointmentRequest.id) ?: throw AppointmentNotFoundException()
        if (currentAppointment.endTime.isNotEmpty()) {
            throw AppointmentAlreadyFinishedException()
        }

        val powerUnit = getPowerUnitById(currentAppointment.powerUnitId) ?: throw InvalidPowerUnitIDException()

        if (powerUnit.busyNrOutlets <= 0) {
            throw PowerUnitEmptyException()
        } else {
            updateAppointment(currentAppointment.copy().apply { endTime = finishAppointmentRequest.endTime })
            updatePowerUnit(powerUnit.copy().apply { busyNrOutlets -= 1 })

            val startTime = DateTime(currentAppointment.startTime)
            val endTime = DateTime(finishAppointmentRequest.endTime)
            val differenceMillis = endTime.millis - startTime.millis
            val differenceMinutes = TimeUnit.MILLISECONDS.toMinutes(differenceMillis)

            val differenceHours: Double = differenceMinutes.toDouble() / 60
            val pricePerKwh = powerUnit.priceKwh * differenceHours

            return FinishAppointmentResponse(pricePerKwh, differenceHours)
        }
    }

    override fun deleteAppointmentById(id: Int) = transaction(db) {
        AppointmentEntity.deleteWhere {
            AppointmentEntity.id eq id
        }
        Unit
    }

    override fun addPowerUnit(powerUnit: PowerUnit) = transaction(db) {
        PowerUnitEntity.insert {
            it[stationId] = powerUnit.stationId
            it[powerKw] = powerUnit.powerKw
            it[priceKwh] = powerUnit.priceKwh
            it[totalNrOutlets] = powerUnit.totalNrOutlets
            it[busyNrOutlets] = powerUnit.busyNrOutlets
        }
        Unit
    }

    override fun getAllPowerUnits(): List<PowerUnit> = transaction(db) {
        PowerUnitEntity.selectAll().map {
            PowerUnit(
                it[PowerUnitEntity.id],
                it[PowerUnitEntity.stationId],
                it[PowerUnitEntity.powerKw],
                it[PowerUnitEntity.priceKwh],
                it[PowerUnitEntity.totalNrOutlets],
                it[PowerUnitEntity.busyNrOutlets]
            )
        }
    }

    override fun getPowerUnitById(id: Int): PowerUnit? = transaction(db) {
        PowerUnitEntity.select {
            PowerUnitEntity.id eq id
        }.map {
            PowerUnit(
                it[PowerUnitEntity.id],
                it[PowerUnitEntity.stationId],
                it[PowerUnitEntity.powerKw],
                it[PowerUnitEntity.priceKwh],
                it[PowerUnitEntity.totalNrOutlets],
                it[PowerUnitEntity.busyNrOutlets]
            )
        }.singleOrNull()
    }

    override fun updatePowerUnit(powerUnit: PowerUnit) = transaction(db) {
        PowerUnitEntity.update({ PowerUnitEntity.id eq powerUnit.id }) {
            it[stationId] = powerUnit.stationId
            it[powerKw] = powerUnit.powerKw
            it[priceKwh] = powerUnit.priceKwh
            it[totalNrOutlets] = powerUnit.totalNrOutlets
            it[busyNrOutlets] = powerUnit.busyNrOutlets
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

    override fun deletePowerUnitById(id: Int) = transaction(db) {
        PowerUnitEntity.deleteWhere { PowerUnitEntity.id eq id }
        Unit
    }

    override fun addUser(user: User) = transaction(db) {
        if (getUserByEmail(user.email) != null) {
            throw UserWithEmailAlreadyExistsException()
        }

        UserEntity.insert {
            it[name] = user.name
            it[email] = user.email
            it[password] = user.password
            it[profilePictureUrl] = user.profilePictureUrl
        }
        Unit
    }

    override fun getAllUsers(): List<User> = transaction(db) {
        UserEntity.selectAll().map {
            User(
                it[UserEntity.id],
                it[UserEntity.name],
                it[UserEntity.email],
                it[UserEntity.password],
                it[UserEntity.profilePictureUrl]
            )
        }
    }

    override fun getUserByEmail(email: String): User? = transaction(db) {
        UserEntity.select {
            UserEntity.email eq email
        }.map {
            User(
                it[UserEntity.id],
                it[UserEntity.name],
                it[UserEntity.email],
                it[UserEntity.password],
                it[UserEntity.profilePictureUrl]
            )
        }.singleOrNull()
    }

    override fun updateUser(user: User) = transaction(db) {
        UserEntity.update({ UserEntity.id eq user.id }) {
            it[name] = user.name
            it[email] = user.email
            it[password] = user.password
            it[profilePictureUrl] = user.profilePictureUrl
        }
        Unit
    }

    override fun deleteUserById(id: Int) = transaction(db) {
        UserEntity.deleteWhere { UserEntity.id eq id }
        Unit
    }

    override fun addStation(station: Station) = transaction(db) {
        StationEntity.insert {
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

    override fun getAllStations(): List<StationAvailableResponse> = transaction(db) {
        StationEntity.selectAll().map {
            val powerUnits = getPowerUnitsOfStation(it[StationEntity.id])
            val availablePowerUnits = powerUnits.count { powerUnit -> powerUnit.busyNrOutlets < powerUnit.totalNrOutlets }

            StationAvailableResponse(
                it[StationEntity.id],
                it[StationEntity.name],
                it[StationEntity.address],
                it[StationEntity.photoUrl],
                it[StationEntity.latitude],
                it[StationEntity.longitude],
                it[StationEntity.rating],
                it[StationEntity.nrReviews],
                availablePowerUnits,
                powerUnits.size
            )
        }
    }

    override fun getStationsByKeyword(keyword: String): List<StationAvailableResponse> = transaction(db) {
        StationEntity.select {
            StationEntity.address.lowerCase() like "%${keyword.toLowerCase()}%"
        }.map {
            val powerUnits = getPowerUnitsOfStation(it[StationEntity.id])
            val availablePowerUnits = powerUnits.count { powerUnit -> powerUnit.busyNrOutlets < powerUnit.totalNrOutlets }

            StationAvailableResponse(
                it[StationEntity.id],
                it[StationEntity.name],
                it[StationEntity.address],
                it[StationEntity.photoUrl],
                it[StationEntity.latitude],
                it[StationEntity.longitude],
                it[StationEntity.rating],
                it[StationEntity.nrReviews],
                availablePowerUnits,
                powerUnits.size
            )
        }
    }

    override fun updateStation(station: Station) = transaction(db) {
        StationEntity.update({ StationEntity.id eq station.id }) {
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

    override fun deleteStationById(id: Int) = transaction(db) {
        StationEntity.deleteWhere { StationEntity.id eq id }
        Unit
    }

    override fun close() {

    }
}