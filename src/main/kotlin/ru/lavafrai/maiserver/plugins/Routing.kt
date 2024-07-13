package ru.lavafrai.maiserver.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*
import ru.lavafrai.maiserver.routes.*

fun Application.configureRouting() {
    routing {
        data()
        applicants()
        groups()
        schedule()
        teacherSchedule()
        index()
        metrics()
        teacher()
        swaggerUI(path = "docs", swaggerFile = "openapi/documentation.json")

        staticResources("/static", "static")
    }
}
