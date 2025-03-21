package ru.kestus.courses_presentation.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.kestus.courses_domain.entities.CourseItem
import javax.inject.Inject

class AdapterDelegate @Inject constructor(
    private val coursesDelegate: CoursesDelegate
) : AsyncListDifferDelegationAdapter<RecyclerItem>(RecyclerItemDiffCallback) {

    fun setOnCourseFavButtonClickListener(block: (CourseItem) -> Unit) {
        coursesDelegate.onFavouriteClickListener = block
    }

    fun setOnCourseMoreButtonClickListener(block: (CourseItem) -> Unit) {
        coursesDelegate.onMoreButtonClickListener = block
    }

    init {
        delegatesManager.addDelegate(coursesDelegate)
    }

}