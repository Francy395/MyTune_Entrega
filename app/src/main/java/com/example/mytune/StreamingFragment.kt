package com.example.mytune

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment

class StreamingBrowserFragment : Fragment() {

    private lateinit var webView: WebView
    private lateinit var spinner: Spinner

    // URLs oficiales de las plataformas
    private val platforms = mapOf(
        "Spotify" to "https://open.spotify.com/",
        "YouTube Music" to "https://music.youtube.com/",
        "Apple Music" to "https://music.apple.com/",
        "SoundCloud" to "https://soundcloud.com/"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_streaming, container, false)

        spinner = view.findViewById(R.id.spinnerPlatforms)
        webView = view.findViewById(R.id.webView)

        // Configurar WebView
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        // Cargar por defecto la primera opción
        val defaultUrl = platforms.values.first()
        webView.loadUrl(defaultUrl)

        // Detectar cambios en el spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selected = parent.getItemAtPosition(position).toString()
                val url = platforms[selected]
                url?.let { webView.loadUrl(it) }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        return view
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }
}
