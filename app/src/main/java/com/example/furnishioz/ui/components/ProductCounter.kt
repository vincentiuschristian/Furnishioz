package com.example.furnishioz.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.furnishioz.ui.theme.FurnishiozTheme

@Composable
fun ProductCounter(
    orderId: Long,
    orderCount: Int,
    onProductIncreased: (Long) -> Unit,
    onProductDecreased: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .size(width = 120.dp, height = 50.dp)
            .padding(8.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(size = 8.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
            color = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(24.dp)
        ) {
            Text(
                text = "—",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onProductDecreased(orderId)
                    }
            )
        }
        Text(
            text = orderCount.toString(),
            modifier = Modifier
                .testTag("count")
                .weight(1f),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Surface(
            shape = RoundedCornerShape(size = 8.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
            color = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(24.dp)
        ) {
            Text(
                text = "＋",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onProductIncreased(orderId)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCounterPreview() {
    FurnishiozTheme {
        ProductCounter(
            orderId = 1,
            orderCount = 2,
            onProductIncreased = {},
            onProductDecreased = {},
        )
    }
}