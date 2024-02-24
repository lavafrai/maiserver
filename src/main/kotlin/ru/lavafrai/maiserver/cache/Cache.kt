package ru.lavafrai.maiserver.cache

import ru.lavafrai.maiserver.DEFAULT_CACHE_EXPIRE_HOURS
import ru.lavafrai.maiserver.models.Cacheable
import java.time.LocalDateTime


class Cache {
    private val cache: MutableMap<String, Cacheable<*>> = HashMap()

    fun <T> storeExpirable(key: String, data: T, expired: LocalDateTime = LocalDateTime.now().plusHours(DEFAULT_CACHE_EXPIRE_HOURS)) {
        synchronized(this) {
            val cacheable = Cacheable(data, expired)
            cache.put(key, cacheable)
        }
    }

    fun <T> storeExpirableAndReturn(key: String, data: T, expired: LocalDateTime = LocalDateTime.now().plusHours(DEFAULT_CACHE_EXPIRE_HOURS)): T {
        this.storeExpirable(key, data, expired)
        return data
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getExpirableOrNull(key: String): T? {
        synchronized(this) {
            val now = LocalDateTime.now()
            val data = cache[key] ?: return null

            if (data.expired < now) { return null }
            return data.value as T
        }
    }

    companion object {
        private var instance: Cache? = null

        fun getInstance(): Cache {
            if (instance == null) {
                instance = Cache()
            }

            return instance!!
        }
    }
}