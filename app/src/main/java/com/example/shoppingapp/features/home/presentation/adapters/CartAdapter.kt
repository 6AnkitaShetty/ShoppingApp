package com.example.shoppingapp.features.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.databinding.ItemCartBinding
import com.example.shoppingapp.features.home.data.local.model.CartItem

class CartAdapter(
    private val listener: OnCartItemClickListener
) : ListAdapter<CartItem, CartAdapter.CartItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val binding =
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class CartItemViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                deleteImage.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val cartItem = getItem(position)
                        listener.onDeleteItem(cartItem)
                    }
                }
            }
        }

        fun bind(cartItem: CartItem) {
            binding.apply {
                model = cartItem
                executePendingBindings()
            }
        }
    }


    interface OnCartItemClickListener {
        fun onDeleteItem(cartItem: CartItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean =
            oldItem.productId == newItem.productId

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean =
            oldItem == newItem
    }

}
