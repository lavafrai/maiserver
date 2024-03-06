val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.3.8"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
}

group = "ru.lavafrai"
version = "0.0.1"

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
    implementation("io.ktor:ktor-server-websockets-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")
    implementation("org.jsoup:jsoup:1.10.2")
    implementation("io.ktor:ktor-server-html-builder:$ktor_version")
    implementation("io.ktor:ktor-server-metrics-micrometer:$ktor_version")

    implementation("me.tongfei:progressbar:0.10.0")
    implementation("com.github.lavaFrai:maiapi:v1.0.1")
    implementation("com.github.lavaFrai:exler-maiapi:v1.0.0")

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}
