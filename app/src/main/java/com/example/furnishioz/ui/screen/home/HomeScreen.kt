package com.example.furnishioz.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.furnishioz.R
import com.example.furnishioz.ViewModelFactory
import com.example.furnishioz.di.Injection
import com.example.furnishioz.model.OrderItem
import com.example.furnishioz.model.dummyCategory
import com.example.furnishioz.ui.common.UiState
import com.example.furnishioz.ui.components.CategoryCard
import com.example.furnishioz.ui.components.HomeSection
import com.example.furnishioz.ui.components.ProductCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (uiState is UiState.Loading) {
            viewModel.getAllItem()
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp) // Memberi jarak agar konten paling bawah tidak menempel
    ) {
        item {
            Banner()
        }

        item {
            HomeSection(
                title = stringResource(R.string.product_category),
                content = { CategoryRow() }
            )
        }

        when (val state = uiState) {
            is UiState.Loading -> {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
            is UiState.Success -> {
                item {
                    HomeSection(
                        title = stringResource(R.string.best_seller),
                        content = {
                            ProductItemContent(
                                orderItem = state.data,
                                navigateToDetail = navigateToDetail
                            )
                        }
                    )
                }
                item {
                    HomeSection(
                        title = stringResource(R.string.recommendation),
                        content = {
                            ProductRecContent(
                                orderItem = state.data,
                                navigateToDetail = navigateToDetail
                            )
                        }
                    )
                }
            }
            is UiState.Error -> {
                item {
                    Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                        Text(text = "Terjadi kesalahan saat memuat data.")
                    }
                }
            }
        }
    }

}

@Composable
fun ProductItemContent(
    orderItem: List<OrderItem>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .testTag("ProductList")
    ) {
        items(orderItem) { data ->
            ProductCard(
                name = data.product.name,
                imageUrl = data.product.image,
                price = data.product.price,
                modifier = Modifier.clickable {
                    navigateToDetail(data.product.id)
                }
            )
        }
    }
}

@Composable
fun ProductRecContent(
    orderItem: List<OrderItem>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    val shuffledItems = remember(orderItem) { orderItem.shuffled() }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(shuffledItems, key = { it.product.id }) { data ->
            ProductCard(
                name = data.product.name,
                imageUrl = data.product.image,
                price = data.product.price,
                modifier = modifier.clickable {
                    navigateToDetail(data.product.id)
                }
            )
        }
    }
}

@Composable
fun CategoryRow() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(dummyCategory, key = { it.textCategory }) { category ->
            CategoryCard(category)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun Banner(modifier: Modifier = Modifier) {
    val imageBanner = remember {
        listOf(
            R.drawable.banner2,
            R.drawable.banner3,
            R.drawable.banner4,
            R.drawable.banner5,
            R.drawable.banner
        )
    }

    val pagerState = rememberPagerState(pageCount = { imageBanner.size })

    Card(
        modifier = modifier.padding(16.dp).fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        HorizontalPager(
            state = pagerState,
        ) { index ->
            Image(
                painter = painterResource(imageBanner[index]),
                contentDescription = stringResource(R.string.banner),
                contentScale = ContentScale.Crop,
                modifier = modifier.height(180.dp)
            )

        }
    }
}


