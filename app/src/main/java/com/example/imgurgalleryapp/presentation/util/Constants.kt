package com.example.imgurgalleryapp.presentation.util

import java.text.SimpleDateFormat
import java.util.*

object Constants {
    const val LIST_COUNT = 1
    const val GRID_COUNT = 3

    const val BASE_URL = "https://api.imgur.com/3/"
    const val CLIENT_ID = "3f364098fd8e920"
    const val CLIENT_SECRET = "3536acf7d78da928e57c4de2fb1802f6ade51919"
    val dateMMMyyyyFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
    val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    val dateFormatdMMddyyyyHHmm = SimpleDateFormat("MM/dd/yyyy HH:mm a", Locale.ENGLISH)

}