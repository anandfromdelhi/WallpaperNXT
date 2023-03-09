package com.example.wallpapernxt.di

import com.example.wallpapernxt.network.PhotoViewApi
import com.example.wallpapernxt.network.SearchCategoriesApi
import com.example.wallpapernxt.network.SearchPhotosApi
import com.example.wallpapernxt.utils.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideSearchPhotosApi():SearchPhotosApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchPhotosApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchCategoriesApi():SearchCategoriesApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchCategoriesApi::class.java)
    }

    @Singleton
    @Provides
    fun viewPhoto():PhotoViewApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhotoViewApi::class.java)
    }
}