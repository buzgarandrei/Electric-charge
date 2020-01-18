package com.diver6ty.chargetapbackend.model.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

open class SimpleJWT(val secret: String) {
    private val algorithm = Algorithm.HMAC256(secret)
    val verifier = JWT.require(algorithm).build()
    fun sign(email: String): String = JWT.create().withClaim("email", email).sign(algorithm)
}