package com.gmail.marcosav2010.mfp.application.usecases

import com.gmail.marcosav2010.mfp.application.ports.DiaryFetcher
import com.gmail.marcosav2010.mfp.domain.model.Day
import com.gmail.marcosav2010.mfp.domain.usecases.GetFoodDayUseCase
import org.springframework.stereotype.Component
import java.util.*

@Component
class GetFoodDayUseCaseImpl(private val diaryFetcher: DiaryFetcher) : GetFoodDayUseCase {

    override fun invoke(from: Date, end: Date?): Day {
        return diaryFetcher.getDayFood(from);
    }
}