package ru.lavafrai.maiserver.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.lavafrai.maiserver.routes.groups
import ru.lavafrai.maiserver.routes.schedule

fun Application.configureRouting() {
    routing {
        groups()
        schedule()
    }
}
