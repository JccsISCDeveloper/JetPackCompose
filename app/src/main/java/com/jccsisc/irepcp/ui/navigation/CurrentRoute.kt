package com.jccsisc.irepcp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.navigation
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
@Composable
fun CurrentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}