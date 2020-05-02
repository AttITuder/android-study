package com.android.androidstudy.utils

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.ViewGroup
import android.view.WindowManager
import java.lang.reflect.Method


/**
 * <p>描述:  </p>
 * <p>详细描述:  </p>
 * <p>创建时间: 2020/4/20 3:16 PM</p>
 *
 * @author 1578325085@qq.com
 * @version v1.0.0
 */
object ScreenUtil {
    /**
     * 判断设备是否存在NavigationBar(虚拟导航栏)
     *
     * @return true 存在, false 不存在
     */
    open fun deviceHasNavigationBar(): Boolean {
        var haveNav = false
        try {
            //1.通过WindowManagerGlobal获取windowManagerService
            // 反射方法：IWindowManager windowManagerService = WindowManagerGlobal.getWindowManagerService();
            val windowManagerGlobalClass =
                Class.forName("android.view.WindowManagerGlobal")
            val getWmServiceMethod: Method =
                windowManagerGlobalClass.getDeclaredMethod("getWindowManagerService")
            getWmServiceMethod.setAccessible(true)
            //getWindowManagerService是静态方法，所以invoke null
            val iWindowManager: Any = getWmServiceMethod.invoke(null)

            //2.获取windowMangerService的hasNavigationBar方法返回值
            // 反射方法：haveNav = windowManagerService.hasNavigationBar();
            val iWindowManagerClass: Class<*> = iWindowManager.javaClass
            val hasNavBarMethod: Method = iWindowManagerClass.getDeclaredMethod("hasNavigationBar")
            hasNavBarMethod.setAccessible(true)
            haveNav = hasNavBarMethod.invoke(iWindowManager) as Boolean
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return haveNav
    }

    /**
     * 是否是全面屏
     */
    open fun isAllScreenDevice(context: Context): Boolean {
        var mIsAllScreenDevice = false
        // 低于 API 21的，都不会是全面屏。。。
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return false
        }
        val windowManager =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        if (windowManager != null) {
            val display = windowManager.defaultDisplay
            val point = Point()
            display.getRealSize(point)
            val width: Float
            val height: Float
            if (point.x < point.y) {
                width = point.x.toFloat()
                height = point.y.toFloat()
            } else {
                width = point.y.toFloat()
                height = point.x.toFloat()
            }
            if (height / width >= 1.97f) {
                mIsAllScreenDevice = true
            }
        }
        return mIsAllScreenDevice
    }

    /**
     * 判断全面屏是否启用虚拟键盘
     */
    private const val NAVIGATION = "navigationBarBackground"

    open fun isNavigationBarExist(activity: Activity): Boolean {
        val vp = activity.window.decorView as ViewGroup
        if (vp != null) {
            for (i in 0 until vp.childCount) {
                vp.getChildAt(i).context.packageName
                if (vp.getChildAt(i).id != -1 && NAVIGATION == activity.resources
                        .getResourceEntryName(vp.getChildAt(i).id)
                ) {
                    return true
                }
            }
        }
        return false
    }


    /**
     * 状态栏高度
     */
    open fun getStatusBarHeight(context: Context): Int {
        var result: Int = 0;
        var resourceId: Int =
            context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}