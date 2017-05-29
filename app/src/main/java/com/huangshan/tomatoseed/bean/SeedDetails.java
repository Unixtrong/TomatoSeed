package com.huangshan.tomatoseed.bean;

import java.util.Map;

/**
 * Author(s): danyun
 * Date: 2017/5/29
 */
public class SeedDetails {
    private String mName;
    private String mMagnet;
    private Map<String, String> mSeedFiles;

    public String getName() {
        return mName;
    }

    public SeedDetails setName(String name) {
        mName = name;
        return this;
    }

    public String getMagnet() {
        return mMagnet;
    }

    public SeedDetails setMagnet(String magnet) {
        mMagnet = magnet;
        return this;
    }

    public Map<String, String> getSeedFiles() {
        return mSeedFiles;
    }

    public SeedDetails setSeedFiles(Map<String, String> seedFiles) {
        mSeedFiles = seedFiles;
        return this;
    }
}
