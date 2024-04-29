package com.danamon.test.feature.dashboard.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danamon.common.utils.DiffUtils
import com.danamon.domain.model.Photo
import com.danamon.test.R
import com.danamon.test.databinding.ItemPhotosBinding
import kotlin.properties.Delegates

class PhotosAdapter: RecyclerView.Adapter<PhotosAdapter.ViewHolder>(), DiffUtils {

    var data: List<Photo> by Delegates.observable(emptyList()) { _, old, new ->
        calculateDiff(old, new) { oldItem, newItem -> oldItem == newItem }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPhotosBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(
        private val binding: ItemPhotosBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Photo) = with(binding) {
            Glide.with(root).load(item.url).into(ivThumbnail)
            tvPhotos.text = root.resources.getString(R.string.text_photos,
                item.albumId,
                item.id,
                item.title,
                item.url
            )
        }
    }
}
