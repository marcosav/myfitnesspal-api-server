package com.gmail.marcosav2010.mfp.domain.usecases

import com.gmail.marcosav2010.mfp.domain.model.Meal

fun interface GetMealsUseCase {

    operator fun invoke(): List<Meal>
}