package com.ang.anime.provider

import android.content.Context
import com.ang.anime.data.remote.AnimeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {
    @Singleton
    @Provides
    fun provideRetrofit(): AnimeApiService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/")
            .addConverterFactory(GsonConverterFactory.create()) // JSON to Object Mapping
            .build()
        return retrofit.create(AnimeApiService::class.java)
    }


}
