package com.example.furnishioz.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.furnishioz.R
import com.example.furnishioz.ViewModelFactory
import com.example.furnishioz.di.Injection
import com.example.furnishioz.model.OrderItem
import com.example.furnishioz.model.dummyCategory
import com.example.furnishioz.ui.common.UiState
import com.example.furnishioz.ui.components.CategoryProductCard
import com.example.furnishioz.ui.components.HomeSection
import com.example.furnishioz.ui.components.ProductItemCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    Column (
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ){
        Banner()
        HomeSection(
            title = stringResource(R.string.product_category),
            content = { CategoryRow() }
        )
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> viewModel.getAllItem()
                is UiState.Success -> {
                    HomeSection(
                        title = stringResource(R.string.best_seller),
                        content = {
                            ProductItemContent(
                                orderItem = uiState.data,
                                navigateToDetail = navigateToDetail,
                                modifier = modifier
                            )
                        }
                    )
                    HomeSection(
                        title = stringResource(R.string.recommendation),
                        content = {
                            ProductRecContent(
                                orderItem = uiState.data.shuffled(),
                                navigateToDetail = navigateToDetail,
                                modifier = modifier
                            )
                        }
                    )
                }

                is UiState.Error -> {}
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
            ProductItemCard(
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
fun ProductRecContent(
    orderItem: List<OrderItem>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(orderItem) { data ->
            ProductItemCard(
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
            CategoryProductCard(category)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun Banner(modifier: Modifier = Modifier) {
    val imageBanner = listOf(
        R.drawable.banner2,
        R.drawable.banner3,
        R.drawable.banner4,
        R.drawable.banner5,
        R.drawable.banner
    )

    val pagerState = rememberPagerState(pageCount = {
        5
    })

    HorizontalPager(
        state = pagerState
    ) { index ->
        Image(
            painter = painterResource(imageBanner[index]),
            contentDescription = stringResource(R.string.banner),
            contentScale = ContentScale.FillBounds,
            modifier = modifier.height(180.dp)
        )

    }
}


