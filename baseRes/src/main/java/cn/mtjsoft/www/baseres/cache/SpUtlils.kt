package cn.mtjsoft.www.baseres.cache

object SpUtlils : ISp {

    private val sp by lazy {
        MmkvManager.mmkv
    }

    override fun putString(key: String, value: String) {
        sp?.putString(key, value)
    }

    override fun getString(key: String, default: String): String {
        return sp?.getString(key, default) ?: default
    }

    override fun putBoolean(key: String, value: Boolean) {
        sp?.putBoolean(key, value)
    }

    override fun getBoolean(key: String, default: Boolean): Boolean {
        return sp?.getBoolean(key, default) ?: default
    }

    override fun putInt(key: String, value: Int) {
        sp?.putInt(key, value)
    }

    override fun getInt(key: String, default: Int): Int {
        return sp?.getInt(key, default) ?: default
    }

    override fun putLong(key: String, value: Long) {
        sp?.putLong(key, value)
    }

    override fun getLong(key: String, default: Long): Long {
        return sp?.getLong(key, default) ?: default
    }

    override fun putBytes(key: String, value: ByteArray) {
        sp?.putBytes(key, value)
    }

    override fun getBytes(key: String, default: ByteArray): ByteArray {
        return sp?.getBytes(key, default) ?: default
    }

}
