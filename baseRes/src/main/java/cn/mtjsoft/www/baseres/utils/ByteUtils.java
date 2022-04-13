package cn.mtjsoft.www.baseres.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.arraycopy;

/**
 * byte 拼接处理工具类
 * TODO NSDK, xindun-soft-card中各有一个ByteUtil，需要整合
 *
 * @author Leo
 * @date 2016/12/15
 */
public class ByteUtils {
    /**
     * 将两个byte数组拼接成一个byte数组
     */
    public static byte[] concatByteArray(byte[] a, byte[] b) {
        byte[] data = new byte[a.length + b.length];
        arraycopy(a, 0, data, 0, a.length);
        arraycopy(b, 0, data, a.length, b.length);
        return data;
    }

    /**
     * 将三个byte数组拼接成一个byte数组
     */
    public static byte[] concatByteArray(byte[] a, byte[] b, byte[] c) {
        byte[] data = new byte[a.length + b.length + c.length];
        arraycopy(a, 0, data, 0, a.length);
        arraycopy(b, 0, data, a.length, b.length);
        arraycopy(c, 0, data, a.length + b.length, c.length);
        return data;
    }

    /**
     * 把一个整形int转换成byte数组 低位再前高位在后
     */
    public static byte[] intToByteArray(int i) {
        byte[] targets = new byte[4];
        targets[3] = (byte) (i & 0xFF);
        targets[2] = (byte) (i >> 8 & 0xFF);
        targets[1] = (byte) (i >> 16 & 0xFF);
        targets[0] = (byte) (i >> 24 & 0xFF);
        return targets;
    }

    /**
     * 把一个byte数组转换成 整形int，大端序
     */
    public static int byte4ToInt(byte[] bytes) {
        return byte4ToInt(bytes, 0);
    }

    /**
     * 截取byte数组 - 截取的长度
     */
    public static byte[] subBytesByCount(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }

    /**
     * 截取byte数组 - 末尾下标
     */
    public static byte[] subBytesFromBeginToEnd(byte[] src, int begin, int end) {
        byte[] bs = new byte[end - begin];
        System.arraycopy(src, begin, bs, 0, end - begin);
        return bs;
    }

    /**
     * 默认返回大写字母
     */
    public static String bytesToHex(byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        return bytesToHex(data, true);
    }

    /**
     * 经过测试，通过Integer.toHexString把byteArray转换为hex字符串速度最快
     *
     * @param capital 是否大写，true返回大写字符串
     */
    public static String bytesToHex(byte[] byteArray, boolean capital) {
        if (byteArray == null || byteArray.length == 0) {
            return null;
        }
        int length = byteArray.length;
        StringBuilder sb = new StringBuilder(length * 2);
        int i = 0;
        for (; i < byteArray.length && i + 3 < byteArray.length; i += 4) {
            //一次性取出4个字节组成一个32位的int，然后再转换为hex字符串，效率更高
            String hex = Integer.toHexString(byte4ToInt(byteArray, i));
            //高位补0
            for (int j = hex.length(); j < 8; ++j) {
                sb.append('0');
            }
            if (capital) {
                sb.append(hex.toUpperCase());
            } else {
                sb.append(hex);
            }
        }
        for (; i < byteArray.length; ++i) {
            String hex = Integer.toHexString(byteArray[i] & 0xff);
            if (hex.length() < 2) {
                sb.append('0');
            }
            if (capital) {
                sb.append(hex.toUpperCase());
            } else {
                sb.append(hex);
            }
        }
        return sb.toString();
    }

    private static int byte4ToInt(byte[] bytes, int from) {
        int b0 = bytes[from] & 0xFF;
        int b1 = bytes[from + 1] & 0xFF;
        int b2 = bytes[from + 2] & 0xFF;
        int b3 = bytes[from + 3] & 0xFF;
        return b0 << 24 | b1 << 16 | b2 << 8 | b3;
    }

