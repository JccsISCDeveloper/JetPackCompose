package com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.data.remote

import com.jccsisc.irepcp.core.MyResultS
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.data.remote.dto.personaje.toPersonaje
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.data.remote.dto.personajes.toListPersonajes
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.domain.model.personaje.Personaje
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.domain.model.personajes.Personajes
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.domain.repositories.PersonajeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty.data.remote
 * Created by Julio Cesar Camacho Silva on 06/12/22
 */
class PersonajeRepositoryImpl @Inject constructor(private val api: RickAndMortyApi) :
    PersonajeRepository {
    override fun getPersonajes(page: Int): Flow<MyResultS<List<Personajes>>> = flow {
        emit(MyResultS.Loading())
        try {
            val response = api.getPersonajes(page).toListPersonajes()
            emit(MyResultS.Success(response))
        } catch (e: HttpException) {
            emit(MyResultS.Failure(msg = "Oops, ocurrió un error", data = null))
        } catch (e: IOException) {
            emit(MyResultS.Failure(msg = "Error con el servidor", data = null))
        }
    }

    override suspend fun getPersonaje(id: Int): MyResultS<Personaje> {
        val response = try {
            api.getPersonaje(id)
        } catch (e: Exception) {
            return MyResultS.Failure("Ocurrió un error")
        }
        return MyResultS.Success(response.toPersonaje())
    }
}