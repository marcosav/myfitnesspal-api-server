package com.gmail.marcosav2010.mfp.infrastructure.mfp.library.service

import com.gmail.marcosav2010.myfitnesspal.api.IMFPSession
import com.gmail.marcosav2010.myfitnesspal.api.MFPSession
import com.gmail.marcosav2010.myfitnesspal.api.diary.Diary
import com.gmail.marcosav2010.myfitnesspal.api.diary.food.DiaryMeal
import org.springframework.beans.factory.annotation.Value
import java.util.*

class MFPService {

    @Value("\${mfp.library.username}")
    private lateinit var username: String

    @Value("\${mfp.library.password}")
    private lateinit var password: String

    @Value("\${mfp.library.cache-lifespan}")
    private val cacheLifespanSeconds: Long = 7200

    private val dayFoodCache = hashMapOf<Int, FilledFoodRequest>()

    private var mfpSession: IMFPSession? = null
        get() {
            if (field == null || field!!.shouldReLog())
                field = createSession()
            return field
        }

    private fun getDayMeals0(date: Date): List<DiaryMeal> = mfpSession!!.toDiary().getDay(date, Diary.FOOD).meals

    fun getDayMeals(date: Date): List<DiaryMeal> = getRequestKey(date).let { k ->
        val cached = dayFoodCache[k]
        if (cached != null && cached.timestamp + cacheLifespanSeconds * 1000L >= System.currentTimeMillis())
            cached.result
        else
            getDayMeals0(date).also { dayFoodCache[k] = FilledFoodRequest(it, System.currentTimeMillis()) }
    }

    val userMeals: MutableList<String> get() = mfpSession!!.toUser().mealNames

    private fun getRequestKey(date: Date): Int = "${date.year % 100}${date.month}${date.day}".toInt()

    private fun createSession(): IMFPSession =
        MFPSession.create(username, password, null)
}