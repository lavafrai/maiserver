package ru.lavafrai.maiserver.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.TeacherId
import ru.lavafrai.maiserver.ScheduleManager
import ru.lavafrai.maiserver.cache.Cache
import ru.lavafrai.maiserver.metrics.MetricName
import ru.lavafrai.maiserver.metrics.Metrics
import ru.lavafrai.maiserver.parser.Parser

fun Route.schedule() {
    route("/schedule/{group}") {
        val cache = Cache.getInstance()
        val parser = Parser.getInstance()

        get {
            Metrics.getInstance().incrementMetric(MetricName.SCHEDULE_GET)

            val groupName = call.parameters["group"]!!
            var schedule = ScheduleManager.getInstance().downloadAndCacheSchedule(Group(groupName))

            if (schedule != null) {
                call.respondText(Json.encodeToString(schedule)); return@get
            }

            schedule = ScheduleManager.getInstance().downloadAndCacheTeacherSchedule(TeacherId("", call.parameters["group"]!!))
            if (schedule != null) {
                call.respondText(Json.encodeToString(schedule)); return@get
            }

            call.respondText("{}", status = HttpStatusCode.NotFound)
        }
    }
}
