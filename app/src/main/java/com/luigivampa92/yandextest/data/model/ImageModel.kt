package com.luigivampa92.yandextest.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ImageModel(
        @SerializedName("resource_id") val resourceId: String,
        @SerializedName("name") val name: String,
        @SerializedName("preview") val preview: String,
        @SerializedName("file") val file: String,
        @SerializedName("created") val created: Date,
        @SerializedName("modified") val modified: Date,
        @SerializedName("path") val path: String,
        @SerializedName("media_type") val mediaType: String,
        @SerializedName("mime_type") val mimeType: String,
        @SerializedName("antivirus_status") val antivirusStatus: String,
        @SerializedName("md5") val md5: String,
        @SerializedName("sha256") val sha256: String,
        @SerializedName("size") val size: Int,
        @SerializedName("revision") val revision: String
)