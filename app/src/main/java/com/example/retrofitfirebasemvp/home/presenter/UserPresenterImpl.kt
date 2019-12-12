package com.example.retrofitfirebasemvp.home.presenter

import com.example.retrofitfirebasemvp.model.UserListModel
import com.example.retrofitfirebasemvp.model.UserModel
import com.example.retrofitfirebasemvp.model.UserResponseModel
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
        val service = remoteService?.getDataMember
        service?.enqueue(object : Callback<UserResponseModel> {
            override fun onFailure(call: Call<UserResponseModel>?, t: Throwable?) {
                view?.updateData(UserModel())
                view?.hideLoading()
            }

            override fun onResponse(
                call: Call<UserResponseModel>?,
                response: Response<UserResponseModel>?
            ) {
                val model = UserModel()
                response?.body()?.userList?.map {
                    val modelList = UserListModel().apply {
                        idUser = it.value.idUser
                        name = it.value.name
                        weight = it.value.weight
                        height = it.value.height
                    }
                    model.userList.add(modelList)
                    model.email = response?.body()?.email
                }

                view?.updateData(model)
                view?.hideLoading()
            }
        })

    }

    override fun removeItemMember(userId: String) {
        view?.showLoading()
        val service = remoteService?.removeMember("ID_$userId")
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