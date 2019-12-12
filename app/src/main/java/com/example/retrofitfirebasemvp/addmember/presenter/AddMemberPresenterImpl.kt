package com.example.retrofitfirebasemvp.addmember.presenter

import com.example.retrofitfirebasemvp.model.UserListResponseModel
import com.example.retrofitfirebasemvp.service.RemoteServiceImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddMemberPresenterImpl(private val view: AddMemberContract.View) :
    AddMemberContract.Presenter {

    companion object {
        const val KEY_USERS = "users"
        const val KEY_USERS_LIST = "userList"
        const val USER_KEY_ID = "ID_"
    }

    private var remoteService: RemoteServiceImpl.RemoteService? = null

    override fun startRemoteService(remoteService: RemoteServiceImpl.RemoteService?) {
        this.remoteService = remoteService
    }

    override fun addMemberUser(
        userId: String?,
        name: String?,
        weight: String?,
        height: String?
    ) {
        if (!userId.isNullOrBlank()) {
            val model = UserListResponseModel(userId, name, weight, height)
            val callApiAddMember = remoteService?.addMember(USER_KEY_ID + userId, model)
            callApiAddMember?.enqueue(object : Callback<UserListResponseModel?> {
                override fun onFailure(call: Call<UserListResponseModel?>?, t: Throwable?) {
                    // load fail ja tam a rai D na? (if call api fail you can handle error)
                }

                override fun onResponse(
                    call: Call<UserListResponseModel?>?,
                    response: Response<UserListResponseModel?>?
                ) {
                    view?.addMemberSuccess()
                }
            })
        }
    }

}