package com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.ui.home

import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.domain.model.personajes.Personajes

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty.ui.home
 * Created by Julio Cesar Camacho Silva on 07/12/22
 */
data class HomeState(
    val personajes: List<Personajes> = emptyList(),
    val showPrevious: Boolean = false,
    val showNext: Boolean = false,
    val isLoading: Boolean = false
)
