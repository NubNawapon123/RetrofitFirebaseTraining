package com.example.retrofitfirebasemvp.service

import com.example.retrofitfirebasemvp.constant.REMOTE_SERVICE_URL
import com.example.retrofitfirebasemvp.model.UserListModel
import com.example.retrofitfirebasemvp.model.UserModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

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
//        @PUT("/task/{title}.json")
//        fun createTask( // title configured as identifier for tasks
//            @Path("title") title: String?,
//            @Body task: Task?
//        ): Call<Task?>?
//
//        @GET("/task/{title}.json")
//        fun getTask(@Path("title") title: String?): Call<Task?>? // could be used for fetching details or checking if item already exists

        // note that we'll receive a Map here from firebase with key being the identifier
        @get:GET("/task/.json")
        val allTasks: Call<Map<String?, Any?>?>?

//        // note that we'll receive a Map here from firebase with key being the identifier
//        @get:GET("/task/test.json")
//        val allTasksModel: Call<Task>?

        @DELETE("/user/userList/{position}.json")
        fun deleteUserListModel(@Path("position") userListPosition: String): Call<UserListModel?>?

        @get:GET("/user.json")
        val getUserList: Call<UserModel>

        @get:GET("/user.json")
        val getUserListMap: Call<Map<Int, UserModel>>
    }

}