package com.jccsisc.irepcp.ui.features.login.domain

import com.jccsisc.irepcp.ui.features.login.data.LoginRepository
import com.jccsisc.irepcp.ui.features.login.data.remote.model.request.LoginRequest
import com.jccsisc.irepcp.ui.features.login.data.remote.model.response.LoginResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.login.domain
 * Created by Julio Cesar Camacho Silva on 15/11/22
 */
class LoginUseCase @Inject constructor(private val repository: LoginRepository) {
    suspend operator fun invoke(request: LoginRequest): Response<LoginResponse> = repository.doLogin(request)
}