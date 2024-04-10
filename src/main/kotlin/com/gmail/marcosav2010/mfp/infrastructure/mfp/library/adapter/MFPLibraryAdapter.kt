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

    override fun getDayFood(date: Date): Day = mapper.toDomain(date, mfp.getDayMeals(date))

    override fun getMeals(): List<Meal> = mapper.toDomain(mfp.userMeals)
}