package com.tomasky.core.util.string;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5加密以及sha加密
 *
 * @author mo
 */
public final class EncryptUtil {

    /**
     * MD5加密
     *
     * @param input 要加密的字符串
     * @return
     */
    public static String encryptByMD5(String input) {
        return encryptByType(input, "MD5");
    }

    /**
     * SHA256加密
     *
     * @param input 要加密的字符串
     * @return
     */
    public static String encryptBySHA(String input) {
        return encryptByType(input, "SHA-256");
    }

    /**
     * SHA256加密
     *
     * @param input 要加密的字符串
     * @return
     */
    private static String encryptByType(String input, String type) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(type);
            md.update(input.getBytes());

            byte byteData[] = md.digest();

            // 二进制转换为十六进制
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
