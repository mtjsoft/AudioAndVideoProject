package cn.mtjsoft.www.baseres.cache

import android.content.Context
import com.tencent.mmkv.MMKV
import com.tencent.mmkv.MMKVLogLevel

object MmkvManager {

    private const val KEY = "and_mmkv"

    internal val mmkv by lazy {
        MMKV.mmkvWithID(KEY, MMKV.MULTI_PROCESS_MODE)
    }

    fun init(context: Context, isDebug: Boolean) {
        val dir = context.filesDir.absolutePath + "/mmkv"
        MMKV.initialize(dir)
        MMKV.setLogLevel(if (isDebug) MMKVLogLevel.LevelDebug else MMKVLogLevel.LevelNone)
    }
}
