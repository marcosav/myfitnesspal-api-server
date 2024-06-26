package com.gmail.marcosav2010.mfp.infrastructure.browsercookies.adapter

import com.gmail.marcosav2010.mfp.application.ports.SessionUpdater
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import java.io.File

@Component
class CookieUpdaterAdapter : SessionUpdater {

    @Value("\${browsercookies.cookies-db-path}")
    private lateinit var sessionCookiesPath: String

    override fun update(cookieFile: Resource?) {
        cookieFile?.inputStream?.copyTo(
            File(sessionCookiesPath).outputStream()
        )
    }
}