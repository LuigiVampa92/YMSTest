package com.luigivampa92.yandextest.routing.transition

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ZoomedImageTransitionData(val title: String, val fullSizeImageUrl: String) : Parcelable