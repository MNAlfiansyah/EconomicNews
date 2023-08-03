package com.example.uas_economicnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uas_economicnews.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val url = intent.getStringExtra(EXTRA_URL)

        binding.webView.loadUrl(url.toString())

        binding.backButton.setOnClickListener {
            onBackPressed() // Call the onBackPressed() method to go back when the button is clicked
        }
    }

    companion object {
        const val EXTRA_URL = "extra_url"
    }
}