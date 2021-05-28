package com.msomu.squareissues.module

import com.msomu.squareissues.data.IssuesRepository
import com.msomu.squareissues.data.database.IssuesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IssuesModule {

    @Provides
    @Singleton
    fun provideDatabase() : IssuesRepository = IssuesDatabase()
}