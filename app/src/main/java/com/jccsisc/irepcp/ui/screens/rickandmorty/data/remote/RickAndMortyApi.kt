package com.jccsisc.irepcp.ui.screens.rickandmorty.data.remote

import com.jccsisc.irepcp.ui.screens.rickandmorty.data.remote.dto.personaje.PersonajeDto
import com.jccsisc.irepcp.ui.screens.rickandmorty.data.remote.dto.personajes.PersonajesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty.data.remote
 * Created by Julio Cesar Camacho Silva on 06/12/22
 */
interface RickAndMortyApi {

    @GET("character/")
    suspend fun getPersonajes(@Query("page") page: Int): PersonajesDto

    @GET("character/{id}")
    suspend fun getPersonaje(@Path("id") id: Int): PersonajeDto
}