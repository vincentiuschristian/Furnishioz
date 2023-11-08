package com.example.furnishioz.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furnishioz.data.FurnishiozRepository
import com.example.furnishioz.model.OrderItem
import com.example.furnishioz.model.Product
import com.example.furnishioz.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: FurnishiozRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<OrderItem>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderItem>>
        get() = _uiState

    fun getProductById(productId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderProductById(productId))
        }
    }

    fun addToCart(product: Product, count: Int) {
        viewModelScope.launch {
            repository.updateOrderProduct(product.id, count)
        }
    }

}