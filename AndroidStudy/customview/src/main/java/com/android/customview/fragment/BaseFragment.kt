package com.android.customview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * <p>描述: Base Fragment </p>
 * <p>详细描述:  </p>
 * <p>创建时间: 2020/4/22 2:40 PM</p>
 *
 * @author 1578325085@qq.com
 * @version v1.0.0
 */
abstract class BaseFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contentView = inflater.inflate(getLayoutId(), container, false)
        initView(contentView)
        return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    abstract fun getLayoutId(): Int
    abstract fun initView(view: View)
    abstract fun initListener(view: View)
    abstract fun initData()

}