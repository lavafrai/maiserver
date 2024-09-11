package ru.lavafrai.maiserver.metrics

import java.util.*

class Metrics {
    private val metrics: MutableMap<MetricName, Int> = EnumMap(ru.lavafrai.maiserver.metrics.MetricName::class.java)

    fun incrementMetric(metric: MetricName) {
        synchronized(this) {
            metrics[metric] = metrics.getOrDefault(metric, 0) + 1
        }
    }

    override fun toString(): String {
        synchronized(this) {
            return metrics.toString()
        }
    }

    companion object {
        private var instance: Metrics? = null

        fun getInstance(): Metrics {
            if (instance == null) {
                instance = Metrics()
            }

            return instance!!
        }
    }
}
