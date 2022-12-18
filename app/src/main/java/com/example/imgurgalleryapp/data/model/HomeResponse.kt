package com.example.imgurgalleryapp.data.model

import com.google.gson.annotations.SerializedName

data class HomeResponse(

    @SerializedName("data")
    var data: List<ImageResponse>? = null,

    @SerializedName("success")
    var success: Boolean? = null,

    @SerializedName("status")
    var status: Int? = null
)