package com.jccsisc.irepcp.ui.screens.rickandmorty.ui.detail

import com.jccsisc.irepcp.ui.screens.rickandmorty.domain.model.personaje.Personaje

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty.ui.detail
 * Created by Julio Cesar Camacho Silva on 07/12/22
 */
data class DetailState(
    val character: Personaje? = null,
    val isLoading: Boolean = false
)
