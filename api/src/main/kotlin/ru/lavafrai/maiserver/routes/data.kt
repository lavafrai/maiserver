package ru.lavafrai.maiserver.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.lavafrai.maiserver.metrics.MetricName
import ru.lavafrai.maiserver.metrics.Metrics
import java.io.File

fun Route.data() {
    route("/data/{name}") {

        get {
            Metrics.getInstance().incrementMetric(MetricName.DATA_GET)

            call.respondFile(File("libs/maidata/${call.parameters["name"]}.lm"))
        }
    }
}
