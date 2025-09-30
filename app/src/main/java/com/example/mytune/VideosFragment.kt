package com.example.mytune

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.MediaController
import android.widget.Spinner
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.core.net.toUri

class VideoFragment : Fragment() {

    private lateinit var videoView: VideoView
    private lateinit var spinner: Spinner

    // Lista de títulos y sus videos
    private val videoOptions = listOf(
        "Dragon" to R.raw.dragon,
        "Levitating" to R.raw.levitating_v,
        "Take My Breath" to R.raw.take_my_breath_v,
        "Bones" to R.raw.bones_v
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_videos, container, false)

        videoView = view.findViewById(R.id.videoView)
        spinner = view.findViewById(R.id.spinnerVideos)

        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        // Adaptador con layouts personalizados
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.item_spinner,   // ítem seleccionado (blanco)
            videoOptions.map { it.first }     // nombres de los videos
        )
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown) // ítems desplegables (negro)
        spinner.adapter = adapter

        // Listener del Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val videoResId = videoOptions[position].second
                playVideo(videoResId)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No hacer nada
            }
        }

        // Reproducir automáticamente el primer video
        playVideo(videoOptions.first().second)

        return view
    }

    private fun playVideo(videoResId: Int) {
        val videoUri = "android.resource://${requireContext().packageName}/$videoResId".toUri()
        videoView.setVideoURI(videoUri)
        videoView.requestFocus()
        videoView.start()
    }

    override fun onPause() {
        super.onPause()
        if (this::videoView.isInitialized && videoView.isPlaying) {
            videoView.pause()
        }
    }
}
