package com.fiftyfive.nativeandroidtemplate.business

import com.google.gson.annotations.SerializedName

/**
 *  Model class for the photo
 *  @param id the unique if of the photo
 *  @param author name of the photo
 *  @param width the width of the photo
 *  @param height the height of the photo
 *  @param url the url of the photo
 *  @param downloadUrl the url to be used to display photo
 *  @param isFavorite is the photo is favorite or not
 */
data class Photo(
    @SerializedName("id")
    val id :String,
    @SerializedName("author")
    val author :String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("download_url")
    val downloadUrl: String,
    val isFavorite: Boolean
)
