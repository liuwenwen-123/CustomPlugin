package com.example.plugn_core;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;

public class BaseActivity extends Activity implements PluginInterface {
    //    上下问 == 代理activity
    protected Activity that;

    @Override
    public void attach(Activity activity) {
        this.that = activity;
    }

    @Override
    public void setContentView(View view) {
        if (that == null) {
            super.setContentView(view);
        }else{
            that.setContentView(view);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        that.setContentView(layoutResID);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return that.findViewById(id);
    }

    @Override
    public Intent getIntent() {
        return that.getIntent();
    }

    @Override
    public ClassLoader getClassLoader() {
        return that.getClassLoader();
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return that.getLayoutInflater();
    }


    @Override
    public void startActivity(Intent intent) {
        Intent im=new Intent();
        im.putExtra("className",intent.getComponent().getClassName());
        that.startActivity(im);
    }

    @Override
    public Resources getResources() {
        return that.getResources();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return that.getApplicationInfo();
    }

    @Override
    public Window getWindow() {
        return that.getWindow();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestory() {

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public boolean onTounchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void backPressed() {

    }
}
