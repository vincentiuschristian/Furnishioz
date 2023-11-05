package com.example.furnishioz.ui.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.furnishioz.ViewModelFactory
import com.example.furnishioz.di.Injection
import com.example.furnishioz.model.Product
import com.example.furnishioz.ui.common.UiState
import com.example.furnishioz.ui.components.ProductItem

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> viewModel.getAllProduct()
            is UiState.Success -> {
                ProductItemSearch(
                    orderItem = uiState.data,
                    navigateToDetail = navigateToDetail,
                    modifier = modifier
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun ProductItemSearch(
    orderItem: List<Product>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(140.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .testTag("Product List")
    ) {
        items(orderItem) { data ->
            ProductItem(
                name = data.name,
                imageUrl = data.image,
                price = data.price,
                modifier = modifier.clickable {
                    navigateToDetail(data.id)
                }
            )
        }
    }
}