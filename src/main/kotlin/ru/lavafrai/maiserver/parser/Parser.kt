package ru.lavafrai.maiserver.parser

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import ru.lavafrai.maiapp.data.parser.parseGroupsList
import ru.lavafrai.maiserver.COOKIES_CONSTANT
import ru.lavafrai.maiserver.models.Group
import java.io.IOException

class Parser {
    fun parseGroupsListOrException(): List<Group> {
        return parseGroupsList(this)
    }


    fun getPage(url: String, args: Map<String, String> = mapOf(), attemptsLeft: Int = 50): Document {
        var builtUrl = url
        if (!builtUrl.endsWith("?")) builtUrl += "?"
        args.forEach {
            builtUrl += "&${it.key}=${it.value}"
        }
        if (attemptsLeft < 0) throw IOException()

        return try {
            Jsoup
                .connect(builtUrl)
                .header("Cookie", COOKIES_CONSTANT)
                .get()
        } catch (e: Exception) {
            e.printStackTrace()
            Thread.sleep(100)
            getPage(url, args, attemptsLeft - 1)
        }
    }


    companion object {
        private var instance: Parser? = null

        fun getInstance(): Parser {
            if (instance == null) {
                instance = Parser()
            }

            return instance!!
        }
    }
}
