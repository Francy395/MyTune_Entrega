package com.example.mytune

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mytune.model.Playlist

class PlaylistsFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var adapter: PlaylistsAdapter
    private val playlists = PlaylistsData.listaPlaylists  // 👈 tus playlists

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_listas, container, false)

        listView = view.findViewById(R.id.listPlaylists)
        val btnCrear = view.findViewById<Button>(R.id.btnCrearPlaylist)

        // 🔹 Usar nuestro adapter personalizado
        adapter = PlaylistsAdapter(requireContext(), playlists)
        listView.adapter = adapter

        // Evento al presionar playlist
        listView.setOnItemClickListener { _, _, position, _ ->
            mostrarMenuPlaylist(playlists[position])
        }

        // Evento crear playlist
        btnCrear.setOnClickListener {
            mostrarDialogoCrearPlaylist()
        }

        return view
    }

    private fun mostrarMenuPlaylist(playlist: Playlist) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Canciones en ${playlist.nombre}")

        val cancionesArray = playlist.canciones.toTypedArray()

        builder.setItems(cancionesArray) { _, which ->
            Toast.makeText(requireContext(), "Seleccionaste: ${cancionesArray[which]}", Toast.LENGTH_SHORT).show()
        }

        builder.setPositiveButton("➕ Agregar canción") { _, _ ->
            mostrarDialogoAgregarCancion(playlist)
        }

        builder.setNegativeButton("➖ Eliminar canción") { _, _ ->
            mostrarDialogoEliminarCancion(playlist)
        }

        builder.setNeutralButton("Cerrar", null)
        builder.show()
    }

    private fun mostrarDialogoAgregarCancion(playlist: Playlist) {
        val input = android.widget.EditText(requireContext())
        AlertDialog.Builder(requireContext())
            .setTitle("Agregar canción")
            .setView(input)
            .setPositiveButton("Agregar") { _, _ ->
                val nueva = input.text.toString()
                if (nueva.isNotBlank()) {
                    playlist.canciones.add(nueva)
                    Toast.makeText(requireContext(), "Agregada: $nueva", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun mostrarDialogoEliminarCancion(playlist: Playlist) {
        if (playlist.canciones.isEmpty()) {
            Toast.makeText(requireContext(), "No hay canciones para eliminar", Toast.LENGTH_SHORT).show()
            return
        }

        val cancionesArray = playlist.canciones.toTypedArray()

        AlertDialog.Builder(requireContext())
            .setTitle("Eliminar canción")
            .setItems(cancionesArray) { _, which ->
                val eliminada = playlist.canciones.removeAt(which)
                Toast.makeText(requireContext(), "Eliminada: $eliminada", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun mostrarDialogoCrearPlaylist() {
        val input = android.widget.EditText(requireContext())
        AlertDialog.Builder(requireContext())
            .setTitle("Nueva lista de reproducción")
            .setView(input)
            .setPositiveButton("Crear") { _, _ ->
                val nombre = input.text.toString()
                if (nombre.isNotBlank()) {
                    val nuevaPlaylist = Playlist(nombre, mutableListOf())
                    playlists.add(nuevaPlaylist)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(requireContext(), "Lista creada: $nombre", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}
