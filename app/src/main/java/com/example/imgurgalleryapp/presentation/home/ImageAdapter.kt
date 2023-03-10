package com.example.imgurgalleryapp.presentation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imgurgalleryapp.R
import com.example.imgurgalleryapp.domain.model.Image
import kotlinx.android.synthetic.main.item_image_cell.view.*

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var items: List<Image> = ArrayList()
    private var listener: ((Image) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_image_cell, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        if (items.isNotEmpty()) {
            val image = items[position]
            holder.bind(image)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setImageClickListener(listener: (Image) -> Unit) {
        this.listener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Image>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ImageViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(img: Image) {
            itemView.apply {
                tvTitle.text = img.title
                val date = resources.getString(
                    R.string.label_image_date,
                    img.date
                )
                tvDate.text = date
                tvNoOfImage.text = img.imagesCount.toString()
                Glide.with(context).load(
                    img.imageUrl ?: ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_launcher_background
                    )
                ).into(imageView)
                setOnClickListener { listener?.invoke(img) }
            }
        }
    }

}