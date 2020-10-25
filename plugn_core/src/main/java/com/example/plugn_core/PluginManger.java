package com.example.plugn_core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * 核心类
 * 获取插件的资源对象
 * 获取到插件中 的类架器
 * 获取到插件包信息
 */
public class PluginManger {
    private static PluginManger pluginManger = new PluginManger();
    // 上下文
    private Context context;
    //    插件资源对象
    private Resources PlugingResources;
    //     插件的累加载器
    private DexClassLoader dexClassLoader;
    //     插件的包信息
    private PackageInfo packageInfo;


    /**
     * 加载插件apk
     *
     * @param pluginPath
     */
    public void loadPlugin(String pluginPath) {

//    获取到 包管理器
        PackageManager packageManager = context.getPackageManager();
//        获取到 插件的包管理信息类
        packageInfo = packageManager.getPackageArchiveInfo(pluginPath, PackageManager.GET_ACTIVITIES);


        //        获取到 插件的类加载器
        /**
         * pluginPath 插件路径
         * plugin.getAbsolutePath()  插件解压的路径
         * null libaray
         * context.getClassLoader()   类加载器的父类
         */
//        获取插件解压的路径
        File plugin = context.getDir("plugin", Context.MODE_PRIVATE);
        dexClassLoader = new DexClassLoader(pluginPath, plugin.getAbsolutePath(), null
                , context.getClassLoader());
//     获取到插件资源 对象
        try {
//            创建 AssetManager
            AssetManager assetManager = AssetManager.class.newInstance();
//            反射获取  addAssetPath 方法
            Method addAssetPath = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, pluginPath);
//            后2个参数 是工具类
            PlugingResources=new Resources(assetManager,context.getResources().getDisplayMetrics(),
                    context.getResources().getConfiguration() );
        } catch (Exception e) {
            e.printStackTrace();
        }
//
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static PluginManger getInstance() {
        return pluginManger;
    }

    private PluginManger() {
    }

    public Resources getPlugingResources() {
        return PlugingResources;
    }

    public void setPlugingResources(Resources plugingResources) {
        PlugingResources = plugingResources;
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public void setDexClassLoader(DexClassLoader dexClassLoader) {
        this.dexClassLoader = dexClassLoader;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }


}
