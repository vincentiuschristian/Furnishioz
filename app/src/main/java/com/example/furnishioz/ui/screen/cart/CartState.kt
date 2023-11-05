package com.example.furnishioz.ui.screen.cart

import com.example.furnishioz.model.OrderItem

data class CartState(
    val orderProduct: List<OrderItem>,
    val totalRequiredPrice: Int
)