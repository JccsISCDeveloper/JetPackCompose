package com.jccsisc.irepcp.ui.screens.homegraph.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jccsisc.irepcp.ui.screens.homegraph.home.data.model.DrawerOption
import com.jccsisc.irepcp.ui.theme.BlackAbi
import com.jccsisc.irepcp.ui.theme.ColorHearder
import com.jccsisc.irepcp.ui.theme.Gray50

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.homegraph.home.ui.components
 * Created by Julio Cesar Camacho Silva on 23/11/22
 */
@Composable
fun MyDrawerLayout(
    onClickDrawer: (option: DrawerOption) -> Unit,
    optionList: List<DrawerOption>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BlackAbi)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(ColorHearder)
        ) {

            CircleFigure(modifier = modifier.padding(top = 30.dp, start = 12.dp))

            CircularProgressIndicator(
                progress = 0.6f,
                modifier = modifier
                    .padding(top = 30.dp, start = 12.dp)
                    .size(60.dp),
                color = Color.Blue,
                strokeWidth = 3.dp
            )
        }

        LazyColumn {
            items(optionList) { option ->
                ItemDrawer(option = option, onOptionClick = {
                    onClickDrawer(it)
                })
            }
        }
    }
}

@Composable
fun CircleFigure(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.size(60.dp).clip(CircleShape).background(Gray50))
        Box(modifier = Modifier.size(53.dp).clip(CircleShape).background(ColorHearder))
    }
}

@Composable
fun ItemDrawer(
    option: DrawerOption,
    onOptionClick: (option: DrawerOption) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onOptionClick(option) }) {
        Icon(
            imageVector = option.navIcon,
            contentDescription = option.name,
            modifier = modifier.padding(10.dp),
            tint = Color.White
        )
        Text(text = option.name, modifier = modifier.padding(10.dp), color = Color.White)
    }
}
