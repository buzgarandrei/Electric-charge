package com.diver6ty.chargetapbackend.dao

import org.jetbrains.exposed.sql.Table

object UserEntity: Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val name = varchar("name", 50)
    val email = varchar("email", 50)
    val password = text("password")
    val profilePictureUrl = text("profilePictureUrl")
}