package com.example.demo.ce;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author limc
 * @date 2019/8/24
 */
@Component
public class FixedPrintTask {

    @Scheduled(cron = "0 0 1 * * ?")
    public void execute() throws IOException {
        System.out.println("ssss");
        ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();//用于存放表单数据.
        pairs.add(new BasicNameValuePair("email","limeichao163@163.com"));
        pairs.add(new BasicNameValuePair("passwd", "lmc@2017"));
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairs);
        HttpResponse httpResponse = HttpClientUtil.doPost("https://v.500ml.xyz/auth/login", null,  urlEncodedFormEntity, "utf-8", null);
        String s = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        System.out.println(decodeUnicode(s));
        String cookie = getCookie(httpResponse);
        ArrayList<BasicNameValuePair> pairs2 = new ArrayList<BasicNameValuePair>();//用于存放表单数据.
        UrlEncodedFormEntity urlEncodedFormEntity2 = new UrlEncodedFormEntity(pairs);
        HttpResponse httpResponse2 = HttpClientUtil.doPost("https://v.500ml.xyz/user/checkin", cookie,  urlEncodedFormEntity2, "utf-8", null);
        String s2 = EntityUtils.toString(httpResponse2.getEntity(), "UTF-8");
        System.out.println(decodeUnicode(s2));
    }

    static String getCookie(HttpResponse response) {
        Header[] lisy = response.getHeaders("Set-Cookie");
        AtomicReference<String> cookie = new AtomicReference<>("");
        Arrays.stream(lisy).forEach(it -> {
            HeaderElement[] elements = it.getElements();
            Arrays.stream(elements).forEach(obj -> {
                cookie.set(cookie + obj.toString() + ";");
            });
        });
        return cookie.toString();
    }



    public static String decodeUnicode(String unicode) {
        List<String> list = new ArrayList<String>();
        String reg= "\\\\u[0-9,a-f,A-F]{4}";
        Pattern p = Pattern.compile(reg);
        Matcher m=p.matcher(unicode);
        while (m.find()){
            list.add(m.group());
        }
        for (int i = 0, j = 2; i < list.size(); i++) {
            String code = list.get(i).substring(j, j + 4);
            char ch = (char) Integer.parseInt(code, 16);
            unicode = unicode.replace(list.get(i),String.valueOf(ch));
        }
        return unicode;
    }



}
