package com.vunh.demo_clean_app.presentation.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunh.demo_clean_app.domain.common.BaseResult
import com.vunh.demo_clean_app.domain.product.entity.ProductEntity
import com.vunh.demo_clean_app.domain.product.usecase.GetAllMyProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMainViewModel @Inject constructor(private val getAllMyProductUseCase: GetAllMyProductUseCase) : ViewModel() {
    private val state = MutableStateFlow<HomeMainFragmentState>(HomeMainFragmentState.Init)
    val mState: StateFlow<HomeMainFragmentState> get() = state

    private val products = MutableStateFlow<List<ProductEntity>>(mutableListOf())
    val mProducts: StateFlow<List<ProductEntity>> get() = products

    init {
        fetchAllMyProducts()
    }


    private fun setLoading(){
        state.value = HomeMainFragmentState.IsLoading(true)
    }

    private fun hideLoading(){
        state.value = HomeMainFragmentState.IsLoading(false)
    }

    private fun showToast(message: String){
        state.value = HomeMainFragmentState.ShowToast(message)
    }

    fun fetchAllMyProducts(){
        viewModelScope.launch {
            getAllMyProductUseCase.invoke()
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.message.toString())
                }
                .collect { result ->
                    hideLoading()
                    when(result){
                        is BaseResult.Success -> {
                            products.value = result.data
                        }
                        is BaseResult.Error -> {
                            showToast(result.rawResponse.message)
                        }
                    }
                }
        }
    }

}

sealed class HomeMainFragmentState {
    object Init : HomeMainFragmentState()
    data class IsLoading(val isLoading: Boolean) : HomeMainFragmentState()
    data class ShowToast(val message : String) : HomeMainFragmentState()
}
