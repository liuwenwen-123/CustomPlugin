package com.example.plugn_core;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 *  标展: 所有的插件apk里面的activity都实现这个接口
 *   所有 外置apk中的app 页面 必须都要 实现的接口类 这个类是 规范所有外置 apk的activity的标准
 *   面向接口考法
 *  */
public interface PluginInterface {
    /**
     *  注入上下我呢
     */
    void attach(Activity activity);
    void onCreate(Bundle savedInstanceState);
    void onStart();
    void onResume();
    void onPause();
    void onDestory  ();

    void onSaveInstanceState(Bundle savedInstanceState);
    boolean onTounchEvent(MotionEvent event);
    void backPressed();
}
