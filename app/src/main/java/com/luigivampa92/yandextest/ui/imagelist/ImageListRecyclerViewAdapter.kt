package com.luigivampa92.yandextest.ui.imagelist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.luigivampa92.yandextest.domain.model.Image

class ImageListRecyclerViewAdapter(
        private val context: Context,
        private val onItemClick: (Image) -> Unit
) : RecyclerView.Adapter<ImageViewHolder>() {

    private var items: ArrayList<Image> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImageViewHolder(LayoutInflater.from(context), parent, onItemClick)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItems(items: List<Image>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}