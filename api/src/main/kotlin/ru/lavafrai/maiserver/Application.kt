package ru.lavafrai.maiserver

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.lavafrai.maiserver.plugins.configureRouting
import ru.lavafrai.maiserver.plugins.configureSerialization


fun main() {
    val manager = ScheduleManager.getInstance()

    // val t = manager.downloadAndCacheSchedule(Group("М4О-106Б-23"))

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
    configureSerialization()
    configureRouting()
}
