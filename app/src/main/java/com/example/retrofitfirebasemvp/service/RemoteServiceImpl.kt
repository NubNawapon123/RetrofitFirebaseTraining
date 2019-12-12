package com.example.retrofitfirebasemvp.service

import com.example.retrofitfirebasemvp.constant.REMOTE_SERVICE_URL
import com.example.retrofitfirebasemvp.model.UserListModel
import com.example.retrofitfirebasemvp.model.UserListResponseModel
import com.example.retrofitfirebasemvp.model.UserResponseModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class RemoteServiceImpl {

    val retrolfit = Retrofit.Builder()
        .baseUrl(REMOTE_SERVICE_URL)
        .addConverterFactory(GsonConverterFactory.create()) // use gson converter
        .build()

    var service: RemoteService? = null

    fun getInstance(): RemoteService? {
        if (service == null) {
            service = retrolfit.create(RemoteService::class.java)
        }
        return service
    }

    interface RemoteService {
        @PUT("/users/userList/{id}.json")
        fun addMember( // title configured as identifier for tasks
            @Path("id") title: String?,
            @Body task: UserListResponseModel?
        ): Call<UserListResponseModel?>?

        @DELETE("/users/userList/{id}.json")
        fun removeMember(@Path("id") userListPosition: String): Call<UserListModel?>?

        @get:GET("/users.json")
        val getDataMember: Call<UserResponseModel>

    }

}