package ru.kestus.thousand_courses

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

//    init {
//        // TODO: remove logout
//        val scope = CoroutineScope(Dispatchers.IO)
//        scope.launch {
//            sessionStorage.removeSession()
//        }
//    }

    suspend fun loadSession() {
        var result: String?
        withContext(Dispatchers.IO) {
            result = sessionStorage.getSession()
        }
        _session.value = result
    }
}