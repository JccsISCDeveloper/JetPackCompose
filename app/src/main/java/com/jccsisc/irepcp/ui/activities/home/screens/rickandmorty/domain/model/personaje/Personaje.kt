package com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.domain.model.personaje

import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.data.remote.dto.personajes.Location
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.data.remote.dto.personajes.Origin

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty.domain.model.personaje
 * Created by Julio Cesar Camacho Silva on 06/12/22
 */
data class Personaje(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String
)
