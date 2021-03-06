package io.github.ovso.worship.data.remote

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://www.googleapis.com"
const val BASE_URL_YOUTUBE = "https://www.youtube.com"

open class Api(
  private val baseUrl: String
) {

  inline fun <reified T> create(): T {
    val client = with(OkHttpClient.Builder()) {
      readTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
      connectTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
      followRedirects(false)
      addInterceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
          .header("Content-Type", "application/json")
        val request = requestBuilder.build()
        chain.proceed(request)
      }
      addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
      })
    }.build()

    return Retrofit.Builder().baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      .client(client)
      .build()
      .create(T::class.java)
  }

  companion object {
    const val TIMEOUT_SECONDS = 60
  }
}
