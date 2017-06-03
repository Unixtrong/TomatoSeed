package com.huangshan.tomatoseed.bean;

/**
 * Author(s): danyun
 * Date: 2017/5/29
 */
public class SearchResult {
    private String mName;
    private String mUrl;

    public SearchResult(String name, String url) {
        mName = name;
        mUrl = url;
    }

    public String getName() {
        return mName;
    }

    public SearchResult setName(String name) {
        mName = name;
        return this;
    }

    public String getUrl() {
        return mUrl;
    }

    public SearchResult setUrl(String url) {
        mUrl = url;
        return this;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "mName='" + mName + '\'' +
                ", mUrl='" + mUrl + '\'' +
                '}';
    }
}
