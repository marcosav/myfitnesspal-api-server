package com.gmail.marcosav2010.mfp.infrastructure.mfp.python.mapper

import com.gmail.marcosav2010.mfp.domain.model.Day
import com.gmail.marcosav2010.mfp.domain.model.Meal
import com.gmail.marcosav2010.mfp.domain.model.MealFood
import com.gmail.marcosav2010.mfp.infrastructure.mfp.python.model.PMeal
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import java.util.*

@Mapper(componentModel = "spring")
interface PythonModelMapper {

    @Mapping(target = "meal.name", source = "name")
    @Mapping(target = "food", source = "entries")
    fun toDomain(source: PMeal): MealFood

    fun toDomain(source: List<PMeal>): List<MealFood>
}

fun PythonModelMapper.toDomain(source: List<String>) = source.map { Meal(it) }

fun PythonModelMapper.toDomain(date: Date, source: List<PMeal>): Day = Day(date, toDomain(source))