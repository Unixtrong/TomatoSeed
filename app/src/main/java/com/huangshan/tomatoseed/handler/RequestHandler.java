package com.huangshan.tomatoseed.handler;

import com.huangshan.tomatoseed.bean.SearchResult;
import com.huangshan.tomatoseed.bean.SeedDetails;
import com.huangshan.tomatoseed.utils.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static final String REGEX_TAG_SEARCH_RESULT = "https://btso.pw/magnet/detail/hash/";
    private static final String REGEX_TAG_DETAILS_TITLE = "https://btso.pw/magnet/detail/hash/";
    private static final String REGEX_TAG_DETAILS_MAGNET = "https://btso.pw/magnet/detail/hash/";
    private static final String REGEX_TAG_DETAILS_FILES = "https://btso.pw/magnet/detail/hash/";

    /**
     * 按照关键字搜索结果
     */
    public static List<SearchResult> searchKeyword(String keyword) {
        String html = fetchHtml("https://btso.pw/search/" + keyword);
        return parseSearchResult(html);
    }

    /**
     * 根据搜索结果获取详情
     */
    public static SeedDetails getDetails(String url) {
        String html = fetchHtml(url);
        Tools.debug("RequestHandler getDetails, html: " + html);
        return parseSeedDetails(html);
    }

    private static SeedDetails parseSeedDetails(String html) {
        StringReader stringReader = new StringReader(html);
        BufferedReader bufReader = new BufferedReader(stringReader);
        try {
            List<SearchResult> results = new ArrayList<>();
            String line;
            while ((line = bufReader.readLine()) != null) {
                if (line.trim().contains(REGEX_TAG_SEARCH_RESULT)) {
                    Pattern pattern = Pattern.compile("href=\"(.+)\".+title=\"(.+)\"");
                    Matcher matcher = pattern.matcher(html);
                    while (matcher.find() && matcher.groupCount() == 2) {
                        results.add(new SearchResult(matcher.group(2), matcher.group(1)));
                    }
                }
            }
            return null;
        } catch (IOException e) {
            Tools.warn(e);
        } finally {
            Tools.close(bufReader, stringReader);
        }
        return null;
    }

    private static List<SearchResult> parseSearchResult(String html) {
        StringReader stringReader = new StringReader(html);
        BufferedReader bufReader = new BufferedReader(stringReader);
        try {
            List<SearchResult> results = new ArrayList<>();
            String line;
            while ((line = bufReader.readLine()) != null) {
                if (line.trim().contains(REGEX_TAG_SEARCH_RESULT)) {
                    Pattern pattern = Pattern.compile("href=\"(.+)\".+title=\"(.+)\"");
                    Matcher matcher = pattern.matcher(html);
                    while (matcher.find() && matcher.groupCount() == 2) {
                        results.add(new SearchResult(matcher.group(2), matcher.group(1)));
                    }
                }
            }
            return results;
        } catch (IOException e) {
            Tools.warn(e);
        } finally {
            Tools.close(bufReader, stringReader);
        }
        return null;
    }

    private static String fetchHtml(String url) {
        return doGetRequest(url);
    }

    private static String doGetRequest(String urlAddress) {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            checkHttps(urlConnection);
            urlConnection.addRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
            urlConnection.setDoInput(true);
            int responseCode = urlConnection.getResponseCode();
            debug("code: " + responseCode);
            if (responseCode == 200) {
                inputStream = urlConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                String result = null;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line + "\n";
                }
                return result;
            }
        } catch (IOException e) {
            Tools.warn(e);
        } finally {
            Tools.close(bufferedReader, inputStreamReader, inputStream);
        }
        return null;
    }

    private static void checkHttps(HttpURLConnection urlConnection) {
        if (urlConnection instanceof HttpsURLConnection) {
            SSLContext sslContext = getSslContext();
            if (sslContext == null) {
                throw new IllegalStateException("不是 HTTPS 地址");
            }
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            ((HttpsURLConnection) urlConnection).setSSLSocketFactory(socketFactory);
        }
    }

    private static SSLContext getSslContext() {
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new TrustManager[]{new X509()}, new SecureRandom());
            return context;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            Tools.warn(e);
            return null;
        }
    }

    private static class X509 implements X509TrustManager {

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
