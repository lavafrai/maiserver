package ru.lavafrai.maiserver

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.runBlocking
import ru.lavafrai.mai.applicantsparser.ApplicantParser
import ru.lavafrai.maiserver.cache.Cache
import ru.lavafrai.maiserver.cache.CacheKeys
import ru.lavafrai.maiserver.plugins.*
import java.time.LocalDateTime


fun main() {
    val manager = ScheduleManager.getInstance()

    runBlocking {
        val cache = Cache.getInstance()
        cache.storeExpirableAndReturn(CacheKeys.APPLICANTS, ApplicantParser.parse(), LocalDateTime.now().plusHours(2))
        cache.storeExpirableAndReturn(CacheKeys.APPLICATIONS, ApplicantParser)
    }

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
    // configureCache()
}
