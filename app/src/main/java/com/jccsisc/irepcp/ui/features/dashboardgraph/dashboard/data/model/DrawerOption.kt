package com.jccsisc.irepcp.ui.features.dashboardgraph.dashboard.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.List
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.features.dashboard.data.model
 * Created by Julio Cesar Camacho Silva on 22/11/22
 */
data class DrawerOption(val id: Int, val name: String, val navIcon: ImageVector)

val listOptions = listOf(
    DrawerOption(0, "Home", Icons.Default.Home),
    DrawerOption(1, "Inicio de ruta", Icons.Filled.ExitToApp),
    DrawerOption(2, "Lista de clientes", Icons.Default.LocationOn),
    DrawerOption(3, "¿Cómo lo estoy haciendo?", Icons.Filled.DateRange),
    DrawerOption(4, "Reportes", Icons.Sharp.List),
    DrawerOption(5, "Desprogramar ruta", Icons.Filled.Build),
    DrawerOption(6, "Fin de ruta", Icons.Filled.Close),
    DrawerOption(6, "Cerrar sesión", Icons.Sharp.Close)
)