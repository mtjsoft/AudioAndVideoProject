package cn.mtjsoft.www.baseres.cache

interface ISp {

    fun putString(key: String, value: String)

    fun getString(key: String, default: String): String

    fun putBoolean(key: String, value: Boolean)

    fun getBoolean(key: String, default: Boolean): Boolean

    fun putInt(key: String, value: Int)

    fun getInt(key: String, default: Int): Int

    fun putLong(key: String, value: Long)

    fun getLong(key: String, default: Long): Long

    fun putBytes(key: String, value: ByteArray)

    fun getBytes(key: String, default: ByteArray): ByteArray
}
