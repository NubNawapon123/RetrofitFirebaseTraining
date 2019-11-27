package com.example.retrofitfirebasemvp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserModel(
    @SerializedName("email") var email: String? = "",
    @SerializedName("userList") var userList: List<UserListModel> = listOf()
) : Parcelable

@Parcelize
data class UserListModel(
    @SerializedName("name") var name: String? = "",
    @SerializedName("age") var age: String? = ""
) : Parcelable