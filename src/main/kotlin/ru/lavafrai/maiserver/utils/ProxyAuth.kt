package ru.lavafrai.maiserver.utils

import java.net.Authenticator
import java.net.PasswordAuthentication

class ProxyAuth(user: String?, password: String?) : Authenticator() {
    private val auth: PasswordAuthentication

    init {
        auth = PasswordAuthentication(user, password?.toCharArray() ?: charArrayOf())
    }

    override fun getPasswordAuthentication(): PasswordAuthentication {
        return auth
    }
}