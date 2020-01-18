package com.diver6ty.chargetapbackend

import com.diver6ty.chargetapbackend.dao.*
import com.diver6ty.chargetapbackend.exceptions.InvalidPowerUnitIDException
import com.diver6ty.chargetapbackend.exceptions.InvalidUserException
import com.diver6ty.chargetapbackend.exceptions.PowerUnitFullException
import com.diver6ty.chargetapbackend.exceptions.UserWithEmailAlreadyExistsException
import com.diver6ty.chargetapbackend.model.Appointment
import com.diver6ty.chargetapbackend.model.User
import com.diver6ty.chargetapbackend.model.jwt.SimpleJWT
import com.diver6ty.chargetapbackend.model.repository.Repository
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
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.event.Level

// local: "jdbc:mysql://root:toor@localhost:3306/chargetap-local-db"
// remote: "jdbc:mysql://y5hjwj5t7xnjh80t:zforyzfl8nj2bbc1@bbj31ma8tye2kagi.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/utu0leoqdyt869br"
private val dao = ApplicationDaoImpl(Database.connect(System.getenv("JDBC_DATABASE_URL"), driver = "com.mysql.cj.jdbc.Driver"))

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
            get("/stations/all") {
                call.respond(mapOf("success" to true, "data" to dao.getAllStations()))
            }

            get("/stations/{keyword}") {
                val keyword = call.parameters["keyword"]
                if (keyword.isNullOrBlank()) {
                    call.respond(mapOf("success" to false, "error" to "Invalid Search Keyword"))
                } else {
                    call.respond(mapOf("success" to true, "data" to dao.getStationsByKeyword(keyword)))
                }
                call.respond(mapOf("success" to true, "data" to dao.getAllStations()))
            }

            get("/powerUnits/all") {
                call.respond(mapOf("success" to true, "data" to dao.getAllPowerUnits()))
            }

            get("/powerUnits/{stationId}") {
                val stationId = call.parameters["stationId"]?.toIntOrNull()
                if (stationId == null) {
                    call.respond(mapOf("success" to false, "error" to "Invalid Station ID"))
                } else {
                    call.respond(mapOf("success" to true, "data" to dao.getPowerUnitsOfStation(stationId)))
                }
            }

            get("/cars/all") {
                call.respond(mapOf("success" to true, "data" to dao.getAllCars()))
            }

            get("/cars") {
                try {
                    val principal = call.principal<UserIdPrincipal>() ?: error("Invalid Session")
                    call.respond(mapOf("success" to true, "data" to dao.getCarsOfUser(principal.name)))
                } catch (e: IllegalStateException) {
                    call.respond(mapOf("success" to false, "error" to e.message))
                }
            }

            get("/users/all") {
                call.respond(mapOf("success" to true, "data" to dao.getAllUsers()))
            }

            get("/appointments/all") {
                call.respond(mapOf("success" to true, "data" to dao.getAllAppointments()))
            }

            get("appointments/{powerUnitId}") {
                val powerUnitId = call.parameters["powerUnitId"]?.toIntOrNull()
                if (powerUnitId == null) {
                    call.respond(mapOf("success" to false, "error" to "Invalid Power Unit ID"))
                } else {
                    call.respond(mapOf("success" to true, "data" to dao.getAppointmentsOfPowerUnit(powerUnitId)))
                }
            }

            get("/appointments") {
                try {
                    val principal = call.principal<UserIdPrincipal>() ?: error("Invalid Session")
                    call.respond(mapOf("success" to true, "data" to dao.getAppointmentsOfUser(principal.name)))
                } catch(e: InvalidUserException) {
                    call.respond(mapOf("success" to false, "error" to e.message))
                } catch (e: IllegalStateException) {
                    call.respond(mapOf("success" to false, "error" to e.message))
                }
            }

            post("/appointments") {
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
                transaction {
                    SchemaUtils.drop(AppointmentEntity, CarEntity, PowerUnitEntity, StationEntity, UserEntity)
                    SchemaUtils.create(AppointmentEntity, CarEntity, PowerUnitEntity, StationEntity, UserEntity)
                }
                Repository.mockStations.forEach { dao.addStation(it) }
                Repository.mockPowerUnits.forEach { dao.addPowerUnit(it) }
                Repository.mockUsers.forEach { dao.addUser(it) }
                Repository.mockCars.forEach { dao.addCar(it) }
                Repository.mockAppointments.forEach { dao.addAppointment(it) }
                call.respond(mapOf("success" to true))
            } catch(e: Exception) {
                call.respond(mapOf("success" to false, "error" to "Database Reset Failed: ${e.message}"))
            }
        }
    }
}

