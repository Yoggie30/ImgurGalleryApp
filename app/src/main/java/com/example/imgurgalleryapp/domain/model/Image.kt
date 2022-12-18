package com.example.imgurgalleryapp.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Image(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @NotNull
    var id: String = "",
    var title: String? = null,
    var date: String? = null,
    var description: String? = null,
    var ups: Int? = null,
    var downs: Int? = null,
    var score: Int? = null,
    var imageUrl: String? = null
) : Parcelable