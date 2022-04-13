package cn.mtjsoft.www.baseres.extentions

import cn.mtjsoft.www.baseres.utils.ByteUtils
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.toMD5(): String {
    return try {
        // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
        val messageDigest = MessageDigest.getInstance("MD5")
        // 输入的字符串转换成字节数组
        val inputByteArray: ByteArray = toByteArray()
        // inputByteArray是输入字符串转换得到的字节数组
        messageDigest.update(inputByteArray)
        // 转换并返回结果，也是字节数组，包含16个元素
        val resultByteArray = messageDigest.digest()
        // 字符数组转换成字符串返回
        ByteUtils.bytesToHex(resultByteArray)
    } catch (e: NoSuchAlgorithmException) {
        ""
    }
}