package com.example.furnishioz.data

import com.example.furnishioz.model.OrderItem
import com.example.furnishioz.model.Product
import com.example.furnishioz.model.ProductData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FurnishiozRepository {

    private val orderItems = mutableListOf<OrderItem>()

    init {
        if (orderItems.isEmpty()) {
            ProductData.dummyProducts.forEach {
                orderItems.add(OrderItem(it, 0))
            }
        }
    }

    fun getAllProduct(): Flow<List<OrderItem>> {
        return flowOf(orderItems)
    }

    fun searchProduct(query: String): Flow<List<Product>> {
        val search = ProductData.dummyProducts.filter {
            it.name.contains(query, ignoreCase = true)
        }
        return flowOf(search)
    }

    fun getOrderProductById(itemId: Long): OrderItem {
        return orderItems.first {
            it.product.id == itemId
        }
    }

    fun updateOrderProduct(productId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderItems.indexOfFirst { it.product.id == productId }
        val result = if (index >= 0 && index <= orderItems.size) {
            val orderItem = orderItems[index]
            orderItems[index] = orderItem.copy(product = orderItem.product, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderProduct(): Flow<List<OrderItem>> {
        return getAllProduct()
            .map { orderProducts ->
                orderProducts.filter { orderProduct ->
                    orderProduct.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: FurnishiozRepository? = null

        fun getInstance(): FurnishiozRepository =
            instance ?: synchronized(this) {
                FurnishiozRepository().apply {
                    instance = this
                }
            }
    }
}