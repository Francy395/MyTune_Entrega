package com.example.mytune

import com.example.mytune.ArtitasData.listaArtista
import com.example.mytune.model.Artista
import com.example.mytune.model.Perfil

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
