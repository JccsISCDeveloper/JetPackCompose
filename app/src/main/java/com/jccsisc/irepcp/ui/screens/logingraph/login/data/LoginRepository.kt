package com.jccsisc.irepcp.ui.screens.logingraph.login.data

import com.jccsisc.irepcp.ui.screens.logingraph.login.data.remote.LoginService
import com.jccsisc.irepcp.ui.screens.logingraph.login.data.remote.model.request.LoginRequest
import javax.inject.Inject

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.login.data
 * Created by Julio Cesar Camacho Silva on 15/11/22
 */
class LoginRepository @Inject constructor(private val api: LoginService) {
    suspend fun doLogin(request: LoginRequest) = api.doLogin(request)
}