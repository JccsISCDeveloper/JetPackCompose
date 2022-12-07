package com.jccsisc.irepcp.ui.screens.rickandmorty.data.remote.dto.personaje

import com.jccsisc.irepcp.ui.screens.rickandmorty.data.remote.dto.personajes.Location
import com.jccsisc.irepcp.ui.screens.rickandmorty.data.remote.dto.personajes.Origin
import com.jccsisc.irepcp.ui.screens.rickandmorty.domain.model.personaje.Personaje

data class PersonajeDto(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

fun PersonajeDto.toPersonaje() = Personaje(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    origin = origin,
    location = location,
    image = image
)