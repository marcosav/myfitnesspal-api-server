package com.gmail.marcosav2010.mfp.application.ports

import com.gmail.marcosav2010.mfp.domain.model.Day
import java.util.*

interface DiaryFetcher {

    fun getDayFood(date: Date): Day
}