package com.example.plugn_core;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

/**
 * 代理类
 */
public class ProxyActivity extends AppCompatActivity {

    private PluginInterface pluginInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
        Intent intent = getIntent();
//        获取真正的类名
        String className = intent.getStringExtra("className");
//  通过 里加载器 去加载 这个类
        try {
//            真正目的地的 activity对象
            Class<?> aClass = PluginManger.getInstance().getDexClassLoader().loadClass(className);
//            实例化
            Object activity = aClass.newInstance();
            if(activity instanceof  PluginInterface){
                pluginInterface= (PluginInterface) activity;
                pluginInterface.attach(this);
                pluginInterface.onCreate(savedInstanceState);
                PluginManger.getInstance().getPlugingResources();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

//       从插件中 跳转到 目标apk
    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        Intent intent1=new Intent(this,ProxyActivity.class);
        intent1.putExtra("className",className);
        super.startActivity(intent1);
    }

    @Override
    public Resources getResources() {
        return PluginManger.getInstance().getPlugingResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManger.getInstance().getDexClassLoader();
    }

    @Override
    protected void onStart() {
        super.onStart();
        pluginInterface.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        pluginInterface.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pluginInterface.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pluginInterface.onDestory();
    }
}