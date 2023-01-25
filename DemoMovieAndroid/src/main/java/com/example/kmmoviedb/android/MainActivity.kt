package com.example.kmmoviedb.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kmmoviedb.android.composable.Slogan
import com.example.kmmoviedb.demo.model.Movie
import com.example.kmmoviedb.demo.service.MovieData
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MoviesScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
private fun MoviesScreen() {
    val items = remember { mutableStateListOf<Movie>() }
    runBlocking {
        MovieData().getTopRatedMovies().apply {
            items.addAll(this.results)
        }
    }

    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Slogan(modifier = Modifier.align(CenterHorizontally))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(all = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items.toList()) {
                MovieItem(it)
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        Column(modifier = Modifier) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                model = "https://image.tmdb.org/t/p/w500/${movie.backdropPath}",
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.height(50.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(50.dp)
                    .height(100.dp),
                model = "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
                contentDescription = null
            )
            Text(
                text = movie.originalTitle,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
}
