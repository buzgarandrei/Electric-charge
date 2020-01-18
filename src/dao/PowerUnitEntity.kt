package com.diver6ty.chargetapbackend.dao

import org.jetbrains.exposed.sql.Table

object PowerUnitEntity: Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val stationId = integer("stationId") references StationEntity.id
    val powerKw = integer("powerKw")
    val priceKwh = double("priceKwh")
    val totalNrOutlets = integer("totalNrOutlets")
    val busyNrOutlets = integer("busyNrOutlets")
}