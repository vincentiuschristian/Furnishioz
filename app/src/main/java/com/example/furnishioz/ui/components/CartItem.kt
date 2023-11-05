package com.example.furnishioz.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.furnishioz.R
import com.example.furnishioz.ui.theme.FurnishiozTheme

@Composable
fun CartItem(
    productId: Long,
    imageUrl: String,
    name: String,
    price: Int,
    count: Int,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Column( modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .weight(1.0f),
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = stringResource(R.string.price, price),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        ProductCounter(
            orderId = productId,
            orderCount = count,
            onProductIncreased = {onProductCountChanged(productId, count + 1)},
            onProductDecreased = {onProductCountChanged(productId, count - 1)},
            modifier = modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun CartItemPreview() {
    FurnishiozTheme {
        CartItem(
            productId = 1,
            imageUrl = "",
            name = "Wardrobe",
            price = 120,
            count = 2,
            onProductCountChanged = { _, _ -> }
        )

    }
}