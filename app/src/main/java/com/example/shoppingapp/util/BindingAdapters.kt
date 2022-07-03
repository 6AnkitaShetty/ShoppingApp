package com.example.shoppingapp.util

import android.graphics.Color
import android.os.Build
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BulletSpan
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.shoppingapp.R
import com.example.shoppingapp.features.home.data.local.model.CartItem
import com.example.shoppingapp.features.home.data.remote.model.Product
import com.example.shoppingapp.features.home.presentation.home.HomeFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView

@BindingAdapter("productImage")
fun loadImage(view: ImageView, product: Product?) {
    val imageUrl = product?.productImage
    Glide.with(view.context)
        .load(imageUrl)
        .into(view)
}

@BindingAdapter("loadImageWithUrl")
fun loadImageWithUrl(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}

@BindingAdapter("wishListImage")
fun loadWishListImage(view: ImageView, isWishListed: Boolean) {
    val id = if (isWishListed) R.drawable.ic_wishlist_blue else R.drawable.ic_wishlist_blue_outline
    view.setImageResource(id)
}

@BindingAdapter("bind:availabilityText")
fun setAvailabilityText(textView: TextView?, product: Product?) {
    if (textView == null) {
        return
    }
    product?.let {
        textView.text =
            textView.context.getString(R.string.availability_status, it.availabilityState)
    }
}

@BindingAdapter("bind:reviewText")
fun setReviewText(textView: TextView?, product: Product?) {
    if (textView == null) {
        return
    }
    val reviewAverage = product?.reviewInformation?.reviewSummary?.reviewAverage
    val reviewText = product?.reviewInformation?.reviewSummary?.reviewCount
        ?: 0
    textView.text = textView.context.getString(R.string.reviews_average, reviewAverage, reviewText)
}

@BindingAdapter("bind:priceText")
fun setPriceText(textView: TextView?, product: Product?) {
    if (textView == null) {
        return
    }

    val priceText = product?.salesPriceIncVat
    textView.text = textView.context.getString(R.string.product_price, priceText)
}

@BindingAdapter("bind:totalPrice")
fun setTotalPrice(textView: TextView?, product: CartItem) {
    if (textView == null) {
        return
    }
    product.price?.let {
        val priceText = (product.count?.times(it))
        textView.text = textView.context.getString(R.string.product_price, priceText)
    }
}

@BindingAdapter("bind:nextDayDelivery")
fun setNextDayDelivery(textView: TextView?, product: Product?) {
    if (textView == null) {
        return
    }
    product?.let {
        if (it.nextDayDelivery == true) {
            textView.visibility = View.VISIBLE
            textView.text = textView.context.getString(R.string.next_day_delivery)
        }
    }
}

@BindingAdapter("bind:reviewCount")
fun setReviewCount(textView: TextView?, product: Product?) {
    if (textView == null) {
        return
    }

    val reviewCount = product?.reviewInformation?.reviewSummary?.reviewCount
    textView.text = textView.context.getString(R.string.review_count, reviewCount)
}

@BindingAdapter("bind:rating")
fun setRating(ratingBar: RatingBar?, product: Product?) {
    val rating = product?.reviewInformation?.reviewSummary?.reviewAverage?.div(2.5f)
    rating?.let {
        ratingBar?.rating = it.toFloat()
    }
}

@BindingAdapter("bind:productDescription")
fun setProductDescription(textView: TextView?, product: Product?) {
    if (textView == null) {
        return
    }
    val builder = SpannableStringBuilder()
    product?.USPs?.forEach {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            builder.appendLine(
                Html.fromHtml("${Constants.BULLET_SYMBOL}${it}")
            )
        } else {
            builder.append(
                it + "\n",
                BulletSpan(20, ContextCompat.getColor(textView.context, R.color.black), 10),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

    }
    textView.text = builder
}

@BindingAdapter("cartCount")
fun BottomNavigationView.updateCartCount(count: Int) {
    getOrCreateBadge(R.id.cartFragment).apply {
        backgroundColor = Color.RED
        badgeTextColor = Color.WHITE
        isVisible = count > 0
        if (isVisible) number = count
    }
}

@BindingAdapter("wishListCount")
fun BottomNavigationView.updateWishListCount(count: Int) {
    getOrCreateBadge(R.id.wishListFragment).apply {
        backgroundColor = Color.RED
        badgeTextColor = Color.WHITE
        isVisible = count > 0
        if (isVisible) number = count
    }
}

@BindingAdapter("android:sendDataToProductDetailFragment")
fun sendDataToProductDetailFragment(view: ConstraintLayout, currentEntity: Product?) {
    view.setOnClickListener {
        currentEntity?.let {
            val action =
                HomeFragmentDirections.actionNavigationHomeToProductDetailFragment(it)
            view.findNavController().navigate(action)
        }
    }
}

