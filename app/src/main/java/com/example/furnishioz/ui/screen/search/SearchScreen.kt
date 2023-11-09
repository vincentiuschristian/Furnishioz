package com.example.furnishioz.ui.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.furnishioz.R
import com.example.furnishioz.ViewModelFactory
import com.example.furnishioz.di.Injection
import com.example.furnishioz.model.OrderItem
import com.example.furnishioz.ui.common.UiState
import com.example.furnishioz.ui.components.ProductCard
import com.example.furnishioz.ui.components.SearchBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    val query by viewModel.query
    val uiStates by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiStates) {
        is UiState.Loading -> viewModel.getAllProduct()
        is UiState.Success -> {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                SearchBar(query = query, onQueryChange = viewModel::searchProduct)
                SearchContent(
                    orderItem = state.data,
                    navigateToDetail = navigateToDetail,
                    modifier = modifier
                )
            }
        }

        is UiState.Error -> {}
    }

//    viewModel.uiState.collectAsStateWithLifecycle().value.let { uiState ->
//        when (uiState) {
//            is UiState.Loading -> viewModel.getAllProduct()
//            is UiState.Success -> {
//                Column(
//                    modifier = modifier.fillMaxSize()
//                ) {
//                    SearchBar(query = query, onQueryChange = viewModel::searchProduct)
//                    SearchContent(
//                        orderItem = uiState.data,
//                        navigateToDetail = navigateToDetail,
//                        modifier = modifier
//                    )
//                }
//            }
//
//            is UiState.Error -> {}
//        }
//    }
}

@Composable
fun SearchContent(
    orderItem: List<OrderItem>,
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
        if (orderItem.isNotEmpty()) {
            items(orderItem) { data ->
                ProductCard(
                    key = data.product.id,
                    name = data.product.name,
                    imageUrl = data.product.image,
                    price = data.product.price,
                    modifier = modifier.clickable {
                        navigateToDetail(data.product.id)
                    }
                )
            }
        } else {
            item {
                Box(
                    modifier = modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.no_product_found),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }

    }

}
