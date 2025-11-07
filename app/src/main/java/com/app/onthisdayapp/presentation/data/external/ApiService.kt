package com.app.onthisdayapp.presentation.data.external


import com.app.onthisdayapp.presentation.data.models.WikiApiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


private const val BASE_URL =
    "https://api.wikimedia.org/"

interface APIService {
    @GET("feed/v1/wikipedia/pt/onthisday/selected/{month}/{day}")
    suspend fun getDayInfo(
        @Path("month") month: String,
        @Path("day") day: String
    ): Response<WikiApiResponse>
}


private const val USER_AGENT = "OnThisDayApp/1.0 (https://yourdomain.com; contact@yourdomain.com)"

object ServiceApi {
    val retrofitService : APIService by lazy {
        val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()

        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestWithUserAgent = original.newBuilder()
                    .header("User-Agent", USER_AGENT)
                    .build()
                chain.proceed(requestWithUserAgent)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
        retrofit.create(APIService::class.java)
    }
}