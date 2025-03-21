package ru.kestus.courses_domain.useCase

import ru.kestus.courses_domain.Repository
import ru.kestus.courses_domain.entities.CourseItem
import javax.inject.Inject

class FetchCoursesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<CourseItem> {
        return repository.fetchCourses()
    }
}