package com.jccsisc.irepcp.ui.screens.dashboard.navigation.model

import androidx.annotation.DrawableRes
import com.jccsisc.irepcp.ui.screens.dashboard.navigation.ScreenChildItemDrawer

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.navigation.model
 * Created by Julio Cesar Camacho Silva on 28/11/22
 */
data class DrawerItem(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int,
    val expandableOptions: List<ScreenChildItemDrawer>? = listOf()
)
