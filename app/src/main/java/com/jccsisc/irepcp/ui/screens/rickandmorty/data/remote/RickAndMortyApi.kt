package com.jccsisc.irepcp.ui.screens.rickandmorty.data.remote

import com.jccsisc.irepcp.core.constants.ConstantsApi.END_POINT_ID
import com.jccsisc.irepcp.core.constants.ConstantsApi.END_POINT_PAGE
import com.jccsisc.irepcp.core.constants.ConstantsApi.PATH_CHARACTER
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

    @GET(PATH_CHARACTER)
    suspend fun getPersonajes(@Query(END_POINT_PAGE) page: Int): PersonajesDto

    @GET("$PATH_CHARACTER{$END_POINT_ID}")
    suspend fun getPersonaje(@Path(END_POINT_ID) id: Int): PersonajeDto
}