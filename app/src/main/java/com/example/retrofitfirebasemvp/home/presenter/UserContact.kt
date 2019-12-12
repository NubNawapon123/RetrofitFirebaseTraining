package com.example.retrofitfirebasemvp.home.presenter

import com.example.retrofitfirebasemvp.common.BaseView
import com.example.retrofitfirebasemvp.model.UserModel
import com.example.retrofitfirebasemvp.service.RemoteServiceImpl

class UserContact {
    interface Presenter {

        fun startRemoteService(remoteService: RemoteServiceImpl.RemoteService?)

        fun loadData()

        fun removeItemMember(userId: String)
    }

    interface View : BaseView {

        fun updateData(model: UserModel)

    }
}