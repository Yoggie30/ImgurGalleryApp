package com.example.imgurgalleryapp.presentation.util

import java.text.SimpleDateFormat
import java.util.*

object Constants {
    const val LIST_COUNT = 1
    const val GRID_COUNT = 3

    const val BASE_URL = "https://api.imgur.com/3/"
    const val CLIENT_ID = "607807a177913e7"
    const val CLIENT_SECRET = "d7e23c7e7236aae2aeda967c35a2d6c3ec137f92"
    val dateMMMyyyyFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
    val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    val dateFormatdMMddyyyyHHmm = SimpleDateFormat("MM/dd/yyyy HH:mm a", Locale.ENGLISH)

}