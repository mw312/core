package com.tomasky.core.util;


import java.util.Date;
import java.util.Random;

/**
 * 随机数工具类
 *
 * @author mo
 */
public class RandomUtil {
    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Random random = new Random();

    /**
     * 返回随机ID.
     */
    public static long randomId() {
        return random.nextLong();
    }

    /**
     * 返回随机名称, prefix字符串+5位随机数字.
     */
    public static String randomByPrefix(String prefix, int num) {
        return prefix + random.nextInt((int) Math.pow(10, num));
    }

    /**
     * 生成32位编码，格式为：yyyyMMddHHmmssSSS + 15 随机数
     */
    public static synchronized String getBusNumber() {
        return getBusNumber(15);
    }

    /**
     * 生成指定位数的编码，格式为：yyyyMMddHHmmssSSS
     */
    public static synchronized String getBusNumber(int num) {
        StringBuffer result = new StringBuffer();
        int ID_BYTES = num;
        // 取时间
        result.append(DateUtil.format(new Date(), "yyyyMMddHHmmssSSS"));
        // 取10位随机数
        for (int i = 0; i < ID_BYTES; i++) {
            result = result.append(random.nextInt(10));
        }
        return result.toString();
    }

    /**
     * 获取随机字符
     *
     * @param select 类型: 0:a-z 1:A-Z 2:0-9 其他:随机
     * @return
     */
    public static synchronized Character getRandomChar(int select) {
        int tempval;
        if (select == 0) {
            tempval = (int) ((float) 'a' + random.nextFloat() * (float) ('z' - 'a'));
        } else if (select == 1) {
            tempval = (int) ((float) 'A' + random.nextFloat() * (float) ('Z' - 'A'));
        } else if (select == 2) {
            tempval = (int) ((float) '0' + random.nextFloat() * (float) ('9' - '0'));
        } else {
            tempval = allChar.charAt(random.nextInt(allChar.length()));
        }
        return new Character((char) tempval);
    }

    /**
     * 根据传入的字符串获取随机字符
     *
     * @return
     */
    public static synchronized Character getRandomChar(String str) {
        int tempval = str.charAt(random.nextInt(str.length()));
        return new Character((char) tempval);
    }

    /**
     * 获取指定长度随机字符串
     *
     * @param length
     * @return
     */
    public static synchronized String getRandomString(int length) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int randomSelect = (int) (random.nextFloat() * 100) % 2;
            buffer.append(getRandomChar(randomSelect));
        }
        return buffer.toString();
    }

    /**
     * 获取指定最大长度随机数
     *
     * @param maxLength
     * @return
     */
    public static synchronized long getRandomNumber(int maxLength) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < maxLength; i++) {
            if (i == 0) {
                buffer.append(getRangeRandom(1, 9));
            } else {
                buffer.append(getRandomChar(2));
            }
        }
        return Long.parseLong(buffer.toString());
    }

    /**
     * 获取指定长度随机字符和数字结合串
     *
     * @param length
     * @return
     */
    public static synchronized String getRandomStringAndNumber(int length) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int randomSelect = (int) (random.nextFloat() * 100) % 3;
            buffer.append(getRandomChar(randomSelect));
        }
        return buffer.toString();
    }

    /**
     * 根据传入的字符串随机组合指定长度的字符串
     *
     * @param length
     * @return
     */
    public static synchronized String getRandomStringByStr(int length, String str) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append(getRandomChar(str));
        }
        return buffer.toString();
    }

    /**
     * 取一定范围的随机数
     *
     * @param start
     * @param end
     * @return
     */
    public static int getRangeRandom(int start, int end) {
        if (start > end) {
            return -1;
        }
        return (int) (Math.random() * (end - start + 1)) + start;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
//        System.out.println(randomByPrefix("test", 2));
        System.out.println(getRandomNumber(8));
    }
}
