package com.gmail.marcosav2010.mfp.domain.usecases

import org.springframework.core.io.Resource

fun interface UpdateSessionUseCase {

    operator fun invoke(resource: Resource?)
}