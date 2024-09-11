package ru.lavafrai.maiserver.landing

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.ZoneId
import java.time.ZonedDateTime
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import freemarker.cache.ClassTemplateLoader
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import io.ktor.server.response.*


fun main() {
    embeddedServer(
        Netty,
        port = 80,
        host = "0.0.0.0",
        module = Application::module,
        configure = {
            callGroupSize = 128
            connectionGroupSize = 128
            workerGroupSize = 128
        }
    ).start(wait = true)
}

fun Application.module() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    routing {
        staticResources("/static", "static")
        get ("/") {
            call.respond(FreeMarkerContent("index.ftl", mapOf<String, String>()))
        }
    }
}
