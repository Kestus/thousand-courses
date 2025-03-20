package ru.kestus.presentation.viewModel

import android.app.Application
import android.text.Editable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kestus.core.domain.preferences.SessionStorage
import ru.kestus.presentation.R
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val application: Application,
    private val sessionStorage: SessionStorage
): AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val emailRE = Regex("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,6}$")

    private val _finished = MutableLiveData(false)
    val finished : LiveData<Boolean> get() = _finished

    private val _inputEmail = MutableLiveData<String>()
    private val _inputPassword = MutableLiveData<String>()

    private val _errorEmail = MediatorLiveData<String?>().apply {
        addSource(_inputEmail) {
            this.value = null
            it?.let {
                val isValid = validateEmail(it)
                if (!isValid) {
                    this.value = application.getString(R.string.input_email_error)
                }
            }
        }
    }
    val errorEmail: LiveData<String?> get() = _errorEmail

    private val _errorPassword = MediatorLiveData<String?>().apply {
        addSource(_inputPassword) {
            this.value = null
            it?.let {
                val isValid = validatePassword(it)
                if (!isValid) {
                    this.value = application.getString(R.string.input_password_error)
                }
            }
        }
    }
    val errorPassword: LiveData<String?> get() = _errorPassword

    private val _submitButtonActive = MediatorLiveData(false).apply {
        addSource(_inputEmail) { checkButtonActive() }
        addSource(_inputPassword) { checkButtonActive() }
    }
    val submitButtonEnabled: LiveData<Boolean> get() = _submitButtonActive

    fun setInputEmail(input: Editable) {
        _inputEmail.value = input.toString()
    }

    fun setInputPassword(input: Editable) {
        _inputPassword.value = input.toString()
    }

    private fun validateEmail(input: String): Boolean {
        return emailRE.matches(input)
    }

    private fun validatePassword(input: String): Boolean {
        return input.isNotEmpty()
    }

    private fun checkButtonActive() {
        val noErrors = _errorEmail.value == null && errorPassword.value == null
        val inputsNotEmpty = !_inputEmail.value.isNullOrEmpty() && !_inputPassword.value.isNullOrEmpty()
        _submitButtonActive.value = noErrors && inputsNotEmpty
    }

    fun login() = ioScope.launch {
        val currentEmailInput = _inputEmail.value ?: return@launch
        val currentPasswordInput = _inputPassword.value ?: return@launch
        sessionStorage.setSession(currentEmailInput)
        _finished.postValue(true)
    }

}