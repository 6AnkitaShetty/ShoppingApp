package com.example.shoppingapp.features.home.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.databinding.ItemProductsBinding
import com.example.shoppingapp.features.home.data.remote.model.Product
import com.example.shoppingapp.util.loadWishListImage

class ProductsAdapter(private val listener: OnClickListener) :
    ListAdapter<Product, ProductsAdapter.ProductsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding =
            ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class ProductsViewHolder(val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                wishlistImage.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val product = getItem(position)
                        product.isWishListed = !product.isWishListed
                        loadWishListImage(wishlistImage, product.isWishListed)
                        listener.onWishListClicked(product)
                    }
                }
                addToCart.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val product = getItem(position)
                        listener.onCartClicked(product)
                    }
                }
            }

        }

        fun bind(product: Product) {
            binding.apply {
                model = product
                executePendingBindings()
            }
        }
    }


    interface OnClickListener {
        fun onWishListClicked(product: Product)
        fun onCartClicked(product: Product)
    }

    class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem.productId == newItem.productId

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem == newItem
    }
}