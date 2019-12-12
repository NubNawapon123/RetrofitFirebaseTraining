package com.example.retrofitfirebasemvp.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitfirebasemvp.R
import com.example.retrofitfirebasemvp.addmember.AddMemberActivity
import com.example.retrofitfirebasemvp.common.BaseActivity
import com.example.retrofitfirebasemvp.home.adapter.UserAdapter
import com.example.retrofitfirebasemvp.home.presenter.UserContact
import com.example.retrofitfirebasemvp.home.presenter.UserPresenterImpl
import com.example.retrofitfirebasemvp.model.UserListModel
import com.example.retrofitfirebasemvp.model.UserModel
import com.example.retrofitfirebasemvp.service.RemoteServiceImpl
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), UserContact.View {

    companion object {
        const val REQUEST_CODE = 999
    }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            presenter.loadData()
        }
    }

    override fun updateData(model: UserModel) {
        userModel = model
        tv_email?.text = "Email :${model.email}"
        userAdapter?.updateDataUserList(userModel?.userList ?: listOf())
    }

    private fun initView() {
        userAdapter = UserAdapter(userModel?.userList ?: arrayListOf(), callback)
        rv_user?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }

        btn_add_member_firebase?.setOnClickListener {
            presenter.loadData()
        }

        btn_add_member_data.setOnClickListener {
            startActivityForResult(
                Intent(this, AddMemberActivity::class.java), REQUEST_CODE
            )
        }
    }

    private val callback = object : UserCallback {
        override fun onSelectItem(userListModel: UserListModel, position: Int) {
            startActivityForResult(
                Intent(AddMemberActivity.getStartIntent(this@MainActivity, userListModel)),
                REQUEST_CODE
            )
        }

        override fun onSelectItemLongClick(userListModel: UserListModel, position: Int) {
            presenter.removeItemMember(userListModel.idUser ?: "")
        }
    }

}
