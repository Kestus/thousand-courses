package ru.kestus.courses_data

import ru.kestus.courses_domain.entities.CourseItem
import ru.kestus.network.model.CourseDao
import ru.kestus.network.model.CoursesResponseDao

object ApiResponseMapper {

    fun mapCoursesResponseDaoToCourseList(dao: CoursesResponseDao): List<CourseItem> =
        dao.courses.map {
            mapCourseDaoToCourseItem(it)
        }

    fun mapCourseDaoToCourseItem(dao: CourseDao): CourseItem = CourseItem(
        id = dao.id.toLong(),
        title = dao.title,
        text = dao.text,
        price = dao.price,
        rate = dao.rate,
        startDate = dao.startDate,
        hasLike = dao.hasLike,
        publishDate = dao.publishDate
    )

}