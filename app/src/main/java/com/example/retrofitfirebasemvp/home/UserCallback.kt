package com.example.retrofitfirebasemvp.home

import com.example.retrofitfirebasemvp.model.UserListModel

interface UserCallback {
    fun onSelectItem(userListModel: UserListModel)
    fun onSelectItemLongClick(userListModel: UserListModel)
}