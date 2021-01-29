package com.example.pluginlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import dalvik.system.DexClassLoader;

// 插件apk的实体对象
public class PluginApk {

    public PackageInfo mPackageInfo;
    public DexClassLoader mClassloader;
    public Resources mResource;
    public AssetManager mAssetManager;


    public PluginApk(PackageInfo mPackageInfo, DexClassLoader mClassloader,
                     Resources mResource) {
        this.mPackageInfo = mPackageInfo;
        this.mClassloader = mClassloader;
        this.mResource = mResource;
        this.mAssetManager = mResource.getAssets();

    }

}
