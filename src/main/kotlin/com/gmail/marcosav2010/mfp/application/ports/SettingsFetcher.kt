package com.gmail.marcosav2010.mfp.application.ports

import com.gmail.marcosav2010.mfp.domain.model.Meal

interface SettingsFetcher {

    fun getMeals(): List<Meal>
}