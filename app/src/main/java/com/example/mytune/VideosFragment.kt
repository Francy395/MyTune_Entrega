package com.example.mytune

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.core.net.toUri

class VideoFragment : Fragment() {

    private lateinit var videoView: VideoView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_videos, container, false)

        videoView = view.findViewById(R.id.videoView)

        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(videoView)

        val videoUri = "android.resource://${requireContext().packageName}/${R.raw.dragon}".toUri()
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(videoUri)
        videoView.requestFocus()
        videoView.start()

        return view
    }

    override fun onPause() {
        super.onPause()
        if (this::videoView.isInitialized && videoView.isPlaying) {
            videoView.pause()
        }
    }
}
