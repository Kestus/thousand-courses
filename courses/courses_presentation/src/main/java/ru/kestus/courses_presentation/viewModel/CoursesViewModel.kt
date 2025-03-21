package ru.kestus.courses_presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import ru.kestus.courses_domain.entities.CourseItem
import ru.kestus.courses_domain.useCase.FetchCoursesUseCase
import ru.kestus.courses_presentation.CoursesFragmentState
import ru.kestus.courses_presentation.adapter.RecyclerItem
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val fetchCoursesUseCase: FetchCoursesUseCase
) : ViewModel() {


    val stateFlow = flow {

        emit(CoursesFragmentState.Loading)
        val result = fetchCoursesUseCase()
        val recyclerItems = result.map {
            RecyclerItem.Course(it)
        }
        emit(CoursesFragmentState.Content(recyclerItems))


    }.stateIn(
        viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = CoursesFragmentState.Initial
    )

    fun toggleFavoriteStatus(item: CourseItem) {

    }

}