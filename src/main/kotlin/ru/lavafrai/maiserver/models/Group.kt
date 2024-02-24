package ru.lavafrai.maiserver.models

import kotlinx.serialization.Serializable


@Serializable
class Group(
    val name: String
): SerializableModel() {

}