package ru.lavafrai.maiserver.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME


lateinit var applications: Pair<ZonedDateTime, List<ru.lavafrai.mai.applicantsparser.Application>>
lateinit var applicants: Pair<ZonedDateTime, List<ru.lavafrai.mai.applicantsparser.Applicant>>

fun Route.applicants() {
    route("/applicants") {
        get {
            call.response.header(
                "Last-Modified",
                RFC_1123_DATE_TIME.format(applicants.first)
            )
            call.respondText(
                Json.encodeToString(applicants.second),
                contentType = io.ktor.http.ContentType.Application.Json
            )
        }
    }

    route("/applications") {
        get {
            call.response.header(
                "Last-Modified",
                RFC_1123_DATE_TIME.format(applications.first)
            )
            call.respondText(
                Json.encodeToString(applications.second),
                contentType = io.ktor.http.ContentType.Application.Json
            )
        }
    }
}