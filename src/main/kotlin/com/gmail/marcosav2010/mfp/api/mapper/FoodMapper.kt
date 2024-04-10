package com.gmail.marcosav2010.mfp.api.mapper

import com.gmail.marcosav2010.mfp.api.dto.DayMealDTO
import com.gmail.marcosav2010.mfp.api.dto.FoodDayResponse
import com.gmail.marcosav2010.mfp.domain.model.Day
import com.gmail.marcosav2010.mfp.domain.model.MealFood
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface FoodMapper {

    @Mapping(target = "name", source = "meal.name")
    fun toDto(source: MealFood): DayMealDTO

    fun toResponse(source: Day): FoodDayResponse
}