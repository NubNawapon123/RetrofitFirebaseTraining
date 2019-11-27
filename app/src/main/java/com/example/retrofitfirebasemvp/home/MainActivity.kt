package com.example.retrofitfirebasemvp.home

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitfirebasemvp.R
import com.example.retrofitfirebasemvp.common.BaseActivity
import com.example.retrofitfirebasemvp.home.adapter.UserAdapter
import com.example.retrofitfirebasemvp.home.presenter.UserContact
import com.example.retrofitfirebasemvp.home.presenter.UserPresenterImpl
import com.example.retrofitfirebasemvp.model.UserListModel
import com.example.retrofitfirebasemvp.model.UserModel
import com.example.retrofitfirebasemvp.service.RemoteServiceImpl
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), UserContact.View {

    private lateinit var presenter: UserPresenterImpl
    private var userAdapter: UserAdapter? = null
    private var userModel: UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = UserPresenterImpl(this)
        presenter.startRemoteService(RemoteServiceImpl().getInstance())
        presenter.loadData()
        initView()
    }

    override fun updateData(model: UserModel) {
        userModel = model
        updateEmail(model.email)
        userAdapter?.updateDataUserList(userModel?.userList ?: listOf())
        Toast.makeText(
            this@MainActivity, "email: ${model.email}", Toast.LENGTH_LONG
        ).show()
    }

    private fun initView() {
        userAdapter = UserAdapter(userModel?.userList ?: arrayListOf(), callback)
        rv_user?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
    }

    private fun updateEmail(email: String?) {
        tv_email?.text = "Email :" + email
        btn_add_member?.setOnClickListener {
            Toast.makeText(
                this@MainActivity, "Are u doing?", Toast.LENGTH_LONG
            ).show()
        }
    }

    private val callback = object : UserCallback {
        override fun onSelectItem(userListModel: UserListModel, position: Int) {
            Toast.makeText(
                this@MainActivity, "you select : ${userListModel.name} \n " +
                        "position: $position", Toast.LENGTH_LONG
            ).show()
        }

        override fun onSelectItemLongClick(userListModel: UserListModel, position: Int) {
            Toast.makeText(
                this@MainActivity, "you removeItemMember position : $position", Toast.LENGTH_LONG
            ).show()
        }
    }

}
