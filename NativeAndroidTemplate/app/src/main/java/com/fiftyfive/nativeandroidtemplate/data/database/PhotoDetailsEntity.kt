package com.fiftyfive.nativeandroidtemplate.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model used to represent a Photo stored locally in a Room database.
 */
@Entity
data class PhotoDetailsEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "author") val author : String,
    @ColumnInfo(name = "width") val width : Int,
    @ColumnInfo(name = "height") val height : Int,
    @ColumnInfo(name = "url") val url : String,
    @ColumnInfo(name = "download_url") val downloadUrl : String,
    @ColumnInfo(name = "is_liked") val isLiked : Boolean
)