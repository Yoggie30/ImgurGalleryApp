package com.example.imgurgalleryapp.data.api


import com.example.imgurgalleryapp.data.model.HomeResponse
import com.example.imgurgalleryapp.presentation.util.Constants
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import timber.log.Timber
import java.util.concurrent.TimeUnit

interface ImageApi {
    @GET("gallery/search/{sort}/{window}/{page}")
    suspend fun getGalleryImages(
        @Path("sort") sort: String,
        @Path("window") window: String,
        @Path("page") page: Int,
    ): Response<HomeResponse>


    companion object {
        operator fun invoke(): ImageApi {
            Timber.e("Base URL: ${Constants.BASE_URL}")
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getAuthorizationHeaderInterceptor())
                .build()
                .create(ImageApi::class.java)
        }


        private fun getAuthorizationHeaderInterceptor(): OkHttpClient {
            return UnsafeOkHttpClient.getUnsafeOkHttpClient()
                .build()
                .newBuilder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .connectionPool(ConnectionPool(0, 5, TimeUnit.MINUTES))
                .protocols(listOf(Protocol.HTTP_1_1))
                .addInterceptor { chain ->
                    val originalRequest = chain.request()
                    val builder = originalRequest.newBuilder()
                        .header(
                        "Authorization",
                        "Client-ID " + Constants.CLIENT_ID
                    )
                   // builder.addHeader("Authorization", "Bearer ${Constants.CLIENT_SECRET}")
                    val newRequest = builder.build()
                    Timber.e(" headers: ${newRequest.headers()}")
                    Timber.e(" Request: $newRequest")
                    chain.proceed(newRequest)
                }.build()
        }
    }

}