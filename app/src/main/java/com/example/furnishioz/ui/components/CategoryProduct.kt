package com.example.furnishioz.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.furnishioz.R
import com.example.furnishioz.model.Category
import com.example.furnishioz.ui.theme.FurnishiozTheme

@Composable
fun CategoryProduct(
    category: Category,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = category.imageCategory,
            contentDescription = stringResource(R.string.category),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Text(
            text = category.textCategory,
            fontSize = 10.sp,
            maxLines = 1,
            modifier = modifier
                .paddingFromBaseline(top = 16.dp, bottom = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryProductPreview() {
    FurnishiozTheme {
        CategoryProduct(
            category = Category(
                R.drawable.chair,
                "Chair"
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}