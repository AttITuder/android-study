package com.android.customview.scroll

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.NestedScrollingParent2
import androidx.fragment.app.Fragment

/**
 * <p>描述:  </p>
 * <p>详细描述:  </p>
 * <p>创建时间: 2020/4/21 4:48 PM</p>
 *
 * @author 1578325085@qq.com
 * @version v1.0.0
 */
class DampLayout(context: Context) : FrameLayout(context),NestedScrollingParent2 {

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        TODO("Not yet implemented")
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        TODO("Not yet implemented")
    }
}