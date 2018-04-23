package com.luigivampa92.yandextest.domain.model

import java.util.*

data class Image(
        val resourceId: String,
        val name: String,
        val preview: String,
        val file: String,
        val created: Date,
        val modified: Date,
        val path: String,
        val mediaType: String,
        val mimeType: String,
        val antivirusStatus: String,
        val md5: String,
        val sha256: String,
        val size: Int,
        val revision: String
)