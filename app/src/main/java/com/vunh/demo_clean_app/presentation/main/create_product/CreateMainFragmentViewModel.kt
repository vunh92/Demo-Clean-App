package com.vunh.demo_clean_app.presentation.main.create_product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunh.demo_clean_app.data.product.remote.dto.ProductCreateRequest
import com.vunh.demo_clean_app.domain.common.BaseResult
import com.vunh.demo_clean_app.domain.product.usecase.CreateProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateMainFragmentViewModel @Inject constructor(
    private val createProductUseCase: CreateProductUseCase
)  : ViewModel() {
    private val state = MutableStateFlow<CreateMainFragmentState>(CreateMainFragmentState.Init)
    val mState: StateFlow<CreateMainFragmentState> get() = state

    private fun setLoading(){
        state.value = CreateMainFragmentState.IsLoading(true)
    }

    private fun hideLoading(){
        state.value = CreateMainFragmentState.IsLoading(false)
    }

    private fun showToast(message: String){
        state.value = CreateMainFragmentState.ShowToast(message)
    }

    private fun successCreate(){
        state.value = CreateMainFragmentState.SuccessCreate
    }

    fun createProduct(productCreateRequest: ProductCreateRequest){
        viewModelScope.launch {
            createProductUseCase.invoke(productCreateRequest)
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.stackTraceToString())
                }
                .collect { result ->
                    hideLoading()
                    when(result){
                        is BaseResult.Success -> successCreate()
                        is BaseResult.Error -> showToast(result.rawResponse.message)
                    }
                }
        }
    }
}

sealed class CreateMainFragmentState {
    object Init: CreateMainFragmentState()
    object SuccessCreate : CreateMainFragmentState()
    data class IsLoading(val isLoading: Boolean) : CreateMainFragmentState()
    data class ShowToast(val message: String) : CreateMainFragmentState()
}