package com.example.mytune

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mytune.model.Playlist

class PlaylistsAdapter(context: Context, private val playlists: List<Playlist>) :
    ArrayAdapter<Playlist>(context, 0, playlists) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val playlist = playlists[position]
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_playlist, parent, false)

        val txtName = view.findViewById<TextView>(R.id.txtPlaylistName)
        txtName.text = playlist.nombre

        return view
    }
}
