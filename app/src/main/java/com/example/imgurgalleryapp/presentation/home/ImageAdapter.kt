package com.example.imgurgalleryapp.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val image = items[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return if (items.isEmpty()) 5 else items.size
    }

    fun setImageClickListener(listener: (Image) -> Unit) {
        this.listener = listener
    }

    fun setItems(items: List<Image>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ImageViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(img: Image) {
            itemView.apply {
                tvImageDesc.text = img.title
                val date = resources.getString(
                    R.string.label_image_date,
                    img.date
                )
                tvImageDesc.tvDate.text = date
                Glide.with(context).load(img.imageUrl?:resources.getDrawable(R.mipmap.ic_launcher_round)).into(imageView)
                setOnClickListener { listener?.invoke(img) }
            }
        }
    }

}