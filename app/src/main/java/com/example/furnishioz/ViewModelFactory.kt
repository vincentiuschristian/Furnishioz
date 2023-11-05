package com.example.furnishioz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.furnishioz.data.FurnishiozRepository
import com.example.furnishioz.ui.screen.cart.CartViewModel
import com.example.furnishioz.ui.screen.detail.DetailViewModel
import com.example.furnishioz.ui.screen.home.HomeViewModel
import com.example.furnishioz.ui.screen.search.SearchViewModel

class ViewModelFactory(private val repository: FurnishiozRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(SearchViewModel::class.java)){
            return SearchViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)){
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class : " + modelClass.name)
    }

}