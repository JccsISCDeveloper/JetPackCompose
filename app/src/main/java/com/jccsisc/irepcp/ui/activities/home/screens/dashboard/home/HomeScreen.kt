package com.jccsisc.irepcp.ui.activities.home.screens.dashboard.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.theme.PrimaryLight2
import kotlinx.coroutines.launch

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.activities.home.screens.dashboard.home
 * Created by Julio Cesar Camacho Silva on 04/01/23
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {
    val tabs = listOf(
        TabItem.ItemDescription,
        TabItem.ItemModules,
        TabItem.ItemComponents
    )

    val pagerState = rememberPagerState()

    Column {
        Tabs(tabs, pagerState)
        TabsContent(tabs, pagerState)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = dimensionResource(id = R.dimen.height_4),
                color = PrimaryLight2
            )
        }
    ) {
        tabs.forEachIndexed { index, tabItem ->
            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                icon = {
                    Image(
                        painter = painterResource(id = tabItem.icon),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                },
                text = { Text(text = tabItem.title, style = MaterialTheme.typography.overline) })
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        val title = tabs[page].title
//        Text(text = "Información -> $title")
        tabs[page].screen()
    }
}