package com.example.mytune.model

data class Album(
    val nombre: String,
    val artista: String,
    val canciones: List<String>,
    val recursos: List<Int>
)
