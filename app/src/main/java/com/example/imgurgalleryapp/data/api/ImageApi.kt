package com.example.imgurgalleryapp.data.api


import com.example.imgurgalleryapp.data.model.HomeResponse
import com.example.imgurgalleryapp.presentation.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImageApi {
    @GET("gallery/{section}/{sort}/{window}/{page}")
    suspend fun getGalleryImages(
        @Path("section") section : String,
        @Path("sort") sort : String,
        @Path("window") window : String,
        @Path("page") page : String,
        @Query("showViral") showViral : Boolean,
        @Query("mature") showMature : Boolean,
        @Query("album_previews") albumPreview : Boolean
    ) : Response<HomeResponse>

    companion object {
        operator fun invoke(): ImageApi {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getAuthorizationHeaderInterceptor())
                .build()
                .create(ImageApi::class.java)
        }

        private fun getAuthorizationHeaderInterceptor(): OkHttpClient {
            return OkHttpClient().newBuilder().addInterceptor { chain ->
                val originalRequest = chain.request()
                val builder = originalRequest.newBuilder().header(
                    "Authorization",
                    "Client-ID " + Constants.CLIENT_ID
                )
                val newRequest = builder.build()
                chain.proceed(newRequest)
            }.build()
        }
    }

}