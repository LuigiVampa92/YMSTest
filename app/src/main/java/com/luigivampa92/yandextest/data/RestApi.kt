package com.luigivampa92.yandextest.data

import com.luigivampa92.yandextest.data.model.ImageListReponse
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RestApi {

    @GET("v1/disk")
    fun getDiskInfo(): Observable<ResponseBody>

    @GET("v1/disk/resources/files")
    fun getFiles(@QueryMap params: Map<String,String>): Observable<ImageListReponse>
}