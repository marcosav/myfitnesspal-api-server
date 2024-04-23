package com.gmail.marcosav2010.mfp.infrastructure.mfp.library.adapter

import com.gmail.marcosav2010.mfp.application.ports.DiaryFetcher
import com.gmail.marcosav2010.mfp.application.ports.SettingsFetcher
import com.gmail.marcosav2010.mfp.domain.model.Day
import com.gmail.marcosav2010.mfp.domain.model.Meal
import com.gmail.marcosav2010.mfp.infrastructure.mfp.library.mapper.LegacyMapper
import com.gmail.marcosav2010.mfp.infrastructure.mfp.library.mapper.toDomain
import com.gmail.marcosav2010.mfp.infrastructure.mfp.library.service.MFPService
import java.util.*

class MFPLibraryAdapter(private val mapper: LegacyMapper, private val mfp: MFPService) : DiaryFetcher, SettingsFetcher {

    override fun getDayFood(dates: List<Date>): List<Day> =
        listOf(mapper.toDomain(dates.first(), mfp.getDayMeals(dates.first())))

    override fun getMeals(): List<Meal> = mapper.toDomain(mfp.userMeals)
}