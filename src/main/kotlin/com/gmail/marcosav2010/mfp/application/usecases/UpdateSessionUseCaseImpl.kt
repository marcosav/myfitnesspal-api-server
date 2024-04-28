package com.gmail.marcosav2010.mfp.application.usecases

import com.gmail.marcosav2010.mfp.application.ports.SessionUpdater
import com.gmail.marcosav2010.mfp.domain.usecases.UpdateSessionUseCase
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

@Component
class UpdateSessionUseCaseImpl(
    private val sessionUpdater: SessionUpdater
) : UpdateSessionUseCase {

    override fun invoke(resource: Resource?) = sessionUpdater.update(resource)
}