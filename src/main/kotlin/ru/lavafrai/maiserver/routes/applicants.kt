package ru.lavafrai.maiserver.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import ru.lavafrai.mai.applicantsparser.ApplicantParser
import ru.lavafrai.maiserver.cache.Cache
import ru.lavafrai.maiserver.cache.CacheKeys
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.hours

fun Route.applicants() {
    route("/applicants") {
        val cache = Cache.getInstance()

        get {
            val applicants = cache.getOrExecuteAndCache(CacheKeys.APPLICANTS, LocalDateTime.now().plusHours(1)) {
                ApplicantParser.parse()
            }

            call.respond(applicants)
        }
    }
}