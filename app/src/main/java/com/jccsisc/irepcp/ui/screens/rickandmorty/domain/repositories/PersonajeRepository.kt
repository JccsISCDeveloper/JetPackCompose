package com.jccsisc.irepcp.ui.screens.rickandmorty.domain.repositories

import com.jccsisc.irepcp.core.MyResultS
import com.jccsisc.irepcp.ui.screens.rickandmorty.domain.model.personaje.Personaje
import com.jccsisc.irepcp.ui.screens.rickandmorty.domain.model.personajes.Personajes
import kotlinx.coroutines.flow.Flow

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty.domain.repositories
 * Created by Julio Cesar Camacho Silva on 06/12/22
 */
interface PersonajeRepository {
    fun getPersonajes(page: Int): Flow<MyResultS<List<Personajes>>>
    suspend fun getPersonaje(id: Int): MyResultS<Personaje>
}