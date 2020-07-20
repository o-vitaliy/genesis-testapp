package com.genesis.testapp.domain

object LinkHelper {
    fun getParam(link: String?, param: String): String? {
        return link?.split("?")
            ?.get(1)
            ?.split("&")
            ?.firstOrNull { it.startsWith("$param=") }
            ?.split("=")
            ?.get(1)
    }
}
