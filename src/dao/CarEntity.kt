package com.diver6ty.chargetapbackend.dao

import org.jetbrains.exposed.sql.Table

object CarEntity: Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val year = integer("year")
    val brand = varchar("brand", 30)
    val model = varchar("model", 50)
    val extraInfo = varchar("extraInfo", 50)
    val plate = varchar("plate", 10)
    val powerKwh = integer("powerKwh")
    val autonomyKm = integer("autonomyKm")
    val photoUrl = text("photoUrl")
    val userId = integer("userId") references UserEntity.id
}