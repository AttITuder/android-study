package com.android.androidstudy

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * @describe
 * @author dpc
 * @date 2020-03-03 19:24,
 */
class MainAdapter(activity: FragmentActivity, fragments: ArrayList<Fragment>) :
    FragmentStatePagerAdapter(activity.supportFragmentManager) {

    private val fragments: ArrayList<Fragment> = fragments
    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size
//    override fun getItemCount(): Int = fragments.size
//
//    override fun createFragment(position: Int): Fragment = fragments[position]

}