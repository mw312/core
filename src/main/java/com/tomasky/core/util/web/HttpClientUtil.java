package com.tomasky.core.util.web;

import com.google.common.collect.Lists;
import com.tomasky.core.util.string.EncodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * http帮助类
 *
 * @author mo
 */
public class HttpClientUtil {

    protected static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static BasicCookieStore setBasicCookieStore(Map<String, String> requestHeader) {
        BasicCookieStore cookieStore = new BasicCookieStore();
        return cookieStore;
    }

    private static RequestConfig setRequestConfig(String proxyIp, Integer proxyPort) {
        RequestConfig.Builder builder = RequestConfig.custom()
                .setConnectionRequestTimeout(40000)
                .setConnectTimeout(50000)
                .setSocketTimeout(60000)
                .setCookieSpec("mySpec");
        if (StringUtils.isNotBlank(proxyIp) && null != proxyPort) {
            HttpHost proxy = new HttpHost(proxyIp, proxyPort);
            builder.setProxy(proxy);
        }
        return builder.build();
    }

    public static CloseableHttpClient setHttpClient(BasicCookieStore cookieStore, String proxyIp, Integer proxyPort) {
        Registry<CookieSpecProvider> registry = RegistryBuilder.<CookieSpecProvider>create().build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieSpecRegistry(registry)
                .setDefaultRequestConfig(setRequestConfig(proxyIp, proxyPort))
                .setDefaultCookieStore(cookieStore)
                .build();
        return httpClient;
    }

    public static HttpGet setHttpGet(String url) {
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36");
        httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4");
        httpget.setHeader("Accept-Charset", "GB2312,UTF-8;q=0.7,*;q=0.7");
        return httpget;
    }

