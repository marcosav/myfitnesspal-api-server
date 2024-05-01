package com.gmail.marcosav2010.mfp.infrastructure.mfp.library.adapter

import com.gmail.marcosav2010.mfp.application.ports.DiaryFetcher
import com.gmail.marcosav2010.mfp.application.ports.SettingsFetcher
import com.gmail.marcosav2010.mfp.domain.model.Day
import com.gmail.marcosav2010.mfp.domain.model.Meal
import com.gmail.marcosav2010.mfp.infrastructure.mfp.library.mapper.LegacyMapper
import com.gmail.marcosav2010.mfp.infrastructure.mfp.library.mapper.toDomain
import com.gmail.marcosav2010.mfp.infrastructure.mfp.library.service.MFPService
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import java.util.*

@Component
@ConditionalOnProperty(value = ["mfp.implementation"], havingValue = "library")
class MFPLibraryAdapter(
    private val mapper: LegacyMapper,
    private val mfp: MFPService
) : DiaryFetcher, SettingsFetcher {

    override fun getDayFood(dates: List<Date>): List<Day> = mapper.toDomain(mfp.getDayMeals(dates))

    override fun getMeals(): List<Meal> = mapper.toDomain(mfp.userMeals)
}