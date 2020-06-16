package com.android.androidstudy.fragment

import android.util.DisplayMetrics
import android.view.View
import androidx.fragment.app.Fragment
import com.android.androidstudy.R
import com.android.customview.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_third.*

/**
 * <p>描述:  </p>
 * <p>详细描述:  </p>
 * <p>创建时间: 2020/4/22 2:38 PM</p>
 *
 * @author 1578325085@qq.com
 * @version v1.0.0
 */
class ThirdFragment:BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_third

    override fun initView(view: View) {

    }

    override fun initListener(view: View) {
    }

    override fun initData() {
        tvScreenWidth.text = "屏幕宽度："+resources.displayMetrics.widthPixels;
        tvScreenHeight.text = "屏幕高度："+resources.displayMetrics.heightPixels;
    }
}