package com.gmail.marcosav2010.mfp.application.ports

import org.springframework.core.io.Resource

interface SessionUpdater {

    fun update(cookieFile: Resource?)
}