    public static byte[] hexToBytes(String hexString) {
        if (hexString == null || "".equals(hexString)) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] data = new byte[length];
        int i = 0;
        while (i < length) {
            int pos = i * 2;
            data[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
            i++;
        }
        return data;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 数据16倍数填充
     */
    public static byte[] fillDataTo16(byte[] dataBytes) {
        byte[] byteEnd = "。".getBytes();
        int dataBytesLength = dataBytes.length;
        byte[] newByte = new byte[dataBytesLength + byteEnd.length];
        System.arraycopy(dataBytes, 0, newByte, 0, dataBytesLength);
        System.arraycopy(byteEnd, 0, newByte, dataBytesLength, byteEnd.length);
        byte[] result;
        int reminder = newByte.length % 16;
        int quotient = newByte.length / 16;
        if (reminder == 0) {
            result = newByte;
        } else {
            result = new byte[(quotient + 1) * 16];
            System.arraycopy(newByte, 0, result, 0, newByte.length);
            for (int i = newByte.length; i < result.length; i++) {
                result[i] = (byte) 0x00;
            }
        }
        return result;
    }

    /**
     * 解密时截取填充的数据
     */
    public static String subFillData(byte[] inData) {
        String data = new String(inData);
        if (data.contains("。")) {
            data = data.substring(0, data.lastIndexOf("。"));
        }
        return data;
    }

    /**
     * 将字节数组截取成指定大小的数据集合 长度不足指定大小的舍去
     *
     * @param bytes 要截取的数组
     * @param size  每段数组的长度
     */
    public static List<byte[]> subByte(byte[] bytes, int size) {
        List<byte[]> bs = new ArrayList<>();
        if (size > 0 && bytes != null) {
            int subLength = 0;
            while (subLength + size <= bytes.length) {
                byte[] b = Arrays.copyOfRange(bytes, subLength, subLength + size);
                bs.add(b);
                subLength += size;
            }
        }
        return bs;
    }

    /**
     * 将字节数组集合拼接成一个完整的数组
     *
     * @param bytes 待拼接的字节数组集合
     */
    public static byte[] getByte(List<byte[]> bytes) {
        if (bytes != null && bytes.size() > 0) {
            int totalLength = 0;
            for (int i = 0; i < bytes.size(); i++) {
                totalLength += bytes.get(i).length;
            }
            byte[] bt = new byte[totalLength];
            int positon = 0;
            for (int i = 0; i < bytes.size(); i++) {
                byte[] bs = bytes.get(i);
                System.arraycopy(bs, 0, bt, positon, bs.length);
                positon += bs.length;
            }
            return bt;
        }
        return null;
    }

    /**
     * 字节数据转short数组
     */
    public static short[] byteArray2ShortArray(byte[] b) {
        int len = b.length / 2;
        int index = 0;
        short[] re = new short[len];
        byte[] buf = new byte[2];
        for (int i = 0; i < b.length; ) {
            buf[0] = b[i];
            buf[1] = b[i + 1];
            short st = byteToShort(buf);
            re[index] = st;
            index++;
            i += 2;
        }
        return re;
    }

    /**
     * 字节数组转short
     */
    private static short byteToShort(byte[] b) {
        short s;
        // 最低位
        short s0 = (short) (b[0] & 0xff);
        short s1 = (short) (b[1] & 0xff);
        s1 <<= 8;
        s = (short) (s0 | s1);
        return s;
    }

    /**
     * short数组转字节数组
     */
    public static byte[] shortArray2ByteArray(short[] b) {
        byte[] bytes = new byte[b.length * 2];
        int index = 0;
        for (short st : b) {
            byte[] bt = shortToByte(st);
            bytes[index] = bt[0];
            bytes[index + 1] = bt[1];
            index += 2;
        }
        return bytes;
    }

    /**
     * short转字节数组
     */
    private static byte[] shortToByte(short number) {
        int temp = number;
        byte[] b = new byte[2];
        for (int i = 0; i < b.length; i++) {
            // 将最低位保存在最低位
            b[i] = Integer.valueOf(temp & 0xff).byteValue();
            // 向右移8位
            temp = temp >> 8;
        }
        return b;
    }
}
