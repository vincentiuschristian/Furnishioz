package com.example.furnishioz.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furnishioz.data.FurnishiozRepository
import com.example.furnishioz.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val repository: FurnishiozRepository) : ViewModel(){
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddOrderProduct(){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderProduct()
                .collect{orderProduct ->
                    val total = orderProduct.sumOf { it.product.price * it.count }
                    _uiState.value = UiState.Success(CartState(orderProduct, total))
                }
        }
    }

    fun updateOrderProduct(productId: Long, count: Int){
        viewModelScope.launch {
            repository.updateOrderProduct(productId, count)
                .collect{ isUpdated ->
                    if (isUpdated){
                        getAddOrderProduct()
                    }
                }
        }
    }

}