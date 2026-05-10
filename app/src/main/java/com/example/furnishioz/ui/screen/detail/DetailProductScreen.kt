package com.example.furnishioz.ui.screen.detail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.furnishioz.R
import com.example.furnishioz.ViewModelFactory
import com.example.furnishioz.di.Injection
import com.example.furnishioz.ui.common.UiState
import com.example.furnishioz.ui.components.OrderButton
import com.example.furnishioz.ui.components.ProductCounter
import com.example.furnishioz.ui.theme.FurnishiozTheme

@Composable
fun DetailProductScreen(
    productId: Long,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(initialValue = UiState.Loading)

    LaunchedEffect(productId) {
        if (uiState is UiState.Loading) {
            viewModel.getProductById(productId)
        }
    }

    when (val state = uiState) {
        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is UiState.Success -> {
            val data = state.data
            DetailContent(
                imageUrl = data.product.image,
                title = data.product.name,
                basePrice = data.product.price,
                description = data.product.description,
                brand = data.product.brand,
                count = data.count,
                onBackClick = navigateBack,
                onAddToCart = { count ->
                    viewModel.addToCart(data.product, count)
                    navigateToCart()
                },
                modifier = modifier
            )
        }
        is UiState.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Gagal memuat detail produk")
            }
        }
    }
}

@Composable
fun DetailContent(
    imageUrl: String,
    title: String,
    basePrice: Int,
    description: String,
    brand: String,
    count: Int,
    onBackClick: () -> Unit,
    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    var orderCount by rememberSaveable { mutableIntStateOf(count) }
    val totalPrice = basePrice * orderCount

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = stringResource(R.string.detail_image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(360.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    contentDescription = stringResource(R.string.back),
                    tint = colorResource(R.color.back_button_color),
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(12.dp)
                        .size(36.dp)
                        .background(
                            shape = MaterialTheme.shapes.medium,
                            color = colorResource(R.color.cardColor)
                        )
                        .clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = colorResource(R.color.textColor)
                )
                Text(
                    text = stringResource(R.string.price, basePrice),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = modifier.padding(top = 8.dp)
                )
                Text(
                    text = stringResource(R.string.brand, brand),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = modifier.padding(top = 8.dp)
                )
                HorizontalDivider(
                    modifier = modifier.padding(top = 16.dp),
                    thickness = DividerDefaults.Thickness, color = DividerDefaults.color
                )
                Text(
                    text = stringResource(R.string.description),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = colorResource(R.color.textColor),
                    modifier = modifier
                        .padding(top = 24.dp)
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    color = colorResource(R.color.textDescription),
                    modifier = modifier.padding(top = 8.dp)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.LightGray)
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductCounter(
                orderId = 1,
                orderCount = orderCount,
                onProductIncreased = { orderCount++ },
                onProductDecreased = { if (orderCount > 0) orderCount-- },
                modifier = Modifier
            )
            Spacer(modifier = Modifier.weight(1f))
            OrderButton(
                text = stringResource(R.string.add_to_cart, totalPrice),
                enabled = orderCount > 0,
                onClick = {
                    onAddToCart(orderCount)
                }
            )
        }
    }
}

@Preview(showBackground = false, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailProductScreenPreview() {
    FurnishiozTheme {
        DetailContent(
            imageUrl = "",
            title = "Karian Bed",
            basePrice = 120,
            count = 0,
            description = stringResource(R.string.lorem_ipsum),
            brand = "Kana",
            onBackClick = {  },
            onAddToCart = {}
        )
    }
}