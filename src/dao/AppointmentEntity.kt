package com.diver6ty.chargetapbackend.dao

import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

object AppointmentEntity : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val userId = integer("userId")
    val powerUnitId = integer("powerUnitId") references PowerUnitEntity.id
    val startTime = datetime("startTime")
    val endTime = datetime("endTime")
}