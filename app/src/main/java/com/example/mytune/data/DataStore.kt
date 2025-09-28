package com.example.mytune

import com.example.mytune.ArtitasData.listaArtista
import com.example.mytune.model.Artista
import com.example.mytune.model.Perfil
import com.example.mytune.model.Album

object ArtitasData {
    val listaArtista = mutableListOf(
        Artista("The Weeknd", "Pop/R&B",true),
        Artista("Dua Lipa", "Pop", true),
        Artista("Diomedes Diaz", "Vallenato", true),
        Artista("Bad Bunny", "Reguetón", true),
        Artista("Freddie Mercury", "Rock", true)
    )
}

object UsuarioData {
    val usuarioInfo =  Perfil("User local",listaArtista.count{ it.esfavorito })
}

object AlbumesData {
    val listaAlbumes = mutableListOf(
        Album(
            nombre = "Dawn FM",
            artista = "The Weeknd",
            canciones = listOf("Take My Breath", "Sacrifice", "Out of Time"),
            recursos = listOf(R.raw.take_my_breath, R.raw.sacrifice, R.raw.out_of_time)
        ),
        Album(
            nombre = "Mercury – Act 1",
            artista = "Imagine Dragons",
            canciones = listOf("Enemy", "Bones", "Wrecked"),
            recursos = listOf(R.raw.enemy, R.raw.bones, R.raw.wrecked)
        ),
        Album(
            nombre = "Future Nostalgia",
            artista = "Dua Lipa",
            canciones = listOf("Levitating", "Don’t Start Now", "Hallucinate"),
            recursos = listOf(R.raw.levitating, R.raw.dont_start_now, R.raw.hallucinate)
        ),
        Album(
            nombre = "Positions",
            artista = "Ariana Grande",
            canciones = listOf("34+35", "Positions", "POV"),
            recursos = listOf(R.raw._34_35, R.raw.positions, R.raw.pov)
        )
    )
}
