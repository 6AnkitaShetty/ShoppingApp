package com.example.shoppingapp.features.home.presentation.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentWishListBinding
import com.example.shoppingapp.features.home.data.local.model.WishListItem
import com.example.shoppingapp.features.home.presentation.MainViewModel
import com.example.shoppingapp.features.home.presentation.adapters.WishListAdapter
import com.example.shoppingapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishListFragment : Fragment(), WishListAdapter.OnWishListItemClickListener {

    private val viewModel: WishListViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentWishListBinding
    private val adapter = WishListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWishListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setAdapter()
        setObservers()
    }

    private fun setAdapter() {
        binding.adapter = adapter
    }

    private fun setObservers() {
        viewModel.wishListItems.observe(viewLifecycleOwner) { result ->
            mainViewModel.fetchWishListCount()
            when (result) {
                is Resource.Success -> {
                    binding.wishListRecycler.visibility = View.VISIBLE
                    binding.itemErrorMessage.tvErrorMessage.visibility = View.GONE
                    result.data?.let { adapter.submitList(it) }
                }
                is Resource.Error -> {
                    binding.wishListRecycler.visibility = View.GONE
                    binding.itemErrorMessage.tvErrorMessage.visibility = View.VISIBLE
                }
            }

        }

        viewModel.fetchCartCount.observe(viewLifecycleOwner) { isFetch ->
            if (isFetch) {
                mainViewModel.fetchCartCount()
            }
        }
    }

    override fun onDeleteItem(wishListItem: WishListItem) {
        viewModel.deleteItem(wishListItem)
    }

    override fun onAddToCart(wishListItem: WishListItem) {
        viewModel.addItemToCart(wishListItem)
    }
}