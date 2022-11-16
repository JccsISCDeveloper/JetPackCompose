package com.jccsisc.irepcp.ui.features.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jccsisc.irepcp.core.MyResult
import com.jccsisc.irepcp.ui.features.login.data.remote.model.request.LoginRequest
import com.jccsisc.irepcp.ui.features.login.data.remote.model.response.LoginResponse
import com.jccsisc.irepcp.ui.features.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.login.ui
 * Created by Julio Cesar Camacho Silva on 14/11/22
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase): ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isButtonEnabled = MutableLiveData<Boolean>()
    val isButtonEmablled: LiveData<Boolean> = _isButtonEnabled

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _resultLogin = MutableLiveData<MyResult<LoginResponse>>()
    val resultLogin: LiveData<MyResult<LoginResponse>> = _resultLogin

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isButtonEnabled.value = enabledLogin(email, password)
    }

    private fun enabledLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

    fun doLogin(request: LoginRequest) {
        viewModelScope.launch {
//            _isLoading.value = true
            _resultLogin.postValue(MyResult.loading())
            val result = loginUseCase(request)
            Log.i("data", "data: $result")
            if (result.isSuccessful) {
                _resultLogin.postValue(MyResult.success(result.body()))
            } else {
                _resultLogin.postValue(MyResult.error(result.body(), result.message()))
            }
//            _isLoading.value = false
        }
    }
}