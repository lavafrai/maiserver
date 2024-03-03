package ru.lavafrai.maiserver

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.lavafrai.maiserver.plugins.configureRouting
import ru.lavafrai.maiserver.plugins.configureSerialization
import ru.lavafrai.maiserver.plugins.configureSockets


fun main() {
/*
    System.setProperty("socksProxyHost", "185.200.188.112");
    System.setProperty("socksProxyPort", "62708");
    System.setProperty("java.net.socks.username", "1bRYipniXe");
    System.setProperty("java.net.socks.password", "W8nfocnSyy");
    Authenticator.setDefault(ProxyAuth("1bRYipniXe", "W8nfocnSyy"))
*/

    val manager = ScheduleManager.getInstance()
    // val groupsCount = manager.getGroupsCount()
    /*
    val pb = ProgressBar("Loading groups...", groupsCount)
        manager.downloadAndCacheAllSchedules { downloaded, _ ->
        pb.extraMessage = "Downloading..."
        pb.stepTo(downloaded)
    }*/

    //val t = manager.downloadAndCacheSchedule(Group("М4О-106Б-23"))
    //println(Json.encodeToString(t.subSchedules.find { it.weekId.number == 4 }))

    embeddedServer(
        Netty,
        port = 80,
        host = "0.0.0.0",
        module = Application::module,
        configure = {
            callGroupSize = 12
            connectionGroupSize = 12
            workerGroupSize = 12
        }
    ).start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureSockets()
    configureRouting()
}
