package com.gmail.marcosav2010.mfp.infrastructure.mfp.python.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Entry(
    val name: String,
    val brand: String?,
    val amount: Double,
    val unit: String?,
    @JsonProperty("nutrition_information")
    val nutritionInformation: NutritionInformation
)
