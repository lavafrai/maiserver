package ru.lavafrai.maiserver.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.br
import ru.lavafrai.maiserver.cache.Cache
import ru.lavafrai.maiserver.metrics.Metrics

fun Route.index() {
    get ("/") {
        call.respondHtml(HttpStatusCode.OK) {
            body {
                +"All working now"
                br()
                +"MaiServer by lava_frai"
                br()
                a (href = "/docs") {
                    +"Open documentation"
                }

                br()
                br()
                +"Cache: "
                +Cache.getInstance().cache.keys.toString()
                br()
                +"Metrics: "
                +Metrics.getInstance().toString()
            }
        }
    }
}