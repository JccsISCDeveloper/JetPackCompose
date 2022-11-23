package com.jccsisc.irepcp.ui.features.logingraph.login.data.remote

import com.jccsisc.irepcp.ui.features.logingraph.login.data.remote.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.login.data.remote
 * Created by Julio Cesar Camacho Silva on 15/11/22
 */
interface LoginClient {
    @GET("oauth2/token/")
    suspend fun doLogin(): Response<LoginResponse>
}