package com.example.furnishioz.ui.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.furnishioz.R
import com.example.furnishioz.ViewModelFactory
import com.example.furnishioz.di.Injection
import com.example.furnishioz.ui.common.UiState
import com.example.furnishioz.ui.components.CartItem
import com.example.furnishioz.ui.components.OrderButton


@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    onOrderButtonClicked: (String) -> Unit,
) {

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> viewModel.getAddedOrderProduct()
            is UiState.Success -> {
                CartContent(
                    uiState.data,
                    onProductCountChanged = { productId, count ->
                        viewModel.updateOrderProduct(productId, count)
                    },
                    onOrderButtonClicked = onOrderButtonClicked
                )
            }

            is UiState.Error -> Unit
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartContent(
    state: CartState,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    onOrderButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val shareMessage = stringResource(
        R.string.share_message,
        state.orderProduct.count(),
        state.totalRequiredPrice
    )
    val listState = rememberLazyListState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onSecondary
            ),
            title = {
                Text(
                    text = stringResource(R.string.cart),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            },
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = listState,
            modifier = Modifier
                .weight(weight = 1f)
        ) {
            if (state.orderProduct.isNotEmpty()) {
                items(state.orderProduct, key = { it.product.id }) { item ->
                    CartItem(
                        productId = item.product.id,
                        imageUrl = item.product.image,
                        name = item.product.name,
                        totalPrice = item.product.price * item.count,
                        count = item.count,
                        onProductCountChanged = onProductCountChanged,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Divider(
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            } else {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.emptyCart),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
        OrderButton(
            text = stringResource(R.string.total_order, state.totalRequiredPrice),
            enabled = state.orderProduct.isNotEmpty(),
            onClick = {
                onOrderButtonClicked(shareMessage)
            },
            modifier = Modifier.padding(16.dp)
        )
    }
}



