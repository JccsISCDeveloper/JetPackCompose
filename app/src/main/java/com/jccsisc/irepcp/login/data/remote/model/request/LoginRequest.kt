package com.jccsisc.irepcp.login.data.remote.model.request

import com.google.gson.annotations.SerializedName

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.login.data.remote.model
 * Created by Julio Cesar Camacho Silva on 15/11/22
 */
data class LoginRequest(
    @SerializedName("email") var correo: String = "",
    @SerializedName("password") var contrasenia: String = ""
)
