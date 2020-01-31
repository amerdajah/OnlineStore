package com.example.onlinestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val uid: String, val userName: String, val profileImageUri: String, val status: String): Parcelable {
    constructor(): this("", "", "", "")
}