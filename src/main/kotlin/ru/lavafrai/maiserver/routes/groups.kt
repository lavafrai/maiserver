package ru.lavafrai.maiserver.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.lavafrai.maiserver.cache.Cache
import ru.lavafrai.maiserver.cache.CacheKeys
import ru.lavafrai.maiserver.models.Group
import ru.lavafrai.maiserver.parser.Parser
import java.time.LocalDateTime

fun Route.groups() {
    route("/groups") {
        val cache = Cache.getInstance()
        val parser = Parser.getInstance()

        get {
            run {
                call.respond(
                    Json.encodeToString(
                        cache.getExpirableOrNull<List<Group>>(CacheKeys.GROUPS_LIST) ?: cache.storeExpirableAndReturn(
                            CacheKeys.GROUPS_LIST,
                            parser.parseGroupsListOrException(),
                            expired = LocalDateTime.now().plusDays(14)
                        )
                    )
                )
            }
        }
    }
}
