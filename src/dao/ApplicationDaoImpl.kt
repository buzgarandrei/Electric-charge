package com.diver6ty.chargetapbackend.dao

import com.diver6ty.chargetapbackend.model.Appointment
import com.diver6ty.chargetapbackend.model.PowerUnit
import com.diver6ty.chargetapbackend.model.Station
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class ApplicationDaoImpl(private val db: Database) : ApplicationDao {
    override fun init() = transaction(db) {
        SchemaUtils.create(StationEntity, PowerUnitEntity, AppointmentEntity)
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
            it[address] = station.address
            it[latitude] = station.latitude
            it[longitude] = station.longitude
        }
        Unit
    }

    override fun addAppointment(appointment: Appointment) = transaction(db) {
        AppointmentEntity.insert {
            it[id] = appointment.id
            it[powerUnitId] = appointment.powerUnitId
            it[startTime] = appointment.startTime
            it[endTime] = appointment.endTime
        }
        Unit
    }

    override fun close() {

    }
}