package com.example.imgurgalleryapp.data.mapper

import com.example.imgurgalleryapp.data.model.ImageResponse
import com.example.imgurgalleryapp.domain.model.Image
import com.example.imgurgalleryapp.presentation.util.Constants
import com.example.imgurgalleryapp.presentation.util.formatDate


object ImageMapper {

    fun convertData(imageResponse: ImageResponse): Image {
        return Image(
            id = imageResponse.id,
            title = imageResponse.title,
            date = formatDate(imageResponse.datetime, Constants.dateMMMyyyyFormat),
            description = imageResponse.images?.get(0)?.description,
            ups = imageResponse.ups,
            downs = imageResponse.downs,
            score = imageResponse.score,
            imageUrl = imageResponse.images?.get(0)?.link
        )
    }
}