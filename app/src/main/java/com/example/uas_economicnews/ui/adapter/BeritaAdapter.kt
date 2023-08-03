package com.example.uas_economicnews.ui.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uas_economicnews.R
import com.example.uas_economicnews.databinding.ItemBeritaBinding
import com.example.uas_economicnews.ui.response.BeritaResponse
import java.text.SimpleDateFormat


class BeritaAdapter: RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(response: BeritaResponse.Berita)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val binding = ItemBeritaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return BeritaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        holder.bind(item = differ.currentList[position], position = position)

        holder.itemView.setOnClickListener {
            // Notify the listener about the click event and pass the position
            listener?.onItemClick(differ.currentList[position])
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun dateFormatting(sourceDate: String): String {
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

    inner class BeritaViewHolder(private val binding: ItemBeritaBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: BeritaResponse.Berita, position: Int) {

            binding.run {
                Glide.with(photoBerita.context).load(item.banner_image).placeholder(R.drawable.photo_default).into(photoBerita)

                titleBerita.maxLines = 2
                titleBerita.ellipsize = TextUtils.TruncateAt.END
                titleBerita.text = item.title

                sourceBerita.text = "By "+item.source
                tanggalBerita.text = dateFormatting(item.time_published.toString())

            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<BeritaResponse.Berita>() {
        override fun areItemsTheSame(
            oldExampleModel: BeritaResponse.Berita, newExampleModel: BeritaResponse.Berita
        ): Boolean {
            return oldExampleModel.title == newExampleModel.title
        }

        override fun areContentsTheSame(
            oldExampleModel: BeritaResponse.Berita, newExampleModel: BeritaResponse.Berita
        ): Boolean {
            return oldExampleModel == newExampleModel
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

}