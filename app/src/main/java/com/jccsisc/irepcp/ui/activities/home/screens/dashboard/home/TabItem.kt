package com.jccsisc.irepcp.ui.activities.home.screens.dashboard.home

import androidx.compose.runtime.Composable
import com.jccsisc.irepcp.R

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.detailsfavorites
 * Created by Julio Cesar Camacho Silva on 02/12/22
 */
typealias MiFuncion = @Composable () -> Unit
sealed class TabItem(var icon: Int, var title: String, var screen: MiFuncion) {
    object ItemDescription: TabItem(R.drawable.ic_description, "Descripcion", { DescripcionScreen() })
    object ItemBenefits: TabItem(R.drawable.ic_benefits, "Beneficios", { BeneficiosScreen() })
    object ItemRecipes: TabItem(R.drawable.ic_recipes, "Recetas", { RecetasScreen() })
}
