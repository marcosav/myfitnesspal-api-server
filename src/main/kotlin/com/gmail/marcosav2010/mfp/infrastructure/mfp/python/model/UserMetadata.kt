package com.gmail.marcosav2010.mfp.infrastructure.mfp.python.model

import com.fasterxml.jackson.annotation.JsonProperty

data class UserMetadata(
    @JsonProperty("diary_preferences")
    val diaryPreferences: DiaryPreferences?
)
