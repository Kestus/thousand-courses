package ru.kestus.courses_data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kestus.courses_domain.Repository
import ru.kestus.courses_domain.entities.CourseItem
import ru.kestus.network.ApiService
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: ApiResponseMapper
) : Repository {

    override suspend fun fetchCourses(): List<CourseItem> {
        var result: List<CourseItem>
        withContext(Dispatchers.IO) {
            val response = apiService.getCourses()
            result = mapper.mapCoursesResponseDaoToCourseList(response)
        }
        return result
    }

}