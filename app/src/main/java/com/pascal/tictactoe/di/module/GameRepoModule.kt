package com.pascal.tictactoe.di.module

import com.pascal.tictactoe.repositories.HistoryRepository
import com.pascal.tictactoe.repositories.HistoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named


@Module
@InstallIn(ViewModelComponent::class)
abstract class GameRepoModule {

    @Binds
    @Named("GameRepo")
    abstract fun bindGameRepositoryApi(historyRepositoryImpl: HistoryRepositoryImpl) : HistoryRepository
}