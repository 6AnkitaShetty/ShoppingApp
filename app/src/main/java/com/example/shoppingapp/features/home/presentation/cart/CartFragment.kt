package com.example.shoppingapp.features.home.presentation.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.shoppingapp.databinding.FragmentCartBinding
import com.example.shoppingapp.features.home.data.local.model.CartItem
import com.example.shoppingapp.features.home.presentation.MainViewModel
import com.example.shoppingapp.features.home.presentation.adapters.CartAdapter
import com.example.shoppingapp.features.order.OrderActivity
import com.example.shoppingapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(), CartAdapter.OnCartItemClickListener {

    private val viewModel: CartViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentCartBinding
    private val adapter = CartAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setAdapter()
        setObservers()
    }

    private fun setObservers() {
        viewModel.cartItems.observe(viewLifecycleOwner) { result ->
            mainViewModel.fetchCartCount()
            when (result) {
                is Resource.Success -> {
                    binding.cartRecycler.visibility = View.VISIBLE
                    binding.itemErrorMessage.tvErrorMessage.visibility = View.GONE
                    result.data?.let { adapter.submitList(it) }
                }
                is Resource.Error -> {
                    binding.cartRecycler.visibility = View.GONE
                    binding.itemErrorMessage.tvErrorMessage.visibility = View.VISIBLE
                }
            }

        }

        viewModel.triggerCheckOutClick.observe(viewLifecycleOwner) {
            if (it) {
                val i = Intent(
                    activity,
                    OrderActivity::class.java
                )
                startActivity(i)
            }

        }
    }

    private fun setAdapter() {
        binding.adapter = adapter
    }

    override fun onDeleteItem(cartItem: CartItem) {
        viewModel.deleteItem(cartItem)
    }
}