package com.vunh.demo_clean_app.presentation.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vunh.demo_clean_app.R
import com.vunh.demo_clean_app.databinding.FragmentMainHomeBinding
import com.vunh.demo_clean_app.domain.product.entity.ProductEntity
import com.vunh.demo_clean_app.presentation.common.extension.gone
import com.vunh.demo_clean_app.presentation.common.extension.showToast
import com.vunh.demo_clean_app.presentation.common.extension.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class HomeMainFragment : Fragment(R.layout.fragment_main_home) {

    private var _binding: FragmentMainHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeMainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainHomeBinding.bind(view)
        setupRecyclerView()
        observe()
        goToCreateProductPage()
        setFragmentResultListener("success_create"){ requestKey, bundle ->
            if(bundle.getBoolean("success_create")){
                viewModel.fetchAllMyProducts()
            }
        }
    }

    private fun goToCreateProductPage(){
        binding.createFab.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_create)
        }
    }

    private fun setupRecyclerView(){
        val mAdapter = HomeMainProductAdapter(mutableListOf())
        mAdapter.setItemTapListener(object : HomeMainProductAdapter.OnItemTap{
            override fun onTap(product: ProductEntity) {
                val b = bundleOf("product_id" to product.id)
                findNavController().navigate(R.id.action_home_to_detail, b)
            }
        })

        binding.productsRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun fetchProducts(){
        viewModel.fetchAllMyProducts()
    }

    private fun observeState(){
        viewModel.mState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle,  Lifecycle.State.STARTED)
            .onEach { state ->
                handleState(state)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun observeProducts(){
        viewModel.mProducts
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { products ->
                handleProducts(products)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun observe(){
        observeState()
        observeProducts()
    }

    private fun handleProducts(products: List<ProductEntity>){
        binding.productsRecyclerView.adapter?.let {
            if(it is HomeMainProductAdapter){
                it.updateList(products)
            }
        }
    }

    private fun handleState(state: HomeMainFragmentState){
        when(state){
            is HomeMainFragmentState.IsLoading -> handleLoading(state.isLoading)
            is HomeMainFragmentState.ShowToast -> requireActivity().showToast(state.message)
            is HomeMainFragmentState.Init -> Unit
        }
    }

    private fun handleLoading(isLoading: Boolean){
        if(isLoading){
            binding.loadingProgressBar.visible()
        }else{
            binding.loadingProgressBar.gone()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}