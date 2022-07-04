package com.example.shoppingapp.features.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.databinding.ItemWishlistBinding
import com.example.shoppingapp.features.home.data.local.model.WishListItem

class WishListAdapter(
    private val listener: OnWishListItemClickListener
) : ListAdapter<WishListItem, WishListAdapter.WishListViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListViewHolder {
        val binding =
            ItemWishlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WishListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class WishListViewHolder(val binding: ItemWishlistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                deleteImage.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val wishListItem = getItem(position)
                        listener.onDeleteItem(wishListItem)
                    }
                }

                addToCartImage.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val wishListItem = getItem(position)
                        listener.onAddToCart(wishListItem)
                    }
                }
            }
        }

        fun bind(wishListItem: WishListItem) {
            binding.apply {
                model = wishListItem
                executePendingBindings()
            }
        }
    }


    interface OnWishListItemClickListener {
        fun onDeleteItem(wishListItem: WishListItem)
        fun onAddToCart(wishListItem: WishListItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<WishListItem>() {
        override fun areItemsTheSame(oldItem: WishListItem, newItem: WishListItem): Boolean =
            oldItem.productId == newItem.productId

        override fun areContentsTheSame(oldItem: WishListItem, newItem: WishListItem): Boolean =
            oldItem == newItem
    }

}