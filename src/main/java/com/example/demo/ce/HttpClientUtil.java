package com.example.demo.ce;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 利用HttpClient进行post请求的工具类
 * @ClassName: HttpClientUtil 
 * @Description: TODO
 * @author Devin <xxx> 
 * @date 2017年2月7日 下午1:43:38 
 *  
 */
public class HttpClientUtil {
    @SuppressWarnings("resource")
    public static HttpResponse doPost(String url,String cookie, StringEntity se, String charset, String token){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpPost.addHeader("Authorization", token);
            httpPost.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
            httpPost.addHeader("cookie", cookie);
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            return response;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

}