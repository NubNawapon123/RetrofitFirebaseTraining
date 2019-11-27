package com.example.retrofitfirebasemvp.home

import com.example.retrofitfirebasemvp.model.UserListModel

interface UserCallback {
    fun onSelectItem(userListModel: UserListModel, position: Int)
    fun onSelectItemLongClick(userListModel: UserListModel, position: Int)
}