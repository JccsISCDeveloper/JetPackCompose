package com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.jccsisc.irepcp.R
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.domain.model.personaje.Personaje
import com.jccsisc.irepcp.ui.theme.GrayBg
import com.jccsisc.irepcp.ui.theme.PrimaryColor
import com.jccsisc.irepcp.ui.theme.PrimaryDarkColor
import com.jccsisc.irepcp.ui.theme.PrimaryLight
import com.jccsisc.irepcp.utils.components.loadings.SimpleCircularProgressDialog
import kotlinx.coroutines.flow.collectLatest

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty.ui.detail
 * Created by Julio Cesar Camacho Silva on 06/12/22
 */
@Composable
fun DetailRickAndMortyScreen(
    navigateToBack: () -> Unit,
    viewModel: DetailRickAndMortyViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val eventFlow = viewModel.eventFlow
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        eventFlow.collectLatest { event ->
            when (event) {
                is DetailRickAndMortyViewModel.UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = event.msg)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg),
        scaffoldState = scaffoldState
    ) { padding ->

        DetailContent(
            isLoading = state.isLoading,
            navigateToBack = navigateToBack,
            personaje = state.personaje,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun DetailContent(
    isLoading: Boolean,
    navigateToBack: () -> Unit,
    personaje: Personaje?,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column {
            HeaderDetail(
                personaje = personaje,
                modifier = modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            BodyDetail(personaje)
        }
        IconButton(onClick = navigateToBack) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "ic arrowback",
                tint = Color.White
            )
        }
    }
    if (isLoading) SimpleCircularProgressDialog()
}

@Composable
fun HeaderDetail(
    personaje: Personaje?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    PrimaryDarkColor, PrimaryColor, PrimaryLight
                )
            )
        ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        ImagePersonaje(image = personaje?.image)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = personaje?.name ?: "",
            style = MaterialTheme.typography.h4,
            color = GrayBg
        )
    }
}

@Composable
fun ImagePersonaje(image: String?) {
    ImageContainer(modifier = Modifier.size(130.dp)) {
        Box {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .size(Size.ORIGINAL)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ImageContainer(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Surface(modifier.aspectRatio(1f), shape = CircleShape) {
        content()
    }
}

@Composable
fun BodyDetail(personaje: Personaje?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        DetailProperty(
            label = stringResource(R.string.specie),
            value = personaje?.species,
            imageVector = Icons.Filled.EmojiPeople
        )
        DetailProperty(
            label = stringResource(R.string.status),
            value = personaje?.status,
            imageVector = Icons.Outlined.Help
        )
        DetailProperty(
            label = stringResource(R.string.gender),
            value = personaje?.gender,
            imageVector = Icons.Outlined.SafetyDivider
        )
        DetailProperty(
            label = stringResource(R.string.first_location),
            value = personaje?.origin?.name,
            imageVector = Icons.Outlined.Visibility
        )
        DetailProperty(
            label = stringResource(R.string.last_location),
            value = personaje?.location?.name,
            imageVector = Icons.Outlined.LocationOn
        )
    }
}

@Composable
fun DetailProperty(
    modifier: Modifier = Modifier,
    label: String,
    value: String?,
    imageVector: ImageVector
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 4.dp),
        elevation = 3.dp,
        backgroundColor = PrimaryLight
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(Modifier.width(10.dp))
            Column {
                Text(text = label, fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(Modifier.height(5.dp))
                Text(
                    text = value ?: "",
                    color = Color.White,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

