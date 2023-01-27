package com.example.kmmoviedb.android.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kmmoviedb.android.composable.Slogan
import com.example.kmmoviedb.demo.model.Movie
import com.example.kmmoviedb.demo.service.MovieRepo
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviesScreen() {
    val items = remember { mutableStateListOf<Movie>() }
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    val openDetail = remember { mutableStateOf<Int?>(null) }

    runBlocking {
        MovieRepo().getTopRatedMovies().apply {
            items.addAll(this.results)
        }
    }

    ModalBottomSheetLayout(
        modifier = Modifier,
        sheetState = modalSheetState,
        sheetContent = { DetailBottomSheet() }) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Slogan(modifier = Modifier.align(Alignment.CenterHorizontally))

            Divider(thickness = 1.dp, color = Color.Yellow.copy(alpha = 0.3f))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(all = 8.dp),
                verticalArrangement = Arrangement.spacedBy(48.dp)
            ) {
                items(items.toList()) {
                    MovieItem(
                        Modifier.clickable {
                            openDetail.value = it.id
                        }, it
                    )
                }
            }
        }
    }

    LaunchedEffect(key1 = openDetail.value) {
        if (!modalSheetState.isVisible && openDetail.value != null) {
            modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
        } else {
            modalSheetState.hide()
        }
    }
}

@Composable
private fun MovieItem(modifier: Modifier, movie: Movie) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .then(modifier)
    ) {
        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .graphicsLayer {
                        shadowElevation = 39f
                        shape = RoundedCornerShape(8.dp)
                        clip = true
                        ambientShadowColor = Color.Yellow
                        spotShadowColor = Color.Yellow
                    },
                model = "https://image.tmdb.org/t/p/w500/${movie.backdropPath}",
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(40.dp))
            Divider(
                modifier.fillMaxWidth(fraction = 0.85f),
                thickness = 1.dp,
                color = Color.Yellow.copy(alpha = 0.1f)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.85f)
                .padding(bottom = 4.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(150.dp)
                    .graphicsLayer {
                        shadowElevation = 30f
                        shape = RoundedCornerShape(4.dp)
                        clip = true
                        ambientShadowColor = Color.DarkGray
                        spotShadowColor = Color.DarkGray
                    },
                model = "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = movie.originalTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFEDBD0)
            )
        }
    }
}

@Composable
private fun DetailBottomSheet() {
    Text(modifier = Modifier
        .fillMaxSize() ,text = "BottomSheet")
}