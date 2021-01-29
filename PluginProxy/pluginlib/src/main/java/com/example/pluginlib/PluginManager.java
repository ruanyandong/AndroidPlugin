package com.example.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

// 创建apk对象里所存在的成员变量
public class PluginManager {

    private static final PluginManager instance = new PluginManager();
    public static PluginManager getInstance() {
        return instance;
    }
    private PluginManager(){

    }

    private Context mContext;
    private PluginApk mPluginApk;

    public void init(Context context){
        this.mContext = context.getApplicationContext();
    }

    public PluginApk getPluginApk() {
        return mPluginApk;
    }

    // 加载apk文件
    public void loadApk(String apkPath){
        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES|PackageManager.GET_SERVICES);
        if (packageInfo == null){
            return;
        }
        DexClassLoader dexClassLoader = createDexClassLoader(apkPath);
        AssetManager am = createAssetManager(apkPath);
        Resources resource = createResource(am);
        mPluginApk = new PluginApk(packageInfo,dexClassLoader,resource);
    }

    // 创建DexClassLoader 用来加载apk解压之后的dex文件
    private DexClassLoader createDexClassLoader(String apkPath) {
        // /data/data/your_package_name/app_dex,app_是系统自动加上的
        File file = mContext.getDir("dex",Context.MODE_PRIVATE);
        //  第四个参数是当前ClassLoader的parent classLoader
        return new DexClassLoader(apkPath,file.getAbsolutePath(),null,mContext.getClassLoader());
    }

    // 创建AssetManager
    private AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager am = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
            method.invoke(am,apkPath);
            return am;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 创建Resources
    private Resources createResource(AssetManager am) {
        Resources res = mContext.getResources();
        return new Resources(am,res.getDisplayMetrics(),res.getConfiguration());
    }


}
