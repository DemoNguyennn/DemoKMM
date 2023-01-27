package com.example.kmmoviedb.demo.service

import com.example.kmmoviedb.demo.model.MovieResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

class MovieRepo {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
        engine {
            pipelining = true
            threadsCount = 4
        }
    }
    suspend fun getTopRatedMovies(): MovieResponse {
        return httpClient.get("https://api.themoviedb.org/3/movie/top_rated?api_key=09d7a831c79372626b4eca6e2f8487d1&language=en-US&page=1") {
            contentType(ContentType.Application.Json)
            headers {
                append("Accept", "application/json")
            }
        }.body()
    }
}