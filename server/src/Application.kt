package com.example.server

import com.example.common.CreateCityDto
import com.example.server.db.DatabaseFactory
import com.example.server.repository.CityRepository
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*
import java.net.URI

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            serializeNulls()
        }
    }

    val dbUri = URI(environment.config.property("db.jdbcUrl").getString())

    val username: String = dbUri.userInfo.split(":")[0]
    val password: String = dbUri.userInfo.split(":")[1]
    val dbUrl = ("jdbc:postgresql://${dbUri.host}:${dbUri.port}${dbUri.path}")

    DatabaseFactory(
        dbUrl = dbUrl,
        dbPassword = password,
        dbUser = username
    ).apply {
        init()
    }

    val repository = CityRepository()

    routing {
        route("/weather/prediction") {
            get {
                val start = call.request.queryParameters["start"]?.toLong()
                val size = call.request.queryParameters["size"]?.toInt()
                if (start == null || size == null) {
                    call.respond(repository.getAll())
                } else {
                    call.respond(repository.getPage(start, size))
                }
            }
            post {
                repository.add(call.receive())
                call.respond(HttpStatusCode.OK)
            }
            delete {
                val id = call.request.queryParameters["id"]?.toLong()
                if (id == null) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    repository.delete(id)
                    call.respond(HttpStatusCode.OK)
                }
            }
            put {
                repository.update(call.receive())
                call.respond(HttpStatusCode.OK)
            }
        }

        route("/sample") {
            get {
                call.respond("Sample text!")
            }
        }
    }
}

