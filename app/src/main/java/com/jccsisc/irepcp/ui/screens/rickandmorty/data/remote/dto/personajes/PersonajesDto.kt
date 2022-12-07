package com.jccsisc.irepcp.ui.screens.rickandmorty.data.remote.dto.personajes

import com.jccsisc.irepcp.ui.screens.rickandmorty.domain.model.personajes.Personajes

data class PersonajesDto(
    val info: Info,
    val results: List<Result>
)

fun PersonajesDto.toListPersonajes(): List<Personajes> {
    val resultsEntries = results.mapIndexed { _, entries ->
        Personajes(
            id = entries.id,
            name = entries.name,
            specie = entries.species,
            image = entries.image
        )
    }
    return resultsEntries
}