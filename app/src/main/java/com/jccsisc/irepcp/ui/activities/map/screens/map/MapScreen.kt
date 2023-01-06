package com.jccsisc.irepcp.ui.activities.map.screens.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph.gallery
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
@Composable
fun MapScreen() {



   Box(modifier = Modifier.fillMaxSize()) {

       GoogleMap(modifier = Modifier.fillMaxSize())
   }
}