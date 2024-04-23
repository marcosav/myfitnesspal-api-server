package com.gmail.marcosav2010.mfp.api.mapper

import com.gmail.marcosav2010.mfp.api.dto.DayMealDTO
import com.gmail.marcosav2010.mfp.api.dto.FoodDayDTO
import com.gmail.marcosav2010.mfp.api.dto.FoodDaysResponse
import com.gmail.marcosav2010.mfp.domain.model.Day
import com.gmail.marcosav2010.mfp.domain.model.MealFood
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface FoodMapper {

    @Mapping(target = "name", source = "meal.name")
    fun toDto(source: MealFood): DayMealDTO

    fun toDto(source: Day): FoodDayDTO
}

fun FoodMapper.toResponse(source: List<Day>) = FoodDaysResponse(source.map { toDto(it) })