package ru.kestus.courses_presentation

import ru.kestus.courses_presentation.adapter.RecyclerItem

sealed class CoursesFragmentState {

    data object Initial : CoursesFragmentState()

    data object Loading: CoursesFragmentState()

    class Content(val value: List<RecyclerItem>): CoursesFragmentState()

}