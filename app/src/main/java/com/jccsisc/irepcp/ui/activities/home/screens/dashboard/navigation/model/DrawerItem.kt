package com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation.model

import androidx.annotation.DrawableRes
import com.jccsisc.irepcp.ui.activities.home.screens.dashboard.navigation.ScreenBooksChildItemDrawer

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.navigation.model
 * Created by Julio Cesar Camacho Silva on 28/11/22
 */
data class DrawerItem(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int? = -1,
    val expandableOptions: List<ScreenBooksChildItemDrawer>? = listOf()
)
