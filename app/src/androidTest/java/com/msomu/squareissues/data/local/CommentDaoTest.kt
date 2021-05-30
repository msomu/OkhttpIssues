package com.msomu.squareissues.data.local

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.msomu.squareissues.data.GithubIssuesItem
import com.msomu.squareissues.mock.mockUser
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.msomu.squareissues.data.Comment
import org.junit.Rule

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class CommentDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: IssuesDatabase
    private lateinit var commentDao: CommentDao
    private lateinit var issueDao: IssueDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            IssuesDatabase::class.java
        ).allowMainThreadQueries().build()
        commentDao = database.commentDao()
        issueDao = database.issueDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertComment() = runBlockingTest {
        val issue = GithubIssuesItem(
            title = "java.lang.ArrayIndexOutOfBoundsException: size=0 offset=6 byteCount=1 at okio.-Util.checkOffsetAndCount(-Util.kt:26) ",
            body = "platform: Android\\r\\nok version:4.9.1 \\r\\nretrofit:2.9.0\\r\\nit was collected from released app, so i don't know how to reproduce it. But i run into it sometimes. There isn't any stack trace related to my project code while multiple requests running in the background,including websocket, so i won't be able to provide the test case. \\r\\nThread Name: 'OkHttp Dispatcher' Back traces starts. java.lang.IllegalStateException: No deadline at okio.Timeout.deadlineNanoTime(Timeout.kt:56) at okio.AsyncTimeout\$Companion.scheduleTimeout(AsyncTimeout.kt:239) at okio.AsyncTimeout\$Companion.access\$scheduleTimeout(AsyncTimeout.kt:205) at okio.AsyncTimeout.enter(AsyncTimeout.kt:56) at okio.AsyncTimeout\$source\$1.read(AsyncTimeout.kt:329) at okio.RealBufferedSource.indexOf(RealBufferedSource.kt:427) at okio.RealBufferedSource.readUtf8LineStrict(RealBufferedSource.kt:320) at okhttp3.internal.http1.HeadersReader.readLine(HeadersReader.kt:29) at okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders(Http1ExchangeCodec.kt:178) at okhttp3.internal.connection.Exchange.readResponseHeaders(Exchange.kt:106) at okhttp3.internal.http.CallServerInterceptor.intercept(CallServerInterceptor.kt:79) at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) at okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:34) at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) at okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:95) at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) at okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:83) at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) at okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:76) at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) at okhttp3.internal.connection.RealCall.getResponseWithInterceptorChain\$okhttp(RealCall.kt:201) at okhttp3.internal.connection.RealCall\$AsyncCall.run(RealCall.kt:517) at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167) at java.util.concurrent.ThreadPoolExecutor\$Worker.run(ThreadPoolExecutor.java:641) at java.lang.Thread.run(Thread.java:919) Back traces ends.\\r\\n",
            updated_at = "2021-05-27T14:46:17Z",
            user = mockUser(),
            state = "open",
            comments_url = "https://api.github.com/repos/square/okhttp/issues/6689/comments",
            number = 6689
        )
        issueDao.insertIssues(listOf(issue))


        val comment = Comment(
            issueNumber = 6689,
            user = mockUser(),
            updated_at = "2021-05-27T14:46:17Z",
            id= 847790057,
            body = "Can you reproduce with a test case that doesn't import from our internal APIs? Everything in internal is implementation details that should never be used directly."
        )
        Log.d("TestCase","expected comment $comment")
        commentDao.insertComments(listOf(comment))

        val comments = commentDao.getAllComments(6689).first()
        Log.d("TestCase","actual comment ${comments.first()}")
        assertThat(comments).contains(comment)
    }

    @Test
    fun deleteAllComments() = runBlockingTest {
        val issue = GithubIssuesItem(
            title = "java.lang.ArrayIndexOutOfBoundsException: size=0 offset=6 byteCount=1 at okio.-Util.checkOffsetAndCount(-Util.kt:26) ",
            body = "platform: Android\\r\\nok version:4.9.1 \\r\\nretrofit:2.9.0\\r\\nit was collected from released app, so i don't know how to reproduce it. But i run into it sometimes. There isn't any stack trace related to my project code while multiple requests running in the background,including websocket, so i won't be able to provide the test case. \\r\\nThread Name: 'OkHttp Dispatcher' Back traces starts. java.lang.IllegalStateException: No deadline at okio.Timeout.deadlineNanoTime(Timeout.kt:56) at okio.AsyncTimeout\$Companion.scheduleTimeout(AsyncTimeout.kt:239) at okio.AsyncTimeout\$Companion.access\$scheduleTimeout(AsyncTimeout.kt:205) at okio.AsyncTimeout.enter(AsyncTimeout.kt:56) at okio.AsyncTimeout\$source\$1.read(AsyncTimeout.kt:329) at okio.RealBufferedSource.indexOf(RealBufferedSource.kt:427) at okio.RealBufferedSource.readUtf8LineStrict(RealBufferedSource.kt:320) at okhttp3.internal.http1.HeadersReader.readLine(HeadersReader.kt:29) at okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders(Http1ExchangeCodec.kt:178) at okhttp3.internal.connection.Exchange.readResponseHeaders(Exchange.kt:106) at okhttp3.internal.http.CallServerInterceptor.intercept(CallServerInterceptor.kt:79) at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) at okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:34) at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) at okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:95) at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) at okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:83) at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) at okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:76) at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) at okhttp3.internal.connection.RealCall.getResponseWithInterceptorChain\$okhttp(RealCall.kt:201) at okhttp3.internal.connection.RealCall\$AsyncCall.run(RealCall.kt:517) at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167) at java.util.concurrent.ThreadPoolExecutor\$Worker.run(ThreadPoolExecutor.java:641) at java.lang.Thread.run(Thread.java:919) Back traces ends.\\r\\n",
            updated_at = "2021-05-27T14:46:17Z",
            user = mockUser(),
            state = "open",
            comments_url = "https://api.github.com/repos/square/okhttp/issues/6689/comments",
            number = 6689
        )
        issueDao.insertIssues(listOf(issue))
        val comment = Comment(
            issueNumber = 6689,
            user = mockUser(),
            updated_at = "2021-05-27T14:46:17Z",
            id= 847790057,
            body = "Can you reproduce with a test case that doesn't import from our internal APIs? Everything in internal is implementation details that should never be used directly."
        )
        commentDao.insertComments(listOf(comment))
        commentDao.deleteAllComments(6689)

        val comments = commentDao.getAllComments(6689).first()
        assertThat(comments).isEmpty()
    }

    @Test
    fun deletingIssueDeleteComments() = runBlockingTest {
        val issue = GithubIssuesItem(
            title = "java.lang.ArrayIndexOutOfBoundsException: size=0 offset=6 byteCount=1 at okio.-Util.checkOffsetAndCount(-Util.kt:26) ",
            body = "platform: Android\\r\\nok version:4.9.1 \\r\\nretrofit:2.9.0\\r\\nit was collected from released app, so i don't know how to reproduce it. But i run into it sometimes. There isn't any stack trace related to my project code while multiple requests running in the background,including websocket, so i won't be able to provide the test case. \\r\\nThread Name: 'OkHttp Dispatcher' Back traces starts. java.lang.IllegalStateException: No deadline at okio.Timeout.deadlineNanoTime(Timeout.kt:56) at okio.AsyncTimeout\$Companion.scheduleTimeout(AsyncTimeout.kt:239) at okio.AsyncTimeout\$Companion.access\$scheduleTimeout(AsyncTimeout.kt:205) at okio.AsyncTimeout.enter(AsyncTimeout.kt:56) at okio.AsyncTimeout\$source\$1.read(AsyncTimeout.kt:329) at okio.RealBufferedSource.indexOf(RealBufferedSource.kt:427) at okio.RealBufferedSource.readUtf8LineStrict(RealBufferedSource.kt:320) at okhttp3.internal.http1.HeadersReader.readLine(HeadersReader.kt:29) at okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders(Http1ExchangeCodec.kt:178) at okhttp3.internal.connection.Exchange.readResponseHeaders(Exchange.kt:106) at okhttp3.internal.http.CallServerInterceptor.intercept(CallServerInterceptor.kt:79) at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) at okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:34) at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) at okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:95) at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) at okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:83) at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) at okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:76) at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109) at okhttp3.internal.connection.RealCall.getResponseWithInterceptorChain\$okhttp(RealCall.kt:201) at okhttp3.internal.connection.RealCall\$AsyncCall.run(RealCall.kt:517) at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167) at java.util.concurrent.ThreadPoolExecutor\$Worker.run(ThreadPoolExecutor.java:641) at java.lang.Thread.run(Thread.java:919) Back traces ends.\\r\\n",
            updated_at = "2021-05-27T14:46:17Z",
            user = mockUser(),
            state = "open",
            comments_url = "https://api.github.com/repos/square/okhttp/issues/6689/comments",
            number = 6689
        )
        issueDao.insertIssues(listOf(issue))

        val comment = Comment(
            issueNumber = 6689,
            user = mockUser(),
            updated_at = "2021-05-27T14:46:17Z",
            id= 847790057,
            body = "Can you reproduce with a test case that doesn't import from our internal APIs? Everything in internal is implementation details that should never be used directly."
        )
        commentDao.insertComments(listOf(comment))
        issueDao.deleteAllIssues()

        val comments = commentDao.getAllComments(6689).first()

        assertThat(comments).isEmpty()
    }
}