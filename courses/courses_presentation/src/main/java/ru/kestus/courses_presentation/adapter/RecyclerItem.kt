package ru.kestus.courses_presentation.adapter

import ru.kestus.courses_domain.entities.CourseItem

sealed class RecyclerItem(
    val id: Long,
) {

    data class Course(val value: CourseItem): RecyclerItem(value.id)

}