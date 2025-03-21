package ru.kestus.courses_domain

import ru.kestus.courses_domain.entities.CourseItem

interface Repository {

    suspend fun fetchCourses(): List<CourseItem>

}