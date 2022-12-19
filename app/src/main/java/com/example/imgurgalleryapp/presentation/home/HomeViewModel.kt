package com.example.imgurgalleryapp.presentation.home

import com.example.imgurgalleryapp.data.mapper.ImageMapper
import com.example.imgurgalleryapp.data.repository.ImageRepository
import com.example.imgurgalleryapp.presentation.base.BaseViewModel
import com.example.imgurgalleryapp.presentation.util.Coroutines
import timber.log.Timber


class HomeViewModel(
    private val repository: ImageRepository
) : BaseViewModel() {

    var homeListener: HomeListener? = null

    fun getGalleryImages(sort: String, window: String) {
        Coroutines.main {
            val response = repository.galleryImages(sort, window)
            if (response.isSuccessful) {
                Timber.d("${response.code()}: ${response.body()}")
                response.body()?.data?.let {
                    Timber.d("Response:  $it}")
                    val result = it.map { imgMap ->
                        ImageMapper.convertData(imgMap)
                    }
                    homeListener?.onSuccess(result)
                    repository.saveImages(result)
                    return@main
                }
            } else{
                Timber.d("${response.code()}: ${response.message()}")
                homeListener?.onFailure("Error Code: ${response.code()}")
            }

        }

    }

    fun getCachedGalleryImages() {
        Coroutines.main {
            val savedImages = repository.getAllSavedImages()
            homeListener?.onSuccess(savedImages)
        }
    }
}