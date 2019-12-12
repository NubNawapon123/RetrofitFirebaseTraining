package com.example.retrofitfirebasemvp.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitfirebasemvp.home.UserCallback
import com.example.retrofitfirebasemvp.model.UserListModel
import kotlinx.android.synthetic.main.item_member_user.view.*

class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var textViewName = itemView.tv_name
    var textViewUserId = itemView.tv_userId
    var itemUser = itemView.item_user
    var textViewBmi = itemView.tv_bmi

    fun bindData(userListModel: UserListModel, callback: UserCallback?, position: Int) {

        textViewName.text = "Name : " + userListModel.name
        textViewUserId.text = "UserID: ${userListModel.name} "
        textViewBmi.text = "BMI : ${userListModel.bmi}"

        itemUser?.apply {

            setOnClickListener {
                callback?.onSelectItem(userListModel)
            }

            setOnLongClickListener {
                it.post {
                    callback?.onSelectItemLongClick(userListModel)
                }
            }
        }
    }

}