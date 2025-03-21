package ru.kestus.network.model

import com.google.gson.annotations.SerializedName

data class CoursesResponseDao (
    @SerializedName("courses") val courses: List<CourseDao>
)