package ru.kestus.courses_domain.entities

data class CourseItem(
    val id: Long,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
) {

    fun copyToggleLike(): CourseItem = this.copy(hasLike = !hasLike)

}



