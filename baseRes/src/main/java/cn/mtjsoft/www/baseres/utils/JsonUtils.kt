package cn.mtjsoft.www.baseres.utils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object JsonUtils {

    val moshi = Moshi.Builder().build()

    inline fun <reified T> toJson(bean: T): String {
        return moshi.adapter(T::class.java).toJson(bean)
    }

    inline fun <reified T> toJsonSerializeNulls(bean: T): String {
        return moshi.adapter(T::class.java).serializeNulls().toJson(bean)
    }

    inline fun <reified T> fromJson(string: String): T? {
        return moshi.adapter(T::class.java).fromJson(string)
    }

    inline fun <reified T> listFromJson(string: String): List<T>? {
        val listTypeAdapter = Types.newParameterizedType(List::class.java, T::class.java)
        val jsonAdapter: JsonAdapter<List<T>> =
            moshi.adapter<List<T>>(listTypeAdapter)
        return jsonAdapter.fromJson(string)
    }

    inline fun <reified K, reified V> mapFromJson(string: String): Map<K, V>? {
        val listTypeAdapter = Types.newParameterizedType(Map::class.java, K::class.java, V::class.java)
        val jsonAdapter: JsonAdapter<Map<K, V>> =
            moshi.adapter<Map<K, V>>(listTypeAdapter)
        return jsonAdapter.fromJson(string)
    }

    inline fun <reified T> fromJsonNullSafe(string: String): T? {
        return moshi.adapter(T::class.java).nullSafe().fromJson(string)
    }
}
