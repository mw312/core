package com.tomasky.core.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用校验方式
 *
 * @author mo
 */
public class CommonValidateUtill {

    private static Map<String, Map<String, String>> regexMap = new HashMap<String, Map<String, String>>();
    //中文
    public final static String CHINESE = "CHINESE";
    //英文
    public final static String ENGLISH = "ENGLISH";
    //数字
    public final static String NUMBER = "NUMBER";
    //整数
    public final static String INTEGE = "INTEGER";
    //正整数
    public final static String NATURALINTEGE = "NATURAL_INTEGER";
    //负整数
    public final static String NEGATIVEINTEGE = "NEGATIVE_INTEGER";
    //日期10位YYYY-MM-DD
    public final static String DATE_YYYYMMDD = "yyyy-mm-dd";
    // 日期19位YYYY-MM-DD HH:MM:SS
    public final static String DATE_YYYYMMDD_HHMMSS = "yyyy-mm-dd hh:mm:ss";
    //email
    public final static String EMAIL = "EMAIL";
    //mobile
    public final static String MOBILE = "MOBILE";
    //证件号
    public final static String IDCARD = "IDCARD";
    //用户账号
    public final static String PASSWORD = "password";
    //用户账号
    public final static String USERCODE = "USERCODE";
    //联系电话
    public final static String CONTACT = "CONTACT";

    /**
     * 初始化数据
     */
    static {
        // 中文
        HashMap<String, String> chineseMap = new HashMap<String, String>();
        chineseMap.put("key", "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$");
        chineseMap.put("value", "请输入中文！");
        regexMap.put(CHINESE, chineseMap);

        // 英文
        HashMap<String, String> englishMap = new HashMap<String, String>();
        englishMap.put("key", "^[a-zA-Z]*");
        englishMap.put("value", "请输入英文！");
        regexMap.put(ENGLISH, englishMap);

        // 数字
        HashMap<String, String> numMap = new HashMap<String, String>();
        numMap.put("key", "^([+-]?)\\d*\\.?\\d+$");
        numMap.put("value", "请输入数字！");
        regexMap.put(NUMBER, numMap);

        // 整数
        HashMap<String, String> integeMap = new HashMap<String, String>();
        integeMap.put("key", "^-?[1-9]\\d*$");
        integeMap.put("value", "请输入整数！");
        regexMap.put(INTEGE, integeMap);

        // 正整数
        HashMap<String, String> intege1Map = new HashMap<String, String>();
        intege1Map.put("key", "^[1-9]\\d*$");
        intege1Map.put("value", "请输入正整数！");
        regexMap.put(NATURALINTEGE, intege1Map);

        // 负整数
        HashMap<String, String> intege2Map = new HashMap<String, String>();
        intege2Map.put("key", "^-[1-9]\\d*$");
        intege2Map.put("value", "请输入负整数！");
        regexMap.put(NEGATIVEINTEGE, intege2Map);

        // 日期10位YYYY-MM-DD
        HashMap<String, String> date10Map = new HashMap<String, String>();
        date10Map.put("key", "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$");
        date10Map.put("value", "请输入正确的日期格式(yyyy-MM-dd)！");
        regexMap.put(DATE_YYYYMMDD, date10Map);

        // 日期19位yyyy-MM-dd HH:mm:ss
        HashMap<String, String> date19Map = new HashMap<String, String>();
        date19Map.put("key", "[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s+(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$");
        date19Map.put("value", "请输入正确的日期格式(yyyy-MM-dd HH:mm:ss)！");
        regexMap.put(DATE_YYYYMMDD_HHMMSS, date19Map);

        // email
        HashMap<String, String> emailMap = new HashMap<String, String>();
        emailMap.put("key", "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
        emailMap.put("value", "请输入正确的email！");
        regexMap.put(EMAIL, emailMap);

        // 用户账号
        HashMap<String, String> userCodeMap = new HashMap<String, String>();
        userCodeMap.put("key", "^[\u4e00-\u9fa5A-Za-z0-9-_]{5,20}$");
        userCodeMap.put("value", "请输入正确的用户账号！");
        regexMap.put(USERCODE, userCodeMap);

        // password
        HashMap<String, String> passwordMap = new HashMap<String, String>();
        passwordMap.put("key", "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]){6,20}$");
        passwordMap.put("value", "请输入正确的密码！");
        regexMap.put(PASSWORD, passwordMap);

        // 手机号
        HashMap<String, String> mobileMap = new HashMap<String, String>();
        mobileMap.put("key", "^(13|14|15|18|17)[0-9]{9}$");
        mobileMap.put("value", "请输入正确的手机号码！");
        regexMap.put(MOBILE, mobileMap);

        // 身份证
        HashMap<String, String> idCardMap = new HashMap<String, String>();
        idCardMap.put("key", "^[0-9]{17}[0-9]{1}|^[0-9]{17}[X]{1}");
        idCardMap.put("value", "请输入正确的身份证号！");
        regexMap.put(IDCARD, idCardMap);

        // 联系电话
        HashMap<String, String> contactMap = new HashMap<String, String>();
        contactMap.put("key", "^([\\+][0-9]{1,3}[ \\.\\-])?([\\(]{1}[0-9]{2,6}[\\)])?([0-9 \\.\\-\\/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$");
        contactMap.put("value", "请输入正确的联系电话！");
        regexMap.put(CONTACT, contactMap);

    }

    /**
     * 校验字符数据合法性
     *
     * @param srcVal    待校验字符串
     * @param regexKey  校验规则
     * @param canNull   是否可为空,这个NULL包含空字符串
     * @param minLength
     * @param maxLength 第一个为最小长度，第二个为最大长度，不做限制时，无需定义该参数
     */
    public static final boolean checkStr(String srcVal, String regexKey, boolean canNull, int... length) {
        if (StringUtils.isBlank(srcVal)) {
            return canNull;
        }

        // 匹配校验格式,如果不输入KEY，则表示为不作校验
        if (StringUtils.isNotEmpty(regexKey)) {
            Map<String, String> regex = regexMap.get(regexKey);
            if (regex == null) {
                return false;
            }
            boolean isMatched = srcVal.matches(regex.get("key"));
            if (!isMatched) {
                return false;
            }
        }

        // 校验长度
        if (length != null) {
            if (length.length >= 1) {
                // 校验最小长度
                int len = length[0];
                if (srcVal.getBytes().length < len) {
                    // 如果小于最小长度
                    return false;
                }
            }
            if (length.length >= 2) {
                // 校验最大长度
                int len = length[1];
                if (srcVal.getBytes().length > len) {
                    // 如果小于最大长度
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(CommonValidateUtill.checkStr("！@&%…**&…【】[][sdf]1", PASSWORD, false));
    }
}
