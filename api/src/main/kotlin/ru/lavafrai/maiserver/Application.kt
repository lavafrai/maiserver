package ru.lavafrai.maiserver

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.maiserver.plugins.configureFreeMarker
import ru.lavafrai.maiserver.plugins.configureRouting
import ru.lavafrai.maiserver.plugins.configureSerialization
import ru.lavafrai.maiserver.plugins.configureSockets


fun main() {
    val manager = ScheduleManager.getInstance()

    val t = manager.downloadAndCacheSchedule(Group("М4О-106Б-23"))

    embeddedServer(
        Netty,
        port = 80,
        host = "0.0.0.0",
        module = Application::module,
        configure = {
            callGroupSize = 12
            connectionGroupSize = 12
            workerGroupSize = 12
        }
    ).start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureSockets()
    configureRouting()
    configureFreeMarker()
}
