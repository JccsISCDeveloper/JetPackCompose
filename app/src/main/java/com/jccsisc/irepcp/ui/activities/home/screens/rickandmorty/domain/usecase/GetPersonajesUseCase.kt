package com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.domain.usecase

import com.jccsisc.irepcp.core.MyResultS
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.domain.model.personajes.Personajes
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.domain.repositories.PersonajeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty.domain.usecase
 * Created by Julio Cesar Camacho Silva on 06/12/22
 */
class GetPersonajesUseCase @Inject constructor(private val repository: PersonajeRepository) {
    operator fun invoke(page: Int): Flow<MyResultS<List<Personajes>>> = repository.getPersonajes(page)
}