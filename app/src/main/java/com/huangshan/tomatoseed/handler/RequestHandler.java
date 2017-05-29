package com.huangshan.tomatoseed.handler;

import com.huangshan.tomatoseed.bean.SearchResult;
import com.huangshan.tomatoseed.bean.SeedDetails;
import com.huangshan.tomatoseed.utils.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static com.huangshan.tomatoseed.utils.Tools.debug;

/**
 * Author(s): danyun
 * Date: 2017/5/29
 */
public class RequestHandler {

    /**
     * 按照关键字搜索结果
     */
    public static List<SearchResult> searchKeyword(String keyword) {
        return null;
    }

    /**
     * 根据搜索结果获取详情
     */
    public static SeedDetails getDetails(String url) {
        return null;
    }

    private String fetchHtml(String keyword) {
        return doGetRequest("https://btso.pw/search" + keyword);
    }

    private String doGetRequest(String urlAddress) {
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            checkHttps(urlConnection);
            urlConnection.addRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
            urlConnection.setDoInput(true);
            int responseCode = urlConnection.getResponseCode();
            debug("code: " + responseCode);
            if (responseCode == 200) {
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                // TODO: 2017/5/29 return String result
            }
        } catch (IOException e) {
            Tools.warn(e);
        }
        return null;
    }

    private void checkHttps(HttpURLConnection urlConnection) {
        if (urlConnection instanceof HttpsURLConnection) {
            SSLContext sslContext = getSslContext();
            if (sslContext == null) {
                throw new IllegalStateException("不是 HTTPS 地址");
            }
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            ((HttpsURLConnection) urlConnection).setSSLSocketFactory(socketFactory);
        }
    }

    private SSLContext getSslContext() {
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new TrustManager[]{new X509()}, new SecureRandom());
            return context;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            Tools.warn(e);
            return null;
        }
    }

    private class X509 implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
