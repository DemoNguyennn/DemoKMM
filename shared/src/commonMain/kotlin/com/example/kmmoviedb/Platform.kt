package com.example.kmmoviedb

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform