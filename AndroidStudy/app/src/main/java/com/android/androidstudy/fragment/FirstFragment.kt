package com.android.androidstudy.fragment

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.androidstudy.R
import com.android.customview.fragment.BaseFragment
import com.android.customview.recyclerview.RecyLiineDecoration
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_first.*
import kotlin.random.Random

/**
 * <p>描述:  </p>
 * <p>详细描述:  </p>
 * <p>创建时间: 2020/4/22 2:38 PM</p>
 *
 * @author 1578325085@qq.com
 * @version v1.0.0
 */
class FirstFragment : BaseFragment() {
    /**
     * RV适配器
     */
    private val simpleAdapter by lazy {
        val data = ArrayList<String>()
        var i = 0
        while (i < 100) {
            data.add(Random.nextLong().toString())
            i++
        }
        SimpleAdapter().apply {
            addData(data)
        }
    }


     private val data: ArrayList<String>
        get() {
            var list = ArrayList<String>()
            var i = 0
            while (i < 100) {
                list.add(Random.nextLong().toString())
            }
            return list
        }


    override fun getLayoutId(): Int = R.layout.fragment_first

    override fun initView(view: View) {
    }

    override fun initListener(view: View) {
        recycler.adapter = simpleAdapter
//        recycler.addItemDecoration(RecyLiineDecoration(context, LinearLayoutManager.VERTICAL))
        recycler.layoutManager = LinearLayoutManager(context)
    }

    override fun initData() {
    }


    inner class SimpleAdapter() : BaseQuickAdapter<String, BaseViewHolder>(
        R.layout.simple_list_item_activated_1
    ) {
        override fun convert(holder: BaseViewHolder, item: String) {
            holder.setText(R.id.text1, item)
        }
    }
}