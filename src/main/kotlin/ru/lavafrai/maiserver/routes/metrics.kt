package ru.lavafrai.maiserver.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.lavafrai.maiserver.metrics.Metrics

fun Route.metrics() {
    get("/metrics") {
        call.respond(Metrics.getInstance().toString())
    }
}