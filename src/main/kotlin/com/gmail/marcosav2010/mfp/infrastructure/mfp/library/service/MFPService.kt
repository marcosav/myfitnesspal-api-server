package com.gmail.marcosav2010.mfp.infrastructure.mfp.library.service

import com.gmail.marcosav2010.mfp.infrastructure.browsercookies.service.PythonBrowserCookieProvider
import com.gmail.marcosav2010.myfitnesspal.api.IMFPSession
import com.gmail.marcosav2010.myfitnesspal.api.MFPSession
import com.gmail.marcosav2010.myfitnesspal.api.diary.Day
import com.gmail.marcosav2010.myfitnesspal.api.diary.Diary
import org.springframework.stereotype.Component
import java.util.*

@Component
class MFPService(
    private val browserCookieProvider: PythonBrowserCookieProvider
) {

    private var mfpSession: IMFPSession? = null
        get() {
            if (field == null || field!!.shouldReLog())
                field = createSession()
            return field
        }

    fun getDayMeals(dates: List<Date>): List<Day> = dates.map { date ->
        mfpSession!!.toDiary().getDay(date, Diary.FOOD)
    }

    val userMeals: MutableList<String> get() = mfpSession!!.toUser().mealNames

    private fun createSession(): IMFPSession = MFPSession.create { _, _, _ ->
        browserCookieProvider.loadCookies().associate { it.name to it.value }
    }
}