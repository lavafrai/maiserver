package ru.lavafrai.maiserver.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


class Cacheable<T> (
    val value: T,
    val expired: LocalDateTime
)
