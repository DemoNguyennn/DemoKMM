package com.example.kmmoviedb.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.kmmoviedb.Greeting
import com.example.kmmoviedb.Movie
import com.example.kmmoviedb.api.MovieData
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Movies()
                }
            }
        }
    }
}

@Composable
private fun Movies(){
    val items = remember { mutableStateListOf<Movie>() }
    runBlocking {
        MovieData().getTopRatedMovies().apply {
            items.addAll(this.results)
        }
    }


    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(items.toList()) {
            MovieItem(it)
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(100.dp)
    ) {
        Column(modifier = Modifier) {
            movie.posterPath?.let {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    model = "https://image.tmdb.org/t/p/w500/${movie.backdropPath}",
                    contentDescription = null
                )
            }
            Text(modifier = Modifier.align(End), text = movie.originalTitle ?: "", fontSize = 12.sp)
        }
        movie.posterPath?.let {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                model = movie.posterPath,
                contentDescription = null
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
