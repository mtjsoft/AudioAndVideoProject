package cn.mtjsoft.www.baseres.cache

import cn.mtjsoft.www.baseres.SpKeys

object SpManager {

    var isLogin
        get() = SpUtlils.getBoolean(SpKeys.isLogin, false)
        set(value) = SpUtlils.putBoolean(SpKeys.isLogin, value)

    var userId
        get() = SpUtlils.getInt(SpKeys.userId, -1)
        set(value) = SpUtlils.putInt(SpKeys.userId, value)

    var userPhone
        get() = SpUtlils.getString(SpKeys.userPhone, "")
        set(value) = SpUtlils.putString(SpKeys.userPhone, value)

    var userName
        get() = SpUtlils.getString(SpKeys.userName, "")
        set(value) = SpUtlils.putString(SpKeys.userName, value)

    var userInfo
        get() = SpUtlils.getString(SpKeys.userInfo, "")
        set(value) = SpUtlils.putString(SpKeys.userInfo, value)

    var privateKey
        get() = SpUtlils.getBytes(SpKeys.privateKey, ByteArray(0))
        set(value) = SpUtlils.putBytes(SpKeys.privateKey, value)

    var publicKey
        get() = SpUtlils.getBytes(SpKeys.publicKey, ByteArray(0))
        set(value) = SpUtlils.putBytes(SpKeys.publicKey, value)

    var imToken
        get() = SpUtlils.getString(SpKeys.im_token, "")
        set(value) = SpUtlils.putString(SpKeys.im_token, value)

    var token
        get() = SpUtlils.getString(SpKeys.token, "")
        set(value) = SpUtlils.putString(SpKeys.token, value)

    fun loginOut() {
        isLogin = false
        userId = -1
        imToken = ""
        token = ""
    }
}
