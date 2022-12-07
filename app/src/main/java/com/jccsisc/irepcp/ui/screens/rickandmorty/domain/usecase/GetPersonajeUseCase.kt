package com.jccsisc.irepcp.ui.screens.rickandmorty.domain.usecase

import com.jccsisc.irepcp.core.MyResultS
import com.jccsisc.irepcp.ui.screens.rickandmorty.domain.model.personaje.Personaje
import com.jccsisc.irepcp.ui.screens.rickandmorty.domain.repositories.PersonajeRepository
import javax.inject.Inject

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty.domain.usecase
 * Created by Julio Cesar Camacho Silva on 06/12/22
 */
class GetPersonajeUseCase @Inject constructor(private val repository: PersonajeRepository) {
    suspend operator fun invoke(id: Int): MyResultS<Personaje> = repository.getPersonaje(id)
}