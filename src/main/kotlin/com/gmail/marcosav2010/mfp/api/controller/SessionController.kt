package com.gmail.marcosav2010.mfp.api.controller

import com.gmail.marcosav2010.mfp.domain.usecases.UpdateSessionUseCase
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class SessionController(
    private val updateSessionUseCase: UpdateSessionUseCase
) : SessionCookiesApi {

    override fun updateSessionCookies(body: Resource?): ResponseEntity<Unit> {
        updateSessionUseCase(body)
        return ResponseEntity.ok().build()
    }
}