package ru.lavafrai.maiserver.parser

import ru.lavafrai.maiserver.GROUPS_PAGE_URL
import ru.lavafrai.maiserver.models.Group

fun parseGroupsList(parser: Parser): List<Group> {
    val page = parser.getPage(GROUPS_PAGE_URL)

    val faculties: List<String> = page
        .select("#department")
        .first()!!
        .children().toList()
        .filter { true }
        .map { it.text() }

    val courses = (1..6).toList().map { it.toString() }

    val facultyCoursePairs: MutableList<Pair<String, String>> = ArrayList()
    faculties.forEach { faculty ->
        courses.forEach { course ->
            facultyCoursePairs.add(Pair(faculty, course))
        }
    }

    val groups: MutableList<Group> = ArrayList()
    facultyCoursePairs.map { it ->
        val subPage = parser.getPage(GROUPS_PAGE_URL, mapOf("department" to it.first, "course" to it.second))
        groups.addAll(
            subPage.select(".tab-content").select(".btn-group").map { group ->
                Group(group.text())
            } ?: listOf()
        )
    }

    return groups
}