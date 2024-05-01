package ru.lavafrai.maiserver.routes

import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.index() {
    get ("/") {
        call.respond(FreeMarkerContent("index.ftl", mapOf<String, String>()))
    }
}
