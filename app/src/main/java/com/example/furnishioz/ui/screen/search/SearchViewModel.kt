package com.example.furnishioz.ui.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furnishioz.data.FurnishiozRepository
import com.example.furnishioz.model.OrderItem
import com.example.furnishioz.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: FurnishiozRepository) : ViewModel() {

    private val _uiStates: MutableStateFlow<UiState<List<OrderItem>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderItem>>>
        get() = _uiStates

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllProduct() {
        viewModelScope.launch {
            repository.getAllProduct()
                .catch {
                    _uiStates.value = UiState.Error(it.message.toString())
                }
                .collect { product ->
                    _uiStates.value = UiState.Success(product)
                }
        }
    }

    fun searchProduct(newQuery: String) {
        viewModelScope.launch {
            _query.value = newQuery
            repository.searchProduct(_query.value)
                .catch {
                    _uiStates.value = UiState.Error(it.message.toString())
                }
                .collect { product ->
                    _uiStates.value = UiState.Success(product.map { OrderItem(it, 0) })
                }

        }

    }

}