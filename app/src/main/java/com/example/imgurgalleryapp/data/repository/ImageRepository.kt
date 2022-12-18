package com.example.imgurgalleryapp.data.repository

import com.example.imgurgalleryapp.data.api.ImageApi
import com.example.imgurgalleryapp.data.model.HomeResponse
import com.example.imgurgalleryapp.domain.model.Image
import com.example.imgurgalleryapp.data.db.AppDatabase
import retrofit2.Response

class ImageRepository(
    private val api : ImageApi,
    private val db : AppDatabase
) {
    suspend fun galleryImages(section : String, sort : String, window : String) : Response<HomeResponse> {
        return api.getGalleryImages(section, sort, window, "1",
            showViral = true,
            showMature = true,
            albumPreview = true
        )
    }

    suspend fun saveImages(images : List<Image>) {
        db.getImageDao().deleteImages()
        db.getImageDao().saveImages(images)
    }

    suspend fun getAllSavedImages() : List<Image> {
        return db.getImageDao().getAllSavedImages()
    }
}