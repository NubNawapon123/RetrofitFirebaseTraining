package com.example.retrofitfirebasemvp.home.presenter

import com.example.retrofitfirebasemvp.model.UserListModel
import com.example.retrofitfirebasemvp.model.UserModel
import com.example.retrofitfirebasemvp.service.RemoteServiceImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPresenterImpl(var view: UserContact.View?) : UserContact.Presenter {

    private var remoteService: RemoteServiceImpl.RemoteService? = null

    override fun startRemoteService(remoteService: RemoteServiceImpl.RemoteService?) {
        this.remoteService = remoteService
    }

    override fun loadData() {
        view?.showLoading()
        val service = remoteService?.getUserList
        service?.enqueue(object : Callback<UserModel> {
            override fun onFailure(call: Call<UserModel>?, t: Throwable?) {
                view?.updateData(UserModel())
                view?.hideLoading()
            }

            override fun onResponse(call: Call<UserModel>?, response: Response<UserModel>?) {
                val model = response?.body()
                view?.updateData(model ?: UserModel())
                view?.hideLoading()
            }
        })

    }

    override fun removeItemMember(position: String) {
        view?.showLoading()
        val service = remoteService?.deleteUserListModel(position)
        service?.enqueue(object : Callback<UserListModel?> {
            override fun onFailure(call: Call<UserListModel?>?, t: Throwable?) {
                view?.hideLoading()
            }

            override fun onResponse(
                call: Call<UserListModel?>?,
                response: Response<UserListModel?>?
            ) {
                loadData()
            }
        })
    }
}