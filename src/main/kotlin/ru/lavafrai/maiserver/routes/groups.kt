package ru.lavafrai.maiserver.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.groups() {
    route("/groups") {
        get {
            call.respond("{\"groups\": null}")
        }
    }
}
