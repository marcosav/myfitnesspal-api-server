package com.gmail.marcosav2010.mfp.application.usecases

import com.gmail.marcosav2010.mfp.application.ports.SettingsFetcher
import com.gmail.marcosav2010.mfp.domain.model.Meal
import com.gmail.marcosav2010.mfp.domain.usecases.GetMealsUseCase
import org.springframework.stereotype.Component

@Component
class GetMealsUseCaseImpl(private val settingsFetcher: SettingsFetcher) : GetMealsUseCase {

    override fun invoke(): List<Meal> {
        return settingsFetcher.getMeals();
    }
}