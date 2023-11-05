package com.example.furnishioz.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furnishioz.data.FurnishiozRepository
import com.example.furnishioz.model.OrderItem
import com.example.furnishioz.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: FurnishiozRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<OrderItem>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderItem>>>
        get() = _uiState

    fun getAllItem(){
        viewModelScope.launch {
            repository.getAllProduct()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {orderItem ->
                    _uiState.value = UiState.Success(orderItem)
                }
        }
    }
}