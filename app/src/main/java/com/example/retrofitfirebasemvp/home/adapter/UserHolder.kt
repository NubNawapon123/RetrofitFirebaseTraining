package com.example.retrofitfirebasemvp.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitfirebasemvp.home.UserCallback
import com.example.retrofitfirebasemvp.model.UserListModel
import kotlinx.android.synthetic.main.item_member_user.view.*

class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var textViewName = itemView.tv_name
    var textViewAge = itemView.tv_age
    var itemUser = itemView.item_user

    fun bindData(userListModel: UserListModel, callback: UserCallback?, position: Int) {

        textViewName.text = "Name : " + userListModel.name
        textViewAge.text = "Age: ${userListModel.age} "

        itemUser?.apply {

            setOnClickListener {
                callback?.onSelectItem(userListModel, position)
            }

            setOnLongClickListener {
                it.post {
                    callback?.onSelectItemLongClick(userListModel, position)
                }
            }
        }
    }

}