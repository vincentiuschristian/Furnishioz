package com.example.furnishioz.ui.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.furnishioz.data.FurnishiozRepository
import com.example.furnishioz.model.Product
import com.example.furnishioz.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: FurnishiozRepository) : ViewModel() {

    private val _uiStates: MutableStateFlow<UiState<List<Product>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Product>>>
        get() = _uiStates

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getAllProduct() {
        viewModelScope.launch {
            repository.getAllProducts()
                .catch {
                    _uiStates.value = UiState.Error(it.message.toString())
                }
                .collect { orderItem ->
                    _uiStates.value = UiState.Success(orderItem)
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
                .collect {
                    _uiStates.value = UiState.Success(it)
                }
        }

    }
}