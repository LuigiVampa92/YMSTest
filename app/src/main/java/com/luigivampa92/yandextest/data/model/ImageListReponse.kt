package com.luigivampa92.yandextest.data.model

import com.google.gson.annotations.SerializedName

data class ImageListReponse(
        @SerializedName("items") val items: List<ImageModel>
)