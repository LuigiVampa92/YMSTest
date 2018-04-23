package com.luigivampa92.yandextest.data.repository

import com.luigivampa92.yandextest.data.RestApi
import com.luigivampa92.yandextest.data.model.ImageModel
import com.luigivampa92.yandextest.domain.model.Image
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class YandexDiskRepository @Inject constructor (
        private val restApi: RestApi
) {

    fun getDiskInfo() {}

    fun getImages(limit: Int, offset: Int): Observable<List<Image>> = restApi.getFiles(getImagesParams(limit, offset))
            .map { mapList(it.items) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private fun getImagesParams(limit: Int, offset: Int): Map<String,String> = mapOf(
            Pair("media_type", "image"),
            Pair("preview_size", "S"),
            Pair("limit", limit.toString()),
            Pair("offset", offset.toString())
    )

    private fun mapList(list: List<ImageModel>): List<Image> = list.map { map(it) }

    private fun map(model: ImageModel) = Image(
            model.resourceId,
            model.name,
            model.preview,
            model.file,
            model.created,
            model.modified,
            model.path,
            model.mediaType,
            model.mimeType,
            model.antivirusStatus,
            model.md5,
            model.sha256,
            model.size,
            model.revision
    )
}