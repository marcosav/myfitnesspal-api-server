package com.gmail.marcosav2010.mfp.infrastructure.mfp.python.model

import com.fasterxml.jackson.annotation.JsonProperty

data class DiaryPreferences(
    @JsonProperty("meal_names")
    val mealNames: List<String>?
)
