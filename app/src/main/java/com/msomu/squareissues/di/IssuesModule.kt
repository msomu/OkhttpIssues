package com.msomu.squareissues.di

import android.app.Application
import androidx.room.Room
import com.msomu.squareissues.Constants.BASE_URL
import com.msomu.squareissues.Constants.DATABASE_NAME
import com.msomu.squareissues.data.local.IssuesDatabase
import com.msomu.squareissues.data.remote.IssuesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object IssuesModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): IssuesApi =
        retrofit.create(IssuesApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : IssuesDatabase =
        Room.databaseBuilder(app, IssuesDatabase::class.java, DATABASE_NAME)
            .build()

    @Singleton
    @Provides
    fun provideIssueDao(
        database: IssuesDatabase
    ) = database.issueDao()

    @Singleton
    @Provides
    fun provideCommentDao(
        database: IssuesDatabase
    ) = database.commentDao()

}