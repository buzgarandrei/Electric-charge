package com.diver6ty.chargetapbackend.model

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val profilePictureUrl: String
)