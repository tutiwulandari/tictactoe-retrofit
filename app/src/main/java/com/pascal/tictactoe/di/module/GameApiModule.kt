package com.pascal.tictactoe.di.module

import com.pascal.tictactoe.api.HistoryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GameApiModule {
    @Singleton
    @Provides
    fun provideGameApi(retrofit: Retrofit)= retrofit.create(HistoryApi::class.java)
}