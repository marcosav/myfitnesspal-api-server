package com.gmail.marcosav2010.mfp.application.usecases

import com.gmail.marcosav2010.mfp.application.ports.DiaryFetcher
import com.gmail.marcosav2010.mfp.domain.model.Day
import com.gmail.marcosav2010.mfp.domain.usecases.GetFoodDayUseCase
import org.springframework.stereotype.Component
import java.util.*

@Component
class GetFoodDayUseCaseImpl(private val diaryFetcher: DiaryFetcher) : GetFoodDayUseCase {

    override fun invoke(from: Date, end: Date?): List<Day> {
        return diaryFetcher.getDayFood(from..end);
    }

    operator fun Date.rangeTo(toDate: Date?): List<Date> {
        if (toDate == null) return listOf(this)

        val datesInRange = hashSetOf<Date>()

        val calendar = Calendar.getInstance()
        calendar.setTime(this)
        calendar[Calendar.HOUR_OF_DAY] = 0

        val endCalendar = Calendar.getInstance()
        endCalendar.setTime(toDate)
        endCalendar[Calendar.HOUR_OF_DAY] = 23

        while (calendar.before(endCalendar)) {
            val result = calendar.time
            datesInRange.add(result)
            calendar.add(Calendar.DATE, 1)
        }

        return datesInRange.sorted()
    }
}