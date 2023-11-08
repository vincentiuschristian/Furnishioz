package com.example.furnishioz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.furnishioz.data.FurnishiozRepository
import com.example.furnishioz.ui.screen.cart.CartViewModel
import com.example.furnishioz.ui.screen.detail.DetailViewModel
import com.example.furnishioz.ui.screen.home.HomeViewModel
import com.example.furnishioz.ui.screen.search.SearchViewModel

class ViewModelFactory(private val repository: FurnishiozRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {

        HomeViewModel::class.java -> HomeViewModel(repository)
        SearchViewModel::class.java -> SearchViewModel(repository)
        DetailViewModel::class.java -> DetailViewModel(repository)
        CartViewModel::class.java -> CartViewModel(repository)

        else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    } as T

}