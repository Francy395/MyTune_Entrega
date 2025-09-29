package com.example.mytune

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(PerfilFragment())

        val btnPerfil = findViewById<Button>(R.id.btnPerfil)
        val btnAlbumes = findViewById<Button>(R.id.btnAlbumes)
        val btnVideos = findViewById<Button>(R.id.btnVideos)
        val btnStreaming = findViewById<Button>(R.id.btnStreaming)
        val btnListas = findViewById<Button>(R.id.btnListas)

        btnPerfil.setOnClickListener { replaceFragment(PerfilFragment()) }
        btnAlbumes.setOnClickListener { replaceFragment(AlbumesFragment()) }
        btnVideos.setOnClickListener { replaceFragment(VideoFragment()) }
        btnStreaming.setOnClickListener { replaceFragment(StreamingBrowserFragment()) }
        btnListas.setOnClickListener { replaceFragment(PlaylistsFragment()) }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
