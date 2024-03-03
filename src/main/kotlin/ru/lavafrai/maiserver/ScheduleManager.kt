package ru.lavafrai.maiserver

import ru.lavafrai.maiapp.data.models.schedule.Schedule
import ru.lavafrai.maiserver.cache.Cache
import ru.lavafrai.maiserver.cache.CacheKeys
import ru.lavafrai.maiserver.models.Group
import ru.lavafrai.maiserver.parser.Parser
import ru.lavafrai.maiserver.utils.mapThreaded
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicLong
import kotlin.system.exitProcess

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

    fun downloadAndCacheAllSchedules(callback: (Long, Long) -> Unit): List<Schedule> {
        val downloaded = AtomicLong(0)
        val total = AtomicLong(0)
        val cache = Cache.getInstance()
        val parser = Parser.getInstance()
        val lock = "A"

        val groups = cache.getExpirableOrNull<List<Group>>(CacheKeys.GROUPS_LIST) ?: cache.storeExpirableAndReturn(
            CacheKeys.GROUPS_LIST,
            parser.parseGroupsListOrException(),
            expired = LocalDateTime.now().plusDays(14)
        )
        total.set(groups.size.toLong())

        val schedules = groups.mapThreaded(numThreads = 24) {
            try {
                val schedule = cache.getExpirableOrNull<Schedule>(CacheKeys.SCHEDULE_PREFIX + "." + it.toString())
                    ?: cache.storeExpirableAndReturn(
                        CacheKeys.SCHEDULE_PREFIX + "." + it.toString(),
                        parser.parseScheduleOrException(it)
                    )
                downloaded.incrementAndGet()
                callback(downloaded.get(), total.get())
                schedule
            } catch (e: Exception) {
                exitProcess(1)
            }

        }

        return schedules
    }


    fun downloadAndCacheSchedule(group: Group, callback: (Long, Long) -> Unit = { _, _ -> }): Schedule {
        val cache = Cache.getInstance()
        val parser = Parser.getInstance()

        val schedule = cache.getExpirableOrNull<Schedule>(CacheKeys.SCHEDULE_PREFIX + "." + group.name.toString())
            ?: cache.storeExpirableAndReturn(
                CacheKeys.SCHEDULE_PREFIX + "." + group.name.toString(),
                parser.parseScheduleOrException(group)
            )

        return schedule
    }


    companion object {
        private var instance: ScheduleManager? = null

        fun getInstance(): ScheduleManager {
            if (instance == null) instance = ScheduleManager()
            return instance!!
        }
    }
}