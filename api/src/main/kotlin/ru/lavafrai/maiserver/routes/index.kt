package ru.lavafrai.maiserver.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.index() {
    get ("/") {
        call.respond("this is maiserver api")
    }
}
