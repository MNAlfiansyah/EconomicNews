package com.example.uas_economicnews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_economicnews.databinding.ItemTopGainerBinding
import com.example.uas_economicnews.ui.response.GainerResponse


class GainerAdapter : RecyclerView.Adapter<GainerAdapter.GainerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GainerViewHolder {
        val binding = ItemTopGainerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return GainerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GainerViewHolder, position: Int) {
        holder.bind(item = differ.currentList[position], position = position)

    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class GainerViewHolder(private val binding: ItemTopGainerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GainerResponse.topGainers, position: Int) {
            binding.run {
                ticker.text= item.ticker
                changePercentage.text = item.change_percentage
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<GainerResponse.topGainers>() {
        override fun areItemsTheSame(
            oldExampleModel: GainerResponse.topGainers, newExampleModel: GainerResponse.topGainers
        ): Boolean {
            return oldExampleModel.ticker == newExampleModel.ticker
        }

        override fun areContentsTheSame(
            oldExampleModel: GainerResponse.topGainers, newExampleModel: GainerResponse.topGainers
        ): Boolean {
            return oldExampleModel == newExampleModel
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

}