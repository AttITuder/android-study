package com.android.androidstudy.fragment

import android.text.Editable
import android.text.TextWatcher
import android.view.TextureView
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.androidstudy.LoginViewModel
import com.android.androidstudy.R
import com.android.customview.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_quicklogin.*

/**
 * <p>描述:  </p>
 * <p>详细描述:  </p>
 * <p>创建时间: {DATE} {TIME}</p>
 *
 * @author 1578325085@qq.com
 * @version v1.0.0
 */
class QuickFragment:BaseFragment() {
    private lateinit var viewModel: LoginViewModel
    override fun getLayoutId(): Int = R.layout.fragment_quicklogin

    override fun initView(view: View) {

    }

    override fun initListener(view: View) {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.getPhone().observe(this, Observer {
            tvTitle.text = it
        })
        btnChangeLogin.setOnClickListener {

        }
    }

    override fun initData() {
        edPhone.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                viewModel.setPhone(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }
}