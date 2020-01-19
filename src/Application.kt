package com.diver6ty.chargetapbackend

import com.diver6ty.chargetapbackend.dao.*
import com.diver6ty.chargetapbackend.exceptions.*
import com.diver6ty.chargetapbackend.model.*
import com.diver6ty.chargetapbackend.model.jwt.SimpleJWT
import com.diver6ty.chargetapbackend.model.repository.Repository
import com.diver6ty.chargetapbackend.model.requests.FinishAppointmentRequest
import com.diver6ty.chargetapbackend.model.requests.LoginRequest
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.authenticate
import io.ktor.auth.jwt.jwt
import io.ktor.auth.principal
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.request.path
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.event.Level

// local: "mysql://root:toor@localhost:3306/chargetap-local-db"
// remote: "mysql://f62hpl3n4csbnrgj:y2fu6ma9xvvhfls3@a5s42n4idx9husyc.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/zuwzl5lzvl83usz0"

val url = "jdbc:${System.getenv("JAWSDB_URL")}"
private val dao = ApplicationDaoImpl(Database.connect(url, driver = "com.mysql.cj.jdbc.Driver"))

private fun resetDatabase() {
    transaction {
        SchemaUtils.drop(AppointmentEntity, CarEntity, PowerUnitEntity, StationEntity, UserEntity)
        dao.init()
    }
    Repository.mockStations.forEach { dao.addStation(it) }
    Repository.mockPowerUnits.forEach { dao.addPowerUnit(it) }
    Repository.mockUsers.forEach { dao.addUser(it) }
    Repository.mockCars.forEach { dao.addCar(it) }
    Repository.mockAppointments.forEach { dao.addAppointment(it) }
}

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    dao.init()

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        allowCredentials = true
        anyHost()
    }

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    val simpleJwt = SimpleJWT("diver6ty-chargetap-jwt-secret")
    install(Authentication) {
        jwt {
            verifier(simpleJwt.verifier)
            validate {
                UserIdPrincipal(it.payload.getClaim("email").asString())
            }
        }
    }

    routing {
        authenticate {
            route("/appointments") {
                get("/all") {
                    call.respond(mapOf("success" to true, "data" to dao.getAllAppointments()))
                }

                get("/me") {
                    try {
                        val principal = call.principal<UserIdPrincipal>() ?: error("Invalid Session")
                        call.respond(mapOf("success" to true, "data" to dao.getAppointmentsByEmail(principal.name)))
                    } catch (e: InvalidUserException) {
                        call.respond(mapOf("success" to false, "error" to e.message))
                    } catch (e: IllegalStateException) {
                        call.respond(mapOf("success" to false, "error" to e.message))
                    }
                }

                get("/id") {
                    val appointmentId = call.request.queryParameters["appointmentId"]?.toIntOrNull()

                    if (appointmentId == null) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Appointment ID"))
                    } else {
                        call.respond(mapOf("success" to true, "data" to dao.getAppointmentById(appointmentId)))
                    }
                }

                get("/email") {
                    val email = call.request.queryParameters["email"]
                    if (email.isNullOrBlank()) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Parameter Name"))
                    } else {
                        try {
                            call.respond(mapOf("success" to true, "data" to dao.getAppointmentsByEmail(email)))
                        } catch (e: InvalidUserException) {
                            call.respond(mapOf("success" to true, "data" to emptyList<Appointment>()))
                        }
                    }
                }

                get("/powerUnit") {
                    val powerUnitId = call.request.queryParameters["powerUnitId"]?.toIntOrNull()
                    if (powerUnitId == null) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Power Unit ID"))
                    } else {
                        call.respond(mapOf("success" to true, "data" to dao.getAppointmentsOfPowerUnit(powerUnitId)))
                    }
                }

                put {
                    try {
                        val appointment = call.receive<Appointment>()
                        dao.updateAppointment(appointment)
                        call.respond(mapOf("success" to true))
                    } catch (e: Exception) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Appointment: ${e.message}"))
                    }
                }

                put("/finish") {
                    try {
                        val finishAppointmentRequest = call.receive<FinishAppointmentRequest>()
                        val finishAppointmentResponse = dao.finishAppointment(finishAppointmentRequest)
                        call.respond(mapOf("success" to true, "data" to finishAppointmentResponse))
                    } catch(e: EndTimeInvalidException) {
                        call.respond(mapOf("success" to false, "error" to e.message))
                    } catch (e: AppointmentNotFoundException) {
                        call.respond(mapOf("success" to false, "error" to e.message))
                    } catch (e: InvalidPowerUnitIDException) {
                        call.respond(mapOf("success" to false, "error" to e.message))
                    } catch(e: PowerUnitEmptyException) {
                        call.respond(mapOf("success" to false, "error" to e.message))
                    } catch (e: Exception) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Finish Appointment Request: ${e.message}"))
                    }
                }

                post {
                    try {
                        val appointment = call.receive<Appointment>()
                        dao.addAppointment(appointment)
                        call.respond(mapOf("success" to true))
                    } catch (e: InvalidPowerUnitIDException) {
                        call.respond(mapOf("success" to false, "error" to e.message))
                    } catch (e: PowerUnitFullException) {
                        call.respond(mapOf("success" to false, "error" to e.message))
                    } catch (e: Exception) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Appointment: ${e.message}"))
                    }
                }

                delete("/{appointmentId}") {
                    val appointmentId = call.parameters["appointmentId"]?.toIntOrNull()
                    if (appointmentId == null) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Appointment ID"))
                    } else {
                        try {
                            dao.deleteAppointmentById(appointmentId)
                            call.respond(mapOf("success" to true))
                        } catch (e: Exception) {
                            call.respond(mapOf("success" to false, "error" to "Invalid Appointment: ${e.message}"))
                        }
                    }
                }
            }

            route("/stations") {
                get("/all") {
                    call.respond(mapOf("success" to true, "data" to dao.getAllStations()))
                }

                get {
                    val keyword = call.request.queryParameters["keyword"]
                    if (keyword.isNullOrBlank()) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Search Keyword"))
                    } else {
                        call.respond(mapOf("success" to true, "data" to dao.getStationsByKeyword(keyword)))
                    }
                }

                post {
                    try {
                        val station = call.receive<Station>()
                        dao.addStation(station)
                        call.respond(mapOf("success" to true))
                    } catch(e: Exception) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Station: ${e.message}"))
                    }
                }

                put {
                    try {
                        val station = call.receive<Station>()
                        dao.updateStation(station)
                        call.respond(mapOf("success" to true))
                    } catch(e: Exception) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Station: ${e.message}"))
                    }
                }

                delete("/{stationId}") {
                    val stationId = call.parameters["stationId"]?.toIntOrNull()
                    if (stationId == null) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Station ID"))
                    } else {
                        try {
                            dao.deleteStationById(stationId)
                            call.respond(mapOf("success" to true))
                        } catch (e: Exception) {
                            call.respond(mapOf("success" to false, "error" to "Invalid Station: ${e.message}"))
                        }
                    }
                }
            }

            route("/powerUnits") {
                get("/all") {
                    call.respond(mapOf("success" to true, "data" to dao.getAllPowerUnits()))
                }

                get("/id") {
                    val powerUnitId = call.request.queryParameters["powerUnitId"]?.toIntOrNull()
                    if (powerUnitId == null) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Power Unit ID"))
                    } else {
                        call.respond(mapOf("success" to true, "data" to dao.getPowerUnitById(powerUnitId)))
                    }
                }

                get("/station") {
                    val stationId = call.request.queryParameters["stationId"]?.toIntOrNull()
                    if (stationId == null) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Station ID"))
                    } else {
                        call.respond(mapOf("success" to true, "data" to dao.getPowerUnitsOfStation(stationId)))
                    }
                }

                post {
                    try {
                        val powerUnit = call.receive<PowerUnit>()
                        dao.addPowerUnit(powerUnit)
                        call.respond(mapOf("success" to true))
                    } catch(e: Exception) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Power Unit: ${e.message}"))
                    }
                }

                put {
                    try {
                        val powerUnit = call.receive<PowerUnit>()
                        dao.updatePowerUnit(powerUnit)
                        call.respond(mapOf("success" to true))
                    } catch(e: Exception) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Power Unit: ${e.message}"))
                    }
                }

                delete("/{powerUnitId}") {
                    val powerUnitId = call.parameters["powerUnitId"]?.toIntOrNull()
                    if (powerUnitId == null) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Power Unit ID"))
                    } else {
                        try {
                            dao.deletePowerUnitById(powerUnitId)
                            call.respond(mapOf("success" to true))
                        } catch (e: Exception) {
                            call.respond(mapOf("success" to false, "error" to "Invalid Power Unit: ${e.message}"))
                        }
                    }
                }
            }

            route("/cars") {
                get("/all") {
                    call.respond(mapOf("success" to true, "data" to dao.getAllCars()))
                }

                get("/me") {
                    try {
                        val principal = call.principal<UserIdPrincipal>() ?: error("Invalid Session")
                        call.respond(mapOf("success" to true, "data" to dao.getCarsByEmail(principal.name)))
                    } catch (e: IllegalStateException) {
                        call.respond(mapOf("success" to false, "error" to e.message))
                    }
                }

                get {
                    val email = call.request.queryParameters["email"]
                    if (email.isNullOrBlank()) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Email"))
                    } else {
                        call.respond(mapOf("success" to true, "data" to dao.getCarsByEmail(email)))
                    }
                }

                post {
                    try {
                        val car = call.receive<Car>()
                        dao.addCar(car)
                        call.respond(mapOf("success" to true))
                    } catch(e: Exception) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Car: ${e.message}"))
                    }
                }

                put {
                    try {
                        val car = call.receive<Car>()
                        dao.updateCar(car)
                        call.respond(mapOf("success" to true))
                    } catch(e: Exception) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Car: ${e.message}"))
                    }
                }

                delete("/{carId}") {
                    val carId = call.parameters["carId"]?.toIntOrNull()
                    if (carId == null) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Car ID"))
                    } else {
                        try {
                            dao.deleteCarById(carId)
                            call.respond(mapOf("success" to true))
                        } catch (e: Exception) {
                            call.respond(mapOf("success" to false, "error" to "Invalid Car: ${e.message}"))
                        }
                    }
                }
            }

            route("/users") {
                get("/all") {
                    call.respond(mapOf("success" to true, "data" to dao.getAllUsers()))
                }

                get("/me") {
                    try {
                        val principal = call.principal<UserIdPrincipal>() ?: error("Invalid Session")
                        call.respond(mapOf("success" to true, "data" to dao.getUserByEmail(principal.name)))
                    } catch (e: IllegalStateException) {
                        call.respond(mapOf("success" to false, "error" to e.message))
                    }
                }

                get {
                    val email = call.request.queryParameters["email"]
                    if (email.isNullOrBlank()) {
                        call.respond(mapOf("success" to false, "error" to "Invalid Email"))
                    } else {
                        call.respond(mapOf("success" to true, "data" to dao.getUserByEmail(email)))
                    }
                }

                post {
                    try {
                        val user = call.receive<User>()
                        dao.addUser(user)
                        call.respond(mapOf("success" to true))
                    } catch (e: Exception) {
                        call.respond(mapOf("success" to false, "error" to "Invalid User: ${e.message}"))
                    }
                }

                put {
                    try {
                        val user = call.receive<User>()
                        dao.updateUser(user)
                        call.respond(mapOf("success" to true))
                    } catch(e: Exception) {
                        call.respond(mapOf("success" to false, "error" to "Invalid User: ${e.message}"))
                    }
                }

                delete("/{userId}") {
                    val userId = call.parameters["userId"]?.toIntOrNull()
                    if (userId == null) {
                        call.respond(mapOf("success" to false, "error" to "Invalid User ID"))
                    } else {
                        try {
                            dao.deleteUserById(userId)
                            call.respond(mapOf("success" to true))
                        } catch (e: Exception) {
                            call.respond(mapOf("success" to false, "error" to "Invalid User: ${e.message}"))
                        }
                    }
                }
            }
        }

        post("/login") {
            try {
                val loginRequest = call.receive<LoginRequest>()
                val user = dao.getUserByEmail(loginRequest.email)
                if (user != null) {
                    if (loginRequest.password == user.password) {
                        call.respond(mapOf("success" to true, "data" to simpleJwt.sign(user.email)))
                    } else {
                        call.respond(mapOf("success" to false, "error" to "Email or Password Incorrect"))
                    }
                } else {
                    call.respond(mapOf("success" to false, "error" to "Email or Password Incorrect"))
                }
            } catch (e: Exception) {
                call.respond(mapOf("success" to false, "error" to "Invalid Login Request: ${e.message}"))
            }
        }

        post("/register") {
            try {
                val user = call.receive<User>()
                dao.addUser(user)
                call.respond(mapOf("success" to true, "data" to simpleJwt.sign(user.email)))
            } catch (e: UserWithEmailAlreadyExistsException) {
                call.respond(mapOf("success" to false, "error" to e.message))
            } catch (e: Exception) {
                call.respond(mapOf("success" to false, "error" to "Invalid Register Request: ${e.message}"))
            }
        }

        post("/reset") {
            try {
                resetDatabase()
                call.respond(mapOf("success" to true))
            } catch (e: Exception) {
                call.respond(mapOf("success" to false, "error" to "Database reset Failed: ${e.message}"))
            }
        }
    }
}

