package com.example.furnishioz.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
@Composable
fun SectionText(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(text = title,
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.SansSerif
        ),
        modifier = modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
    )
}
