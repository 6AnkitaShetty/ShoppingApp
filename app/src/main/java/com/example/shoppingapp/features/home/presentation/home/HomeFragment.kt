package com.example.shoppingapp.features.home.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.FragmentHomeBinding
import com.example.shoppingapp.features.home.data.remote.model.Product
import com.example.shoppingapp.features.home.presentation.MainViewModel
import com.example.shoppingapp.features.home.presentation.adapters.ProductsAdapter
import com.example.shoppingapp.features.login.presentation.LoginActivity
import com.example.shoppingapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment(), ProductsAdapter.OnClickListener {
    private val homeViewModel: HomeViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    private val adapter = ProductsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = homeViewModel
        binding.fragment = this
        mainViewModel.fetchCartCount()
        mainViewModel.fetchWishListCount()
        setHasOptionsMenu(true)
        setAdapter()
        setupObservers()
        binding.searchProductEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                homeViewModel.fetchProducts()
                clearEditText()
            }
            false
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            homeViewModel.logout()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenStarted {
            homeViewModel.userName?.collectLatest {
                binding.email = it
            }
        }
        homeViewModel.categoryProducts.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Success -> {
                    binding.productsRecycler.visibility = View.VISIBLE
                    binding.itemErrorMessage.tvErrorMessage.visibility = View.GONE
                    result.data?.let { adapter.submitList(it) }
                }
                is Resource.Error -> {
                    binding.productsRecycler.visibility = View.GONE
                    binding.itemErrorMessage.tvErrorMessage.visibility = View.VISIBLE
                }
            }
        }

        homeViewModel.fetchWishListCount.observe(viewLifecycleOwner) { boolean ->
            if (boolean) {
                mainViewModel.fetchWishListCount()
            }
        }

        homeViewModel.fetchCartCount.observe(viewLifecycleOwner) { boolean ->
            if (boolean) {
                mainViewModel.fetchCartCount()
            }
        }

        homeViewModel.triggerLogOutClick.observe(viewLifecycleOwner) {
            if (it) {
                val dialogBuilder = AlertDialog.Builder(activity!!)
                dialogBuilder.setMessage(it.toString())
                    .setPositiveButton(R.string.yes) { _, _ ->
                        val i = Intent(
                            activity,
                            LoginActivity::class.java
                        )
                        startActivity(i)
                        activity?.finish()
                    }
                    .setNegativeButton(R.string.no){ dialog, _ ->
                        dialog.dismiss()
                    }
                val alert = dialogBuilder.create()
                alert.setTitle(R.string.logout)
                alert.setMessage(getString(R.string.logout_confirmation_message))
                alert.show()
            }
        }
    }

    private fun setAdapter() {
        binding.adapter = adapter
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.productsRecycler.addItemDecoration(divider)
    }

    override fun onWishListClicked(product: Product) {
        homeViewModel.addItemToWishList(product)
    }

    override fun onCartClicked(product: Product) {
        homeViewModel.addItemToCart(product)
    }

    override fun onResume() {
        super.onResume()
        clearEditText()
    }

    private fun clearEditText() {
        binding.searchProductEditText.setText("")
    }

    fun fetchProducts() {
        hideKeyboard()
        if (binding.searchProductEditText.text.isNotEmpty()) {
            homeViewModel.fetchProducts()
            clearEditText()
        }
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }
}