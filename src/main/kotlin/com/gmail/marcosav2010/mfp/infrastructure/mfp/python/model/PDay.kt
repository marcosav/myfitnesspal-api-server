package com.gmail.marcosav2010.mfp.infrastructure.mfp.python.model

import java.util.Date

data class PDay(
    val date: Date,
    val meals: List<PMeal>
)
