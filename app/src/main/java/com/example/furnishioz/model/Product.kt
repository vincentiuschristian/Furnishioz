package com.example.furnishioz.model

data class Product(
    val id: Long,
    val name: String,
    val image: String,
    val price: Int,
    val description: String,
    val brand: String
)