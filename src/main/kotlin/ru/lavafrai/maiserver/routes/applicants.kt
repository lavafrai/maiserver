package ru.lavafrai.maiserver.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.lavafrai.mai.applicantsparser.makePredictions
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME


lateinit var applications: Pair<ZonedDateTime, List<ru.lavafrai.mai.applicantsparser.Application>>
lateinit var applicants: Pair<ZonedDateTime, List<ru.lavafrai.mai.applicantsparser.Applicant>>
lateinit var directions: Pair<ZonedDateTime, List<ru.lavafrai.mai.applicantsparser.Direction>>

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

    route("/applicant/{id}") {
        get {
            val id = call.parameters["id"]!!

            val applicant = applicants.second.find { it.id == id }
            if (applicant == null) {
                call.respondText("Applicant not found", status = io.ktor.http.HttpStatusCode.NotFound)
                return@get
            }
            call.respondText(
                Json.encodeToString(applicant.makePredictions(applications.second, directions.second)),
                contentType = io.ktor.http.ContentType.Application.Json
            )
        }
    }
}