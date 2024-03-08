package ru.lavafrai.maiserver.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.lavafrai.mai.api.Api
import ru.lavafrai.maiserver.metrics.MetricName
import ru.lavafrai.maiserver.metrics.Metrics

fun Route.teacher() {
    route("/teachers") {
        get {
            Metrics.getInstance().incrementMetric(MetricName.TEACHER_SCHEDULE_GET)

            val teachers = Api.getInstance().getTeachersList()

            call.respondText(Json.encodeToString(teachers))
        }
    }
}