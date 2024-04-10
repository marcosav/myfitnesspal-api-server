package com.gmail.marcosav2010.mfp.domain.usecases

import com.gmail.marcosav2010.mfp.domain.model.Day
import java.util.*

typealias GetFoodDayUseCase = (from: Date, end: Date?) -> Day