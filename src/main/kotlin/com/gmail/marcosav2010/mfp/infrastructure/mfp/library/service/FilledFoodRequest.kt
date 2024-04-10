package com.gmail.marcosav2010.mfp.infrastructure.mfp.library.service

import com.gmail.marcosav2010.myfitnesspal.api.diary.food.DiaryMeal

data class FilledFoodRequest(val result: List<DiaryMeal>, val timestamp: Long)
