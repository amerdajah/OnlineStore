package com.example.onlinestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SelectedCategoryItem(val title: String, val type: String): Parcelable {

    constructor(): this("", "")
}