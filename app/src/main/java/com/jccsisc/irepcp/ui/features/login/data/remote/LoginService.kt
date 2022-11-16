package com.jccsisc.irepcp.ui.features.login.data.remote

import com.jccsisc.irepcp.ui.features.login.data.remote.model.request.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.login.data.remote
 * Created by Julio Cesar Camacho Silva on 15/11/22
 */
class LoginService @Inject constructor(private val loginClient: LoginClient) {
    suspend fun doLogin(request: LoginRequest) = withContext(Dispatchers.IO) {
        val response = loginClient.doLogin()
        response
    }
}