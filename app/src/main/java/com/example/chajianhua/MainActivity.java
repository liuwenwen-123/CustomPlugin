package com.example.chajianhua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.example.plugn_core.PluginManger;
import com.example.plugn_core.ProxyActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void jump(View view) {
       /* PackageManager pm = getPackageManager();
        try {
            ApplicationInfo appi = pm.getApplicationInfo("com.example.androidevent", PackageManager.GET_META_DATA);
            Log.e("eee",appi.packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }*/



      /*  PackageManager pm = getPackageManager();
        PackageInfo info =pm.getPackageArchiveInfo(  "/data/data/com.example.chajianhua/tm.apk", PackageManager.GET_ACTIVITIES);
        String pkg_name = info.applicationInfo.packageName; //得到安装包名称
        String version = info.versionName;

        Log.e("eee",pkg_name+"--- "+version);*/

/*        PackageInfo info2 = pm.getPackageArchiveInfo(”sd路径“,, PackageManager.GET_META_DATA);
        String game_pkg_name = info2.applicationInfo.metaData.getString(GAIntent.TARGET0);
        String game_version = info2.applicationInfo.metaData.getString(GAIntent.VERSION0);*/

        PluginManger.getInstance().setContext(this);
//        加载插件
        PluginManger.getInstance().loadPlugin("/data/data/com.example.chajianhua/tm.apk");

        PackageInfo packageInfo = PluginManger.getInstance().getPackageInfo();

        if (packageInfo.activities.length <= 0) {
            return;
        }
        //        插件中 一定要
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("className",packageInfo.activities[0].name);
        startActivity(intent);
    }


}