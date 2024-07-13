package ru.lavafrai.maiserver

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.lavafrai.mai.applicantsparser.ApplicantParser
import ru.lavafrai.maiserver.plugins.configureFreeMarker
import ru.lavafrai.maiserver.plugins.configureRouting
import ru.lavafrai.maiserver.plugins.configureSerialization
import ru.lavafrai.maiserver.plugins.configureSockets
import ru.lavafrai.maiserver.routes.applicants
import ru.lavafrai.maiserver.routes.applications
import ru.lavafrai.maiserver.routes.directions
import java.time.ZoneId
import java.time.ZonedDateTime


fun main() {
    val manager = ScheduleManager.getInstance()

    runBlocking {
        applicants = ZonedDateTime.now(ZoneId.of("GMT")) to ApplicantParser.getApplicants()
        applications = ZonedDateTime.now(ZoneId.of("GMT")) to ApplicantParser.getApplications()
        directions = ZonedDateTime.now(ZoneId.of("GMT")) to ApplicantParser.getDirections()
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
    ).start(wait = false)

    runBlocking {
        launch {
            while (true) {
                delay(1000*60*30)
                applicants = ZonedDateTime.now(ZoneId.of("GMT")) to ApplicantParser.getApplicants()
                applications = ZonedDateTime.now(ZoneId.of("GMT")) to ApplicantParser.getApplications()
                directions = ZonedDateTime.now(ZoneId.of("GMT")) to ApplicantParser.getDirections()
            }
        }
    }
}

fun Application.module() {
    configureSerialization()
    configureSockets()
    configureRouting()
    configureFreeMarker()
    // configureCache()
}
