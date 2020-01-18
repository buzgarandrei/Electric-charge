package com.diver6ty.chargetapbackend.model.requests

data class LoginRequest(
    val email: String,
    val password: String
)