    private static HttpPost setHttpPost(String url) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36");
        httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4");
        httpPost.setHeader("Accept-Charset", "GB2312,UTF-8;q=0.7,*;q=0.7");
        return httpPost;
    }

    //-----------------get方式获取信息---------------------
    public static String getResponseInfoByGet(String url) {
        return getResponseInfoByGet(url, null);
    }

    public static String getResponseInfoByGet(String url, Map<String, String> requestHeader) {
        return getResponseInfoByGet(url, requestHeader, null, 0);
    }

    public static String getResponseInfoByGet(String url, Map<String, String> requestHeader, String proxyIp, Integer proxyPort) {
        BasicCookieStore cookieStore = setBasicCookieStore(requestHeader);
        CloseableHttpClient httpClient = setHttpClient(cookieStore, proxyIp, proxyPort);
        try {
            HttpGet httpget = setHttpGet(url);
            if (requestHeader != null && requestHeader.size() > 0) {
                for (Entry<String, String> entry : requestHeader.entrySet()) {
                    httpget.setHeader(entry.getKey(), entry.getValue());
                }
            }
            CloseableHttpResponse response = httpClient.execute(httpget);
            return EntityUtils.toString(response.getEntity(), Consts.UTF_8);
        } catch (SocketTimeoutException | ConnectTimeoutException | ConnectException e) {
            logger.info("get方式获取:" + url + "信息超时！", e);
        } catch (Exception e) {
            logger.error("get方式获取:" + url + "信息异常！", e);
        } finally {
            try {
                //关闭连接
                httpClient.close();
            } catch (IOException e) {
                logger.error("关闭连接失败！", e);
            }
        }
        return null;
    }

    //-----------------post方式获取网页信息---------------------
    public static String getResponseInfoByPost(String url) {
        return getResponseInfoByPost(url, Lists.newArrayList());
    }

    public static String getResponseInfoByPost(String url, List<NameValuePair> params) {
        return getResponseInfoByPost(url, params, null);
    }

    public static String getResponseInfoByPost(String url, List<NameValuePair> params, Map<String, String> requestHeader) {
        return getResponseInfoByPost(url, params, requestHeader, null, null);
    }

    public static String getResponseInfoByPost(String url, List<NameValuePair> params, Map<String, String> requestHeader, String proxyIp, Integer proxyPort) {
        return getResponseInfoByPost(url, params, null, requestHeader, proxyIp, proxyPort);
    }

    public static String getResponseInfoByPost(String url, String stringEntity) {
        return getResponseInfoByPost(url, stringEntity, null);
    }

    public static String getResponseInfoByPost(String url, String stringEntity, Map<String, String> requestHeader) {
        return getResponseInfoByPost(url, null, stringEntity, requestHeader, null, null);
    }

    public static String getResponseInfoByPost(String url, String stringEntity, Map<String, String> requestHeader, String proxyIp, Integer proxyPort) {
        return getResponseInfoByPost(url, null, stringEntity, requestHeader, proxyIp, proxyPort);
    }

    private static String getResponseInfoByPost(String url, List<NameValuePair> params, String stringEntity, Map<String, String> requestHeader, String proxyIp, Integer proxyPort) {
        BasicCookieStore cookieStore = setBasicCookieStore(requestHeader);
        CloseableHttpClient httpClient = setHttpClient(cookieStore, proxyIp, proxyPort);
        try {
            HttpPost httpPost = setHttpPost(url);
            if (requestHeader != null && requestHeader.size() > 0) {
                for (Entry<String, String> entry : requestHeader.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            if (params != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
            } else if (StringUtils.isNotBlank(stringEntity)) {
                httpPost.setEntity(new StringEntity(stringEntity, Consts.UTF_8));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity(), Consts.UTF_8);
        } catch (SocketTimeoutException | ConnectTimeoutException | ConnectException e) {
            logger.info("post方式获取:" + url + "信息超时！", e);
        } catch (Exception e) {
            logger.error("post方式获取:" + url + "信息异常！", e);
        } finally {
            try {
                //关闭连接
                httpClient.close();
            } catch (IOException e) {
                logger.error("关闭连接失败！", e);
            }
        }
        return null;
    }

    public static String getWebserviceResponseStrByPost(String url, String soapRequestData, Map<String, String> requestHeader) {
        return getWebserviceResponseStrByPost(url, soapRequestData, requestHeader, null, null);
    }

    /**
     * Soap请求
     *
     * @param url
     * @param soapRequestData
     * @param requestHeader
     * @return
     */
    public static String getWebserviceResponseStrByPost(String url, String soapRequestData, Map<String, String> requestHeader, String proxyIp, Integer proxyPort) {
        String result = "";
        BasicCookieStore cookieStore = setBasicCookieStore(requestHeader);
        CloseableHttpClient httpClient = setHttpClient(cookieStore, proxyIp, proxyPort);
        try {
            HttpPost httpPost = setHttpPost(url);
            if (requestHeader != null && requestHeader.size() > 0) {
                for (Entry<String, String> entry : requestHeader.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            /*把Soap请求数据添加到PostMethod*/
            byte[] b = soapRequestData.getBytes(Consts.UTF_8);
            InputStream is = new ByteArrayInputStream(b, 0, b.length);
            InputStreamEntity requestEntity = new InputStreamEntity(is, b.length);
            httpPost.setEntity(requestEntity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
        } catch (Exception e) {
            logger.error("getSoapWebserviceResponseStrByPost获取:" + url + "信息失败！参数:" + soapRequestData, e);
        } finally {
            try {
                //关闭连接
                httpClient.close();
            } catch (IOException e) {
                logger.error("关闭连接失败！", e);
            }
        }
        return result;
    }

    public static String getImageBase64EncoderByGet(String imageUrl) {
        return getImageBase64EncoderByGet(imageUrl, null);
    }

    public static String getImageBase64EncoderByGet(String imageUrl, Map<String, String> requestHeader) {
        return getImageBase64EncoderByGet(imageUrl, requestHeader, null, null);
    }

    public static String getImageBase64EncoderByGet(String imageUrl, Map<String, String> requestHeader, String proxyIp, Integer proxyPort) {
        BasicCookieStore cookieStore = setBasicCookieStore(requestHeader);
        CloseableHttpClient httpClient = setHttpClient(cookieStore, proxyIp, proxyPort);
        try {
            HttpGet httpget = HttpClientUtil.setHttpGet(imageUrl);
            if (requestHeader != null && requestHeader.size() > 0) {
                for (Entry<String, String> entry : requestHeader.entrySet()) {
                    httpget.setHeader(entry.getKey(), entry.getValue());
                }
            }
            CloseableHttpResponse response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            InputStream inStream = entity.getContent();
            byte[] data = readInputStream(inStream);
            // 返回Base64编码过的字节数组字符串
            return EncodeUtil.encodeBase64(data);
        } catch (SocketTimeoutException | ConnectTimeoutException | ConnectException e) {
            logger.info("get方式获取:" + imageUrl + "信息超时！", e);
        } catch (Exception e) {
            logger.error("get方式获取:" + imageUrl + "信息异常！", e);
        } finally {
            try {
                //关闭连接
                httpClient.close();
            } catch (IOException e) {
                logger.error("关闭连接失败！", e);
            }
        }
        return null;
    }

    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

}
