package com.gmail.marcosav2010.mfp.api.mapper

import com.gmail.marcosav2010.mfp.api.dto.AvailableMealsResponse
import com.gmail.marcosav2010.mfp.api.dto.MealInfoDTO
import com.gmail.marcosav2010.mfp.domain.model.Meal
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface MealsMapper {

    fun toDto(source: List<Meal>): List<MealInfoDTO>
}

fun MealsMapper.toResponse(source: List<Meal>) = AvailableMealsResponse(toDto(source))