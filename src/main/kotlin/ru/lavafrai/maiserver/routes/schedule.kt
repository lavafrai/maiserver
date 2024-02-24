package ru.lavafrai.maiserver.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.lavafrai.maiapp.data.models.schedule.Schedule
import ru.lavafrai.maiserver.cache.Cache
import ru.lavafrai.maiserver.cache.CacheKeys
import ru.lavafrai.maiserver.models.Group
import ru.lavafrai.maiserver.parser.Parser

fun Route.schedule() {
    route("/schedule/{group}") {
        val cache = Cache.getInstance()
        val parser = Parser.getInstance()

        get {
            val groupName = call.parameters["group"]!!
            val schedule =  cache.getExpirableOrNull<Schedule>(CacheKeys.SCHEDULE_PREFIX + "." + groupName) ?:
                            cache.storeExpirableAndReturn(CacheKeys.SCHEDULE_PREFIX + "." + groupName, parser.parseScheduleOrException(Group(groupName)))

            call.respondText(Json.encodeToString(schedule))
        }
    }
}
