package com.example.onlinestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Offer(val seller: String, val title: String, val description: String, val offerImage: String, val timeSnap: String,
                 val price: String): Parcelable {

    constructor(): this("", "", "","0","","0")
}