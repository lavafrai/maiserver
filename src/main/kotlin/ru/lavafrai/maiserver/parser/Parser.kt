package ru.lavafrai.maiserver.parser

import ru.lavafrai.mai.api.Api
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.Schedule

class Parser {
    fun parseGroupsListOrException(): List<Group> {
        return Api.getGroups()
    }

    fun parseScheduleOrException(group: Group): Schedule? {
        return Api.getSchedule(group)
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
