package com.gmail.marcosav2010.mfp.infrastructure.mfp.python.adapter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.gmail.marcosav2010.mfp.application.ports.DiaryFetcher
import com.gmail.marcosav2010.mfp.application.ports.SettingsFetcher
import com.gmail.marcosav2010.mfp.domain.model.Day
import com.gmail.marcosav2010.mfp.domain.model.Meal
import com.gmail.marcosav2010.mfp.infrastructure.mfp.python.bridge.PythonBridge
import com.gmail.marcosav2010.mfp.infrastructure.mfp.python.mapper.PythonModelMapper
import com.gmail.marcosav2010.mfp.infrastructure.mfp.python.mapper.toDomain
import com.gmail.marcosav2010.mfp.infrastructure.mfp.python.model.PDay
import com.gmail.marcosav2010.mfp.infrastructure.mfp.python.model.UserMetadata
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class MFPPythonAdapter(
    private val bridge: PythonBridge,
    private val objectMapper: ObjectMapper,
    private val mapper: PythonModelMapper
) : DiaryFetcher, SettingsFetcher {

    override fun getDayFood(dates: List<Date>): List<Day> {
        val parsedDates = dates.map { dateFormatter.format(it) }

        val result = bridge.execute("day_meals", *parsedDates.toTypedArray())

        val meals: List<PDay>
        if (result.code == 0)
            meals = objectMapper.readValue(result.output)
        else
            throw RuntimeException(result.output)

        return mapper.toDomain(meals)
    }

    override fun getMeals(): List<Meal> {
        val result = bridge.execute("metadata")

        val metadata: UserMetadata
        if (result.code == 0)
            metadata = objectMapper.readValue(result.output)
        else
            throw RuntimeException(result.output)

        return metadata.diaryPreferences?.mealNames?.let { mapper.toDomain(it) }
            ?: throw RuntimeException("meals not received")
    }

    companion object {
        private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
    }
}