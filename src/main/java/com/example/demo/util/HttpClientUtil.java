package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.example.demo.model.po.TestPo;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Description: HttpClient工具类
 */
@Log4j2
public class HttpClientUtil {

//    header中的权限
    private static String CONTENT_TYPE = "application/json";
//    header中的token key
    public static final String JWT_HEADER_KEY = "Authorization";
    //    header中的权限
    private static String CONTENT = "application/json;charset=utf8";
//    字符集
    private static String CHARSET = "UTF-8";

    public static String doGet(String url) {
        log.info(url);
        String response = "";
        url = url.replaceAll(" ","%20");
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        创建get请求
        HttpGet httpGet = new HttpGet(url);
        log.info(httpGet);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(50000).setConnectionRequestTimeout(1000).setSocketTimeout(120000).build();
        httpGet.setConfig(config);
        httpGet.setHeader("Content-Type", CONTENT);
//        设置token值
//        httpGet.setHeader(JWT_HEADER_KEY, JwtUtil.GENERIC_TOKEN);
        // 响应模型
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
//            设置字符集
            response = EntityUtils.toString(entity, CHARSET);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            IOUtils.close(httpResponse);
        }
        return response;
    }

    public static String doPost(String url, TestPo testPo) {
        log.info(url);
        String httpResponse1 = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        String jsonString  = JSON.toJSONString(testPo);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", CONTENT_TYPE);
//        设置token值
//        httpGet.setHeader(JWT_HEADER_KEY, JwtUtil.GENERIC_TOKEN);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity  = response.getEntity();

            httpResponse1=EntityUtils.toString(responseEntity, CHARSET);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            IOUtils.close(response);
        }
        return httpResponse1;
    }
}
