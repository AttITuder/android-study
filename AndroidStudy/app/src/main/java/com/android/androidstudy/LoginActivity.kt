package com.android.androidstudy

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.android.androidstudy.fragment.FirstFragment
import com.android.androidstudy.fragment.SecondFragment
import kotlinx.android.synthetic.main.activity_login.*

/**
 * <p>描述:  </p>
 * <p>详细描述:  </p>
 * <p>创建时间: {DATE} {TIME}</p>
 *
 * @author 1578325085@qq.com
 * @version v1.0.0
 */
class LoginActivity:AppCompatActivity() {
    private val fragments = ArrayList<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        fragments.add(FirstFragment())
        fragments.add(SecondFragment())
        viewPage.adapter = MainAdapter(this, fragments)

    }

}