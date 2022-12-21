package com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.activities.home.generalcomponents.GenericTopBar
import com.jccsisc.irepcp.ui.activities.home.generalcomponents.ImageContainer
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.domain.model.personajes.Personajes
import com.jccsisc.irepcp.ui.theme.PrimaryColor
import com.jccsisc.irepcp.utils.components.loadings.SimpleCircularProgressDialog
import com.jccsisc.irepcp.utils.setCoilImagePainter
import kotlinx.coroutines.flow.collectLatest

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty.ui
 * Created by Julio Cesar Camacho Silva on 06/12/22
 * url: https://rickandmortyapi.com
 */
@Composable
fun HomeRickAndMortyScreen(
    navigateToDetail: (Int) -> Unit,
    navigateBack: () -> Unit,
    viewModel: HomeRickAndMortyViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val eventFlow = viewModel.eventFlow
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        eventFlow.collectLatest { event ->
            when (event) {
                is HomeRickAndMortyViewModel.UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.msg
                    )
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColor),
        scaffoldState = scaffoldState,
        topBar = {
            GenericTopBar(
                navigateBack = navigateBack,
                title = stringResource(id = R.string.home_title_rick_and_morty)
            )
        },
        bottomBar = {
            HomeBottomBar(
                showPrevious = state.showPrevious,
                showNext = state.showNext,
                onPreviousPressed = { viewModel.getPersonajes(false) },
                onNextPressed = { viewModel.getPersonajes(true) }
            )
        }
    ) { padding ->
        HomeContent(
            onItemClicked = { navigateToDetail(it) },
            isLoading = state.isLoading,
            personajes = state.personajes,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
private fun HomeContent(
    onItemClicked: (Int) -> Unit,
    isLoading: Boolean = false,
    personajes: List<Personajes> = emptyList(),
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.padding_6)),
            modifier = Modifier.fillMaxWidth(),
            content = {
                items(personajes.size) { index ->
                    CardPersonaje(
                        item = personajes[index],
                        onItemClicked = { onItemClicked(it) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        )
        if (isLoading) {
            SimpleCircularProgressDialog()
        }
    }
}

@Composable
private fun CardPersonaje(
    item: Personajes,
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onItemClicked(item.id) }
            .padding(
                top = dimensionResource(id = R.dimen.padding_10),
                start = dimensionResource(id = R.dimen.padding_6),
                end = dimensionResource(id = R.dimen.padding_6),
                bottom = dimensionResource(id = R.dimen.padding_10)
            )
    ) {
        ImageContainer(modifier = Modifier.size(64.dp)) {
            PersonajeImage(item = item)
        }
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        ) {
            Text(text = item.name, style = MaterialTheme.typography.subtitle1)
            //Para darle un toque de suavidad
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = item.specie, style = MaterialTheme.typography.caption)
            }
        }
        Divider(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_10)))
    }
}

@Composable
private fun PersonajeImage(item: Personajes) {
    Box {
        Image(
            painter = setCoilImagePainter(item.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
private fun HomeBottomBar(
    showPrevious: Boolean,
    showNext: Boolean,
    onPreviousPressed: () -> Unit,
    onNextPressed: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.padding_3)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = onPreviousPressed,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                enabled = showPrevious
            ) {
                Text(text = stringResource(id = R.string.previous_button))
            }
            TextButton(
                onClick = onNextPressed,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                enabled = showNext
            ) {
                Text(text = stringResource(id = R.string.next_button))
            }
        }
    }
}