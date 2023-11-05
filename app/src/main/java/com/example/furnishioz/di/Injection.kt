package com.example.furnishioz.di

import com.example.furnishioz.data.FurnishiozRepository

object Injection {
    fun provideRepository(): FurnishiozRepository {
        return FurnishiozRepository.getInstance()
    }
}