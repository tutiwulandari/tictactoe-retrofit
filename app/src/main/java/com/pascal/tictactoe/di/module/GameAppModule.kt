package com.pascal.tictactoe.di.module

import com.pascal.tictactoe.utils.Constans
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class GameAppModule {

    @Singleton
    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()


    @Singleton
    @Provides
    fun provideRetrofitInstance(gson: GsonConverterFactory) :Retrofit {
        return Retrofit.Builder().baseUrl(Constans.BASE_URL)
            .addConverterFactory(gson)
            .build()
    }
}