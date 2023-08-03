package com.example.uas_economicnews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.uas_economicnews.databinding.ActivityDetailBeritaBinding
import com.example.uas_economicnews.ui.response.BeritaResponse
import com.google.gson.Gson
import java.text.SimpleDateFormat

class DetailBeritaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBeritaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityDetailBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = Gson().fromJson(intent.getStringExtra("res"), BeritaResponse.Berita::class.java)

        if (data != null) {
            Toast.makeText(this, "test", Toast.LENGTH_SHORT)

            binding.titleBerita.text = data.title
            binding.summaryBerita.text = data.summary
            binding.tglBerita.text = dateFormatting(data.time_published.toString())
            binding.sentimenBerita.text = data.overall_sentiment_label
            if (data.overall_sentiment_label.toString().toLowerCase().contains("bullish")) {
                binding.sentimenImageBerita.setImageResource(R.drawable.ic_bullish)
            } else if (data.overall_sentiment_label.toString().toLowerCase().contains("bearish")) {
                binding.sentimenImageBerita.setImageResource(R.drawable.ic_bearish)
            } else {
                binding.sentimenImageBerita.setImageResource(R.drawable.ic_netral)
            }
            Glide.with(binding.imageBerita.context).load(data.banner_image).placeholder(R.drawable.photo_default).into(binding.imageBerita)

        }

        binding.backButton.setOnClickListener {
            onBackPressed() // Call the onBackPressed() method to go back when the button is clicked
        }

        binding.shareButton.setOnClickListener {
            shareContent(data.title.toString())
        }

        binding.readMoreButton.setOnClickListener {
            val url = data.url
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(WebViewActivity.EXTRA_URL, url)
            startActivity(intent)
        }
    }

    private fun dateFormatting(sourceDate: String): String {
        val sourceFormat = SimpleDateFormat("yyyyMMdd'T'HHmmss")
        val targetFormat = SimpleDateFormat("dd MMMM yyyy HH:mm")

        try {
            val date = sourceFormat.parse(sourceDate)
            return targetFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }

    private fun shareContent(content: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, content)
            type = "text/plain"
        }

        // Start the chooser activity to allow the user to select the app they want to share with
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

}