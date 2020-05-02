package com.android.androidstudy

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @describe
 * @author dpc
 * @date 2020-03-03 19:24,
 */
class MainAdapter2(activity: FragmentActivity, fragments: ArrayList<Fragment>) :
    FragmentStateAdapter(activity) {

    private val fragments: ArrayList<Fragment> = fragments
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}