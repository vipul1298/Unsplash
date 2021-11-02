package com.example.unsplash.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.unsplash.R
import com.example.unsplash.databinding.ListItemBinding
import com.example.unsplash.db.models.Photo

class PhotosAdapter :ListAdapter<Photo,PhotosAdapter.PhotosViewHolder>(PhotoComparator()){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotosAdapter.PhotosViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false
        )
        return PhotosViewHolder(binding)
    }

    inner class PhotosViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){
         fun bind(photo: Photo){
             binding.apply {
                 Glide.with(itemView)
                     .load(photo.src.medium)
                     .placeholder(R.drawable.ic_baseline_image_24)
                     .into(image)

             }
         }
    }

    override fun onBindViewHolder(holder: PhotosAdapter.PhotosViewHolder, position: Int) {
        val photo = getItem(position)
        if(photo!=null)
            holder.bind(photo)


    }

    class PhotoComparator : DiffUtil.ItemCallback<Photo>(){
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }



}