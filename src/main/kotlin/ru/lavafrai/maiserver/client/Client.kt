package ru.lavafrai.maiserver.client

import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.network.TolerantJson
import ru.lavafrai.mai.api.network.getPage

object Client {
    private var APIRootUrl = "https://mai3.lavafrai.ru/"

    fun setRootUrl(root: String) {
        APIRootUrl = root
    }

    fun downloadEndpointText(endpoint: String, args: Map<String, Any> = mapOf()): String? {
        return  try {
            getPage(APIRootUrl + endpoint, args.map { it.key to it.value.toString() }.toMap())
        } catch (e: Exception) {
            RuntimeException("Error while getting $endpoint, args: $args", e).printStackTrace()
            null
        }
    }

    inline fun <reified T> downloadEndpoint(endpoint: String, args: Map<String, Any> = mapOf()): T? {
        val text = downloadEndpointText(endpoint, args)
        return if (text != null) return TolerantJson.decodeFromString(text) else null
    }

    fun downloadGroupsList(): List<Group>? {
        return downloadEndpoint("/groups")
    }
}