package com.tomasky.core.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * 获取资源文件属性值
 *
 * @author mo
 */
public class ResourceBundleUtil {
    protected static ResourceBundle bundle = ResourceBundle.getBundle("config");

    public static String getString(String key) {
        try {
            return bundle.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    public static int getInt(String key) {
        return Integer.parseInt(getString(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(getString(key));
    }

    public static String getString(String key, String resource) {
        bundle = ResourceBundle.getBundle(resource);
        try {
            return bundle.getString(key);
        } catch (Exception e) {
        }
        return key;
    }

    public static int getInt(String key, String resource) {
        return Integer.parseInt(getString(key, resource));
    }

    public static boolean getBoolean(String key, String resource) {
        return Boolean.parseBoolean(getString(key, resource));
    }

    public static String getString(String key, Object... params) {
        return getString(key, null, params);
    }

    /**
     * @param key      需要获得的key值
     * @param resource 资源文件名称
     * @return 返回value值
     */
    @SuppressWarnings("static-access")
    public static String getString(String key, String resource, Object... params) {
        ResourceBundle bundles = resource == null ? bundle : ResourceBundle.getBundle(resource);
        try {
            String value = bundles.getString(key);
            MessageFormat form = new MessageFormat(value);
            return form.format(value, params);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static void main(String[] args) {
        System.out.println(ResourceBundleUtil.getString("fcXmlDir"));
    }

}
