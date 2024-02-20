package ru.lavafrai.maiserver.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Serializable
class Cacheable (
    val value: SerializableModel,
    val expired: LocalDate
)
