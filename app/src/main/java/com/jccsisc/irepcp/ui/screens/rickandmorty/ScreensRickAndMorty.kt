package com.jccsisc.irepcp.ui.screens.rickandmorty

import com.jccsisc.irepcp.core.constants.Constants.DETAIL_RICK_AND_MORTY
import com.jccsisc.irepcp.core.constants.Constants.HOME_RICK_AND_MORTY
import com.jccsisc.irepcp.core.constants.Constants.ID

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty
 * Created by Julio Cesar Camacho Silva on 06/12/22
 */
sealed class ScreensRickAndMorty(val route: String) {
    object HomeRickAndMortyScreen: ScreensRickAndMorty(HOME_RICK_AND_MORTY)
    object DetailRickAndMortyScreen: ScreensRickAndMorty("${DETAIL_RICK_AND_MORTY}?$ID={$ID}") {
        fun passId(id: Int) = "${DETAIL_RICK_AND_MORTY}?$ID=$id"
    }
}
