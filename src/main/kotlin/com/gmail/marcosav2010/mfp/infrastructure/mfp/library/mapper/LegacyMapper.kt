package com.gmail.marcosav2010.mfp.infrastructure.mfp.library.mapper

import com.gmail.marcosav2010.mfp.domain.model.Day
import com.gmail.marcosav2010.mfp.domain.model.Meal
import com.gmail.marcosav2010.mfp.domain.model.MealFood
import com.gmail.marcosav2010.myfitnesspal.api.diary.food.DiaryMeal
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface LegacyMapper {

    @Mapping(target = "meal.name", source = "name")
    fun toDomain(source: DiaryMeal): MealFood

    fun toDomain(source: List<com.gmail.marcosav2010.myfitnesspal.api.diary.Day>): List<Day>
}

fun LegacyMapper.toDomain(source: List<String>) = source.map { Meal(it) }