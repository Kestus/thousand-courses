package ru.kestus.thousand_courses.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kestus.core.domain.preferences.SessionStorage
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sessionStorage: SessionStorage
) : ViewModel() {

    private val _session = MutableLiveData<String>()
    val session: LiveData<String> get() = _session

    // TODO: remove
//    init {
//        val ioScope = CoroutineScope(Dispatchers.IO)
//        ioScope.launch {
//            sessionStorage.removeSession()
//        }
//    }

    suspend fun loadSession() {
        withContext(Dispatchers.IO) {
            _session.postValue(sessionStorage.getSession())
        }
    }

    suspend fun removeSession() {
        withContext(Dispatchers.IO) {
            sessionStorage.removeSession()
        }
    }
}