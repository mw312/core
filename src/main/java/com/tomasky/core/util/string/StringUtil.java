package com.tomasky.core.util.string;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtil {

    /**
     * java截取中英文混合字符串 等宽显示
     *
     * @param text
     * @param length
     * @param endWith
     * @return
     */
    public static String subString(String text, int length, String endWith) {
        if (text != null) {
            int textLength = text.length();
            int byteLength = 0;
            StringBuffer returnStr = new StringBuffer();
            for (int i = 0; i < textLength && byteLength < length * 2; i++) {
                String str_i = text.substring(i, i + 1);
                if (str_i.getBytes().length == 1) {// 英文
                    byteLength++;
                } else {// 中文
                    byteLength += 2;
                }
                returnStr.append(str_i);
            }
            try {
                if (byteLength < text.getBytes("GBK").length && endWith != null) {// getBytes("GBK")每个汉字长2，getBytes("UTF-8")每个汉字长度为3
                    returnStr.append(endWith);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return returnStr.toString();
        } else {
            return "";
        }
    }

    public static String subString(String text, int length) {
        return subString(text, length, null);
    }

    /**
     * 获取中英文混合字符的长度，中文按2个长度单位计算
     *
     * @param str
     * @return
     */
    public static int getLength(String str) {
        int len = 0;
        int textLength = str.length();
        for (int i = 0; i < textLength; i++) {
            String s = str.substring(i, i + 1);
            len += s.getBytes().length == 1 ? 1 : 2;
        }
        return len;
    }

    public static boolean isEQLength(String str, int lenth) {
        return getLength(str) == lenth;
    }

    public static boolean isGELength(String str, int maxLenth) {
        return getLength(str) >= maxLenth;
    }

    public static boolean isGTLength(String str, int maxLenth) {
        return getLength(str) > maxLenth;
    }

    public static boolean isLELength(String str, int minLenth) {
        return getLength(str) <= minLenth;
    }

    public static boolean isLTLength(String str, int minLenth) {
        return getLength(str) < minLenth;
    }

    /**
     * 格式化金额
     *
     * @param money
     * @param scale 表示需要保留到小数点以后几位。
     * @return
     */
    public static String formatMoneyToString(Double money, int scale) {
        String result = "0";
        StringBuffer scaleStr = new StringBuffer("##.#");
        while (scale > 1) {
            scaleStr.append("#");
            scale--;
        }
        if (null != money) {
            DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
            df.applyPattern(scaleStr.toString());
            result = df.format(money);
            if (money % 1.0 == 0) {
                result = String.valueOf((long) (double) (money));
            }
        }
        return result;
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符
     *
     * @param html
     * @return
     */
    public static String replaceHtml(String html) {
        String dest = "";
        if (html != null) {
            dest = html.replaceAll("\\&[a-zA-Z]{0,9};", "").replaceAll("<[^>]*>", "\n\t");
        }
        return dest;
    }

    /**
     * 对长字符串进行截短
     *
     * @param str
     * @param max
     * @param truncation 默认 ...
     * @return
     */
    public static String truncate(String str, Integer max, String truncation) {
        if (null == str) {
            return "";
        }
        if (str.length() > max) {
            truncation = truncation == null ? "..." : truncation;
            return str.substring(0, max) + truncation;
        }
        return str;
    }

    public static void main(String[] as) {
        Double d = 1.95569E2;
        System.out.println(formatMoneyToString(d, 2));
//		System.out.println(replaceBlank(replaceHtml(" 大理a双廊别想1那只大象-海景房  <font color=red>现付</font>")));
    }

}
