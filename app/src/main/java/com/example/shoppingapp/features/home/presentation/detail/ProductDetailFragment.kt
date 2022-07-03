package com.example.shoppingapp.features.home.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.shoppingapp.databinding.FragmentProductDetailBinding
import com.example.shoppingapp.features.home.presentation.MainViewModel
import com.example.shoppingapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private val viewModel: ProductDetailViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private val args by navArgs<ProductDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        setObservers()

        val product = args.currentItem
        viewModel.fetchProductDetails(product)
    }

    private fun setObservers() {
        viewModel.productDetail.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Success -> {
                    binding.main.visibility = View.VISIBLE
                    binding.errorView.visibility = View.GONE
                    binding.model = result.data
                }
                is Resource.Error -> {
                    binding.main.visibility = View.GONE
                    binding.errorView.visibility = View.VISIBLE
                }
            }
        }

        viewModel.fetchCartCount.observe(viewLifecycleOwner) { isFetch ->
            if (isFetch) {
                mainViewModel.fetchCartCount()
            }
        }

        viewModel.fetchWishListCount.observe(viewLifecycleOwner) { isFetch ->
            if (isFetch) {
                mainViewModel.fetchWishListCount()
            }
        }
    }

}