package com.android.androidstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.androidstudy.fragment.FirstFragment
import com.android.androidstudy.fragment.SecondFragment
import com.android.androidstudy.fragment.ThirdFragment
import com.android.androidstudy.utils.ScreenUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        statusBar.text = "状态栏高度："+ScreenUtil.getStatusBarHeight(this).toString()
//        hasNavigationBa.text = "是否有导航："+ScreenUtil.deviceHasNavigationBar().toString()
//        allScreen.text = "是否全面屏："+ScreenUtil.isAllScreenDevice(this).toString()
//        navigationBar.text = "是否显示导航："+ScreenUtil.isNavigationBarExist(this).toString()
//
//        refresh.setOnClickListener {
//            statusBar.text = "状态栏高度："+ScreenUtil.getStatusBarHeight(this).toString()
//            hasNavigationBa.text = "是否有导航："+ScreenUtil.deviceHasNavigationBar().toString()
//            allScreen.text = "是否全面屏："+ScreenUtil.isAllScreenDevice(this).toString()
//            navigationBar.text = "是否显示导航："+ScreenUtil.isNavigationBarExist(this).toString()
//        }
        viewPage.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> FirstFragment()
                    1 -> SecondFragment()
                    else -> ThirdFragment()
                }
            }
        }
        TabLayoutMediator(bottomTabs, viewPage,
            TabLayoutMediator.TabConfigurationStrategy { tab,position ->
                when(position){
                    0->tab.text = "First"
                    2->tab.text = "Second"
                    else -> tab.text = "Third"
                }
            }).attach()
    }


}
