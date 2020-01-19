package com.diver6ty.chargetapbackend.dao

import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

object AppointmentEntity : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val userId = integer("userId") references UserEntity.id
    val powerUnitId = integer("powerUnitId") references PowerUnitEntity.id
    val startTime = text("startTime")
    val endTime = text("endTime")
}