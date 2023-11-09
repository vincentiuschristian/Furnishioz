package com.example.furnishioz.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.furnishioz.R
import com.example.furnishioz.model.Category
import com.example.furnishioz.ui.theme.FurnishiozTheme

@Composable
fun CategoryCard(
    category: Category,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            color = colorResource(R.color.cardColor),
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        ){
            Image(
                painter = painterResource(category.imageCategory),
                contentDescription = stringResource(R.string.category),
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(4.dp)
            )
        }
        Text(
            text = category.textCategory,
            fontSize = 10.sp,
            modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryProductPreview() {
    FurnishiozTheme {
        CategoryCard(
            category = Category(
                R.drawable.wardrobe,
                "Wardrobe"
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}