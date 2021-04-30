package br.com.ocaminhodaspedras.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.ocaminhodaspedras.domain.LoginResult
import br.com.ocaminhodaspedras.interactor.LoginInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    app: Application,
    private val interactor: LoginInteractor
) : AndroidViewModel(app) {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordCheck = MutableLiveData<String>()
    val result = MutableLiveData<LoginResult<Nothing>>()

    fun login() {
        viewModelScope.launch {
            result.value = interactor.login(email.value, password.value)
        }
    }

    fun signUp() {
        viewModelScope.launch {
            result.value = interactor.signUp(email.value, password.value, passwordCheck.value)
        }
    }

    fun forgotPassword() {
        viewModelScope.launch {
            result.value = interactor.forgotPassword(email.value)
        }
    }

}