package com.gmail.marcosav2010.mfp.api.controller

import com.gmail.marcosav2010.mfp.api.mapper.FoodMapper
import com.gmail.marcosav2010.mfp.domain.usecases.GetFoodDayUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class FoodController(
    private val mapper: FoodMapper,
    private val useCase: GetFoodDayUseCase
) : FoodApi {

    override fun getFoodDiary(date: Date, toDate: Date?) =
        ResponseEntity.ok(mapper.toResponse(useCase(date, toDate)))
}