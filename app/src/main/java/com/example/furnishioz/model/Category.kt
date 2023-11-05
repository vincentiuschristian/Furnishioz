package com.example.furnishioz.model

import androidx.annotation.DrawableRes
import com.example.furnishioz.R

data class Category(
    @DrawableRes val imageCategory: Int,
    val textCategory: String
)

val dummyCategory = listOf(
    R.drawable.chair to "Chair",
    R.drawable.lamp to "Lamp",
    R.drawable.painting to "Decoration",
    R.drawable.work_desk to "Workbench",
    R.drawable.plant to "Plant",
    R.drawable.sofa to "Sofa",
    R.drawable.storage to "Storage",
    R.drawable.wardrobe to "Wardrobe"

).map {Category(it.first, it.second)}
