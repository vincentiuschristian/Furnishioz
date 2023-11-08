package com.example.furnishioz.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.furnishioz.R
import com.example.furnishioz.ui.theme.FurnishiozTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        colors = SearchBarDefaults.colors(
            containerColor = colorResource(R.color.cardColor),
            inputFieldColors = TextFieldDefaults.colors(MaterialTheme.colorScheme.onBackground)
        ),
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.Gray
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search_placeholder),
                color = Color.Gray
            )
        },
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .width(1.dp)
            .heightIn(min = 48.dp)
    ) {
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchBarPreview() {
    FurnishiozTheme {
        SearchBar(
            query = "",
            onQueryChange = {}
        )
    }
}
