package com.example.retrofitfirebasemvp.addmember.presenter

import com.example.retrofitfirebasemvp.service.RemoteServiceImpl

class AddMemberContract {
    interface Presenter {

        fun startRemoteService(remoteService: RemoteServiceImpl.RemoteService?)

        fun addMemberUser(userId: String?, name: String?, weight: String?, height: String?)
    }

    interface View {

        fun addMemberSuccess()

    }
}