package com.example.imgurgalleryapp.presentation.home

import com.example.imgurgalleryapp.domain.model.Image


interface HomeListener {
    fun onSuccess(imgList : List<Image>)
    fun onFailure(message : String)
}