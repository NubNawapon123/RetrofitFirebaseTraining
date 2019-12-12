package com.example.retrofitfirebasemvp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    @SerializedName("email") var email: String? = "",
    @SerializedName("userList") var userList: ArrayList<UserListModel> = arrayListOf()
) : Parcelable

@Parcelize
data class UserListModel(
    @SerializedName("idUser") var idUser: String? = "",
    @SerializedName("name") var name: String? = "",
    @SerializedName("weight") var weight: String? = "",
    @SerializedName("height") var height: String? = "",
    @SerializedName("bmi") var bmi: String? = ""
) : Parcelable