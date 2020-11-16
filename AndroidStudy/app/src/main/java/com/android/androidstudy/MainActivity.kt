package com.android.androidstudy

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.androidstudy.fragment.FirstFragment
import com.android.androidstudy.fragment.SecondFragment
import com.android.androidstudy.fragment.ThirdFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*


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

//        assembleUserAgent()
//        var devicesid = getImeiOrMeidForQ(this)
//        Log.d("dpc","getImeiOrMeidForQ:"+devicesid)
//        val tm =
//            this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//       try {
//            val imei1 =  tm.getImei(0)
//            val imei2 = tm.getImei(1)
//            Log.d("dpc","imei1:"+imei1)
//            Log.d("dpc","imei2:"+imei2)
//        } catch (e: Exception) {
//        }

    }


    private fun assembleUserAgent(): String? {
        val sb = StringBuilder()
        sb.append("os/").append("Android").append(" ")
        sb.append("model/").append(Build.MODEL).append(" ")
        sb.append("brand/").append(Build.BRAND).append(" ")
        sb.append("sdk/").append(Build.VERSION.SDK_INT).append(" ")
        sb.append("devicesId/").append(Build.VERSION.SDK_INT).append(" ")
        //        sb.append("applicationID/").append(BuildConfig.APPLICATION_ID).append(" ");
        return sb.toString()
    }


    /**
     * 系统10的时候
     * 获取手机IMEI 或者MEID
     *
     * @return 手机IMEI
     */
    private fun getImeiOrMeidForQ(context: Context): String? {
        Log.d("dpc","getImeiOrMeidForQ:")
        try {
            val manager =
                context.getSystemService(Activity.TELEPHONY_SERVICE) as TelephonyManager
            if (manager != null) {
                return Settings.System.getString(
                    context.contentResolver,
                    Settings.Secure.ANDROID_ID
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return null
    }


}
