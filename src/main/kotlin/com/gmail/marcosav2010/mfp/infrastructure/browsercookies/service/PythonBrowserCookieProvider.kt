package com.gmail.marcosav2010.mfp.infrastructure.browsercookies.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.gmail.marcosav2010.mfp.infrastructure.browsercookies.model.PCookie
import com.gmail.marcosav2010.mfp.infrastructure.python.bridge.PythonBridge
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class PythonBrowserCookieProvider(
    private val bridge: PythonBridge,
    private val objectMapper: ObjectMapper
) {

    @Value("\${browsercookies.cookie-loader-path}")
    private lateinit var cookiesPyExecutable: String

    fun loadCookies(): List<PCookie> {
        val result = bridge.execute(cookiesPyExecutable)

        val cookies: List<PCookie>
        if (result.code == 0)
            cookies = objectMapper.readValue(result.output)
        else
            throw RuntimeException(result.output)

        return cookies
    }
}