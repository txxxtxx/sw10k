package com.sw10k.zhe.other.reptile.csdn;

import com.sw10k.zhe.other.reptile.bean.CommonException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class DataUtil {
    /**
     * 获取HTML数据
     *
     * @param urlStr url地址
     * @return
     * @throws CommonException
     */
    public static String doGet2(String urlStr) throws CommonException {
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    sb.append(new String(buf, 0, len, "UTF-8"));
                }
                is.close();
            } else {
                throw new CommonException("访问网络失败00");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("访问网络失败11");
        }
        return sb.toString();
    }

    public static String doGet(String urlStr) throws CommonException {
        Connection conn = Jsoup.connect(urlStr);
        conn.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/    20100101 Firefox/32.0");
        Document doc = null;
        try {
            doc = conn.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc.toString();
    }
}
