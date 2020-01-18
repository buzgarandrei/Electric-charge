package com.diver6ty.chargetapbackend

import com.diver6ty.chargetapbackend.dao.ApplicationDaoImpl
import com.diver6ty.chargetapbackend.model.repository.Repository
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.features.*
import org.slf4j.event.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.gson.*
import org.jetbrains.exposed.sql.Database

private val dao = ApplicationDaoImpl(Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver"))

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    dao.init()
    Repository.mockStations.forEach { dao.addStation(it) }
    Repository.mockPowerUnits.forEach { dao.addPowerUnit(it) }
    Repository.mockUsers.forEach { dao.addUser(it) }
    Repository.mockCars.forEach { dao.addCar(it) }

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

    routing {
        get("/stations") {
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

        get("/powerUnits") {
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

        get("/cars") {
            call.respond(mapOf("success" to true, "data" to dao.getAllCars()))
        }

        get("/cars/{userId}") {
            val userId = call.parameters["userId"]?.toIntOrNull()
            if (userId == null) {
                call.respond(mapOf("success" to false, "error" to "Invalid User ID"))
            } else {
                call.respond(mapOf("success" to true, "data" to dao.getCarsOfUser(userId)))
            }
        }

        get("/users") {
            call.respond(mapOf("success" to true, "data" to dao.getAllUsers()))
        }
    }
}

