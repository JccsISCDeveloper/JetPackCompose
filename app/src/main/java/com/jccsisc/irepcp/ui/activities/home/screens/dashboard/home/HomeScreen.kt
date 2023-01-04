package com.jccsisc.irepcp.ui.activities.home.screens.dashboard.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.google.accompanist.pager.*
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
        TabItem.ItemBenefits,
        TabItem.ItemRecipes
    )

    val pagerState = rememberPagerState()

    Column() {
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
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
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
                        contentDescription = ""
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
        var title = tabs[page].title
        Text(text = "Información")
        tabs[page].screen()
    }
}