package com.gmail.marcosav2010.mfp.api.controller

import com.gmail.marcosav2010.mfp.api.dto.AvailableMealsResponse
import com.gmail.marcosav2010.mfp.api.mapper.MealsMapper
import com.gmail.marcosav2010.mfp.api.mapper.toResponse
import com.gmail.marcosav2010.mfp.domain.usecases.GetMealsUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class MealsController(
    private val mapper: MealsMapper,
    private val useCase: GetMealsUseCase
) : MealsApi {

    override fun getMeals(): ResponseEntity<AvailableMealsResponse> = ResponseEntity.ok(mapper.toResponse(useCase()))
}