package com.example.imgurgalleryapp.presentation.home

import com.example.imgurgalleryapp.data.mapper.ImageMapper
import com.example.imgurgalleryapp.data.repository.ImageRepository
import com.example.imgurgalleryapp.presentation.base.BaseViewModel
import com.example.imgurgalleryapp.presentation.util.Coroutines


class HomeViewModel(
    private val repository: ImageRepository
) : BaseViewModel() {

    var homeListener: HomeListener? = null

    fun getGalleryImages(section: String, sort: String, window: String) {
        Coroutines.main {
            val response = repository.galleryImages(section, sort, window)
            if (response.isSuccessful) {
                response.body()?.data?.let {
                    val result = it.map { imgMap ->
                        ImageMapper.convertData(imgMap)
                    }
                    homeListener?.onSuccess(result)
                    repository.saveImages(result)
                    return@main
                }
            } else
                homeListener?.onFailure("Error Code: ${response.code()}")
        }

    }

    fun getCachedGalleryImages() {
        Coroutines.main {
            val savedImages = repository.getAllSavedImages()
            homeListener?.onSuccess(savedImages)
        }
    }
}