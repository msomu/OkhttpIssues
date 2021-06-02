package com.msomu.squareissues.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Loggable
import com.msomu.squareissues.Constants.BASE_URL
import com.msomu.squareissues.Constants.DATABASE_NAME
import com.msomu.squareissues.data.local.CommentDao
import com.msomu.squareissues.data.local.IssueDao
import com.msomu.squareissues.data.local.IssuesDatabase
import com.msomu.squareissues.data.remote.IssuesApi
import com.msomu.squareissues.repository.DefaultIssueRepository
import com.msomu.squareissues.repository.IssueRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.host
import io.ktor.http.URLProtocol
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IssuesModule {

    @Provides
    @Singleton
    fun provideRetrofit(): HttpClient =
        HttpClient(OkHttp) {
            defaultRequest {
                host = BASE_URL
                url {
                    protocol = URLProtocol.HTTPS
                }
            }
            install(JsonFeature) {
                serializer = GsonSerializer {
                    setPrettyPrinting()
                    disableHtmlEscaping()
                }
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("Ktor", message)
                    }
                }
                level = LogLevel.ALL
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }
            engine {
                addInterceptor(CurlInterceptor(Loggable { Log.v("Curl", it) }))
            }
        }

    @Provides
    @Singleton
    fun provideRestaurantApi(httpClient: HttpClient) = IssuesApi(httpClient)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): IssuesDatabase =
        Room.databaseBuilder(app, IssuesDatabase::class.java, DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun provideRepository(
        api: IssuesApi,
        database: IssuesDatabase,
        issueDao: IssueDao,
        commentDao: CommentDao
    ): IssueRepository = DefaultIssueRepository(api, database, issueDao, commentDao)

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