package com.android.androidstudy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * <p>描述:  </p>
 * <p>详细描述:  </p>
 * <p>创建时间: {DATE} {TIME}</p>
 *
 * @author 1578325085@qq.com
 * @version v1.0.0
 */
class LoginViewModel :ViewModel() {
    //输入手机号
    private val phone = MutableLiveData<String>()

    fun getPhone(): MutableLiveData<String> {
        return phone
    }

    fun setPhone(phone: String?) {
        this.phone.postValue(phone)
    }
}