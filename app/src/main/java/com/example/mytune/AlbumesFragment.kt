package com.example.mytune

import android.app.AlertDialog
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.mytune.model.Album

class AlbumesFragment : Fragment() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_albumes, container, false)

        // Referencias a los 4 álbumes en el layout
        val cardAlbum1 = view.findViewById<CardView>(R.id.cardAlbum1)
        val cardAlbum2 = view.findViewById<CardView>(R.id.cardAlbum2)
        val cardAlbum3 = view.findViewById<CardView>(R.id.cardAlbum3)
        val cardAlbum4 = view.findViewById<CardView>(R.id.cardAlbum4)

        // Lista de álbumes desde AlbumesData
        val albumes = AlbumesData.listaAlbumes

        // Click listeners dinámicos
        cardAlbum1.setOnClickListener { mostrarMenuAlbum(albumes[0]) }
        cardAlbum2.setOnClickListener { mostrarMenuAlbum(albumes[1]) }
        cardAlbum3.setOnClickListener { mostrarMenuAlbum(albumes[2]) }
        cardAlbum4.setOnClickListener { mostrarMenuAlbum(albumes[3]) }

        return view
    }

    private fun mostrarMenuAlbum(album: Album) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Canciones de ${album.nombre} - ${album.artista}")

        val cancionesArray = album.canciones.toTypedArray()

        // Lista de canciones
        builder.setItems(cancionesArray) { _, which ->
            reproducirCancion(album.recursos[which])
            Toast.makeText(
                requireContext(),
                "Reproduciendo: ${cancionesArray[which]}",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Botón para agregar canción
        builder.setPositiveButton("➕ Agregar canción") { _, _ ->
            mostrarDialogoAgregarCancion(album)
        }

        builder.setNegativeButton("Cerrar", null)
        builder.show()
    }

    private fun mostrarDialogoAgregarCancion(album: Album) {
        val input = EditText(requireContext())
        input.hint = "Nombre de la canción"

        AlertDialog.Builder(requireContext())
            .setTitle("Agregar a ${album.nombre}")
            .setView(input)
            .setPositiveButton("Guardar") { _, _ ->
                val nuevaCancion = input.text.toString()
                if (nuevaCancion.isNotBlank()) {
                    album.canciones.toMutableList().add(nuevaCancion)
                    Toast.makeText(
                        requireContext(),
                        "Agregada: $nuevaCancion",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(requireContext(), "Debe escribir un nombre", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun reproducirCancion(resourceId: Int) {
        // Detener si ya hay una canción sonando
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(requireContext(), resourceId)
        mediaPlayer?.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
