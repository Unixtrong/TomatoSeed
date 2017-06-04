package com.huangshan.tomatoseed.bean;


import android.support.v4.util.Pair;

import java.util.List;

/**
 * Author(s): danyun
 * Date: 2017/5/29
 */
public class SeedDetails {
    private String mName;
    private String mMagnet;
    private List<Pair<String, String>> mSeedFiles;

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

    public List<Pair<String, String>> getSeedFiles() {
        return mSeedFiles;
    }

    public SeedDetails setSeedFiles(List<Pair<String, String>> seedFiles) {
        mSeedFiles = seedFiles;
        return this;
    }
}
