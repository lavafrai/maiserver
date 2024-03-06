package ru.lavafrai.maiserver.parser

import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.Schedule
import ru.lavafrai.mai.api.parser.parseGroupsList
import ru.lavafrai.mai.api.parser.parseSchedule

class Parser {
    fun parseGroupsListOrException(): List<Group> {
        return parseGroupsList()
    }

    fun parseScheduleOrException(group: Group): Schedule {
        return parseSchedule(group)
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
