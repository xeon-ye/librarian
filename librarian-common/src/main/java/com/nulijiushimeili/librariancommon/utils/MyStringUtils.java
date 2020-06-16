package com.nulijiushimeili.librariancommon.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/******************************************************
 * @Program: librarian-parent
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-16 16:57
 * @Desc: TODO
 ********************************************************/

@Slf4j
public class MyStringUtils {


    /**
     * 使用正则表达式的方式从 url 中提取 域名 或 ip:port
     * @param url
     * @return
     */
    public static String getDomainNameFromUrl(String url) {
        //使用正则表达式过滤，
        String re = "((http|ftp|https)://)(([a-zA-Z0-9._-]+)|([0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}))(([a-zA-Z]{2,6})|(:[0-9]{1,4})?)";
        String str = "";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(re);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        //若url==http://127.0.0.1:9040或www.baidu.com的，正则表达式表示匹配
        if (matcher.matches()) {
            str = url;
        } else {
            String[] split2 = url.split(re);
            if (split2.length > 1) {
                String substring = url.substring(0, url.length() - split2[1].length());
                str = substring;
            } else {
                str = split2[0];
            }
        }
        return str;
    }


    /**
     * 使用 URI 的方式 获取 url 中 的域名或 ip+port
     * @param url
     * @return
     */
    private static URI getIP(String url) throws URISyntaxException {
        URI uri = new URI(url);
        URI effectiveURI = null;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (Throwable var4) {
            effectiveURI = null;
        }
        return effectiveURI;
    }

//    public static void main(String[] args) throws URISyntaxException {
//        System.out.println(getDomainNameFromUrl("http://localhost:8080/system/verList"));
//
//    }
}
