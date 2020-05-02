## Android 嵌套滑动机制
## 一 [Bottom Navigation](https://material.io/develop/android/components/bottom-navigation-view/)
BottomNavigationView创建底部的导航栏，使您轻松单击即可浏览和在顶级内容视图之间切换。
当您有3到5个顶级目标之间进行导航时，可以使用此模式。

1.创建 具有最多5个导航目标的 菜单资源（最多BottomNavigationView支持5个项目）。

2.将BottomNavigationView您的内容布置在下方。

3.将app:menu您的属性设置为BottomNavigationView菜单资源。

4.使用收听选择事件 setOnNavigationItemSelectedListener(...)。

典型的布局文件如下所示：
```

    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

  <!-- Main content -->

  <com.google.android.material.bottomnavigation.BottomNavigationView
      android:id="@+id/bottom_navigation"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:layout_gravity="bottom"
      android:background="@color/colorPrimary"
      app:itemIconTint="@color/white"
      app:itemTextColor="@color/white"
      app:menu="@menu/bottom_navigation_menu" />

</FrameLayout>
```
## [Tab Layout](https://material.io/develop/android/components/tab-layout/)