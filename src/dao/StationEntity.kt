package com.diver6ty.chargetapbackend.dao

import org.jetbrains.exposed.sql.Table

object StationEntity: Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val name = varchar("name", 50)
    val address = text("address")
    val photoUrl = text("photoUrl")
    val latitude = double("latitude")
    val longitude = double("longitude")
    val rating = integer("rating")
    val nrReviews = integer("nrReviews")
}