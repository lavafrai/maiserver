package ru.lavafrai.maiserver.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.lavafrai.exler.mai.Exler
import ru.lavafrai.exler.mai.types.teacherNameHash
import ru.lavafrai.maiserver.metrics.MetricName
import ru.lavafrai.maiserver.metrics.Metrics

fun Route.teacher() {
    route("/teachers") {
        get {
            Metrics.getInstance().incrementMetric(MetricName.TEACHER_LIST_GET)
            val teachers = Exler.parseTeachers()// Api.getInstance().getTeachersList()

            call.respondText(Json.encodeToString(teachers))
        }
    }

    route("/teacher/{name}") {
        get {
            Metrics.getInstance().incrementMetric(MetricName.TEACHER_INFO_GET)
            val teachers = Exler.parseTeachers()
            val teacherName = call.parameters["name"]!!
            if (!teachers.any { teacherNameHash(it.name) == teacherNameHash(teacherName) }) { call.respond(HttpStatusCode.NotFound) ; return@get }

            val teacher = Exler.findTeacher(teacherName)
            if (teacher == null) { call.respond(HttpStatusCode.NotFound) ; return@get }

            call.respondText(Json.encodeToString(Exler.parseTeacherInfo(teacher)))
        }
    }
}