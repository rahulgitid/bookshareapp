package com.bksapp.bookshare.di

import com.bksapp.bookshare.data.repository.BookRepositoryImpl
import com.bksapp.bookshare.data.repository.LoginRepositoryImpl
import com.bksapp.bookshare.domain.repository.BookRepository
import com.bksapp.bookshare.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun loginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository


    @Binds
    @Singleton
    abstract fun BookRepository(bookRepositoryImpl: BookRepositoryImpl) : BookRepository
}