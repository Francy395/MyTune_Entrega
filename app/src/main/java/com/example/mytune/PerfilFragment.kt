package com.example.mytune

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.mytune.model.Artista

class PerfilFragment : Fragment() {

    private lateinit var txtNombre: TextView
    private lateinit var txtStats: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        val containerArtistas = view.findViewById<LinearLayout>(R.id.containerArtistas)
        val btnAgregarArtista = view.findViewById<Button>(R.id.btnAgregarArtista)
        val btnActualizarUsuario = view.findViewById<Button>(R.id.btnEditarPerfil)
        txtNombre = view.findViewById<TextView>(R.id.txtTituloPerfil)
        txtStats = view.findViewById<TextView>(R.id.txtStatsPerfil)

        //Mostra informacion del usuario

        actualizarVistaUsuario()

        // Mostrar Artistas Favoritos
        for (cancion in ArtitasData.listaArtista) {
            if (cancion.esfavorito) {
                mostrarArtista(inflater, containerArtistas, cancion)
            }
        }

        // Configurar botón para agregar artista
        btnAgregarArtista.setOnClickListener {
            mostrarDialogoAgregarCancion(inflater, containerArtistas)
        }

        btnActualizarUsuario.setOnClickListener {
            MostraDialogoActualizarUsuario(inflater,)
        }

        return view
    }

    private fun mostrarArtista(inflater: LayoutInflater, container: LinearLayout, artista: Artista) {
        val itemView = inflater.inflate(R.layout.item_song, container, false)
        val txtNombre = itemView.findViewById<TextView>(R.id.songTitle)
        val txtGenero = itemView.findViewById<TextView>(R.id.songArtist)
        val btnFavorite = itemView.findViewById<ImageView>(R.id.btnFavorite)
        txtNombre.text = artista.nombre
        txtGenero.text = artista.genero
        var isFavorite = artista.esfavorito
        btnFavorite.setImageResource(
            if (isFavorite) R.drawable.ic_favorite_filled
            else R.drawable.ic_favorite_border
        )
        btnFavorite.setOnClickListener {
            isFavorite = !isFavorite
            artista.esfavorito = isFavorite
            btnFavorite.setImageResource(
                if (isFavorite) R.drawable.ic_favorite_filled
                else R.drawable.ic_favorite_border
            )
            UsuarioData.usuarioInfo.numArtistasFavo = ArtitasData.listaArtista.count{ it.esfavorito }
            actualizarVistaUsuario()
        }
        container.addView(itemView)
    }
    private fun mostrarDialogoAgregarCancion(inflater: LayoutInflater, container: LinearLayout) {
        val dialogView = inflater.inflate(R.layout.dialog_add_song, null)
        val inputNombre = dialogView.findViewById<TextView>(R.id.inputTitulo)
        val inputGenero = dialogView.findViewById<TextView>(R.id.inputGenero)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Agregar Artista")
            .setView(dialogView)
            .setPositiveButton("Agregar") { _, _ ->
                val nombreStr = inputNombre.text.toString().ifBlank { "Sin título" }
                val generoStr = inputGenero.text.toString().ifBlank { "Desconocido" }

                val artitaNuevo = Artista(nombreStr,generoStr,true)
                ArtitasData.listaArtista.add(artitaNuevo)
                UsuarioData.usuarioInfo.numArtistasFavo = ArtitasData.listaArtista.count{ it.esfavorito }
                actualizarVistaUsuario()
                mostrarArtista(inflater, container, artitaNuevo)
            }
            .setNegativeButton("Cancelar", null)
            .create()
        dialog.show()
    }

    private fun actualizarVistaUsuario() {
        txtNombre.text = UsuarioData.usuarioInfo.nombre
        txtStats.text = "Artistas: " + UsuarioData.usuarioInfo.numArtistasFavo.toString()
        txtNombre.textAlignment = View.TEXT_ALIGNMENT_CENTER
        txtStats.textAlignment = View.TEXT_ALIGNMENT_CENTER
    }

    private fun MostraDialogoActualizarUsuario(inflater: LayoutInflater){
        val dialogView = inflater.inflate(R.layout.dialog_update_info_user, null)
        val inputNombre = dialogView.findViewById<TextView>(R.id.inputNombreUser)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Actualizar Usuario")
            .setView(dialogView)
            .setPositiveButton("Actualizar") { _, _ ->
                val nombreStr = inputNombre.text.toString().ifBlank { "NA" }
                UsuarioData.usuarioInfo.nombre = nombreStr
                actualizarVistaUsuario()
            }
            .setNegativeButton("Cancelar", null)
            .create()
        dialog.show()
    }
}
