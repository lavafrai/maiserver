package ru.lavafrai.maiserver

import ru.lavafrai.mai.api.MaiApi
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.Schedule
import ru.lavafrai.mai.api.models.schedule.TeacherId
import ru.lavafrai.maiserver.cache.Cache
import ru.lavafrai.maiserver.cache.CacheKeys
import ru.lavafrai.maiserver.parser.Parser
import java.time.LocalDateTime

class ScheduleManager private constructor() {
    fun getGroupsCount(): Long {
        val cache = Cache.getInstance()
        val parser = Parser.getInstance()
        val groups = cache.getExpirableOrNull<List<Group>>(CacheKeys.GROUPS_LIST) ?: cache.storeExpirableAndReturn(
            CacheKeys.GROUPS_LIST,
            parser.parseGroupsListOrException(),
            expired = LocalDateTime.now().plusDays(14)
        )

        return groups.size.toLong()
    }


    suspend fun downloadAndCacheSchedule(group: Group): Schedule? {
        val cache = Cache.getInstance()
        val parser = Parser.getInstance()

        return cache.getOrExecuteAndCache(CacheKeys.SCHEDULE_PREFIX + "." + group.name.toString()) {
            MaiApi.getSchedule(group)
        }
    }


    suspend fun downloadAndCacheTeacherSchedule(teacherId: TeacherId): Schedule? {
        val cache = Cache.getInstance()
        val parser = Parser.getInstance()

        return cache.getOrExecuteAndCache(CacheKeys.SCHEDULE_PREFIX + "." + teacherId.uid) {
            MaiApi.getTeachersSchedule(teacherId)
        }
    }

    fun getKnownTeachers(): List<TeacherId> {
        return MaiApi.getTeachersList()
    }


    companion object {
        private var instance: ScheduleManager? = null

        fun getInstance(): ScheduleManager {
            if (instance == null) instance = ScheduleManager()
            return instance!!
        }
    }
}