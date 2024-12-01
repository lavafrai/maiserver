val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.0.20"
    id("io.ktor.plugin") version "2.3.8"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20"
}

group = "ru.lavafrai"
version = "v1.1.5"

application {
    mainClass.set("ru.lavafrai.maiserver.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")

}

repositories {
    mavenCentral()
    maven( url = "https://jitpack.io")
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-host-common-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")
    implementation("io.ktor:ktor-server-html-builder:$ktor_version")
    implementation("io.ktor:ktor-server-metrics-micrometer:$ktor_version")
    implementation("io.ktor:ktor-server-swagger:$ktor_version")

    implementation("com.github.lavaFrai:maiapi:34bd976a48")
    implementation("com.github.lavaFrai:exler-maiapi:d9fe7edd9f")
}
