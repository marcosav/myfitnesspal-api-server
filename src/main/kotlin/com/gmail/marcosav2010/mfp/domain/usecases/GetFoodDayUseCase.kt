package com.gmail.marcosav2010.mfp.domain.usecases

import com.gmail.marcosav2010.mfp.domain.model.Day
import java.util.*

fun interface GetFoodDayUseCase {

    operator fun invoke(from: Date, end: Date?): List<Day>
}