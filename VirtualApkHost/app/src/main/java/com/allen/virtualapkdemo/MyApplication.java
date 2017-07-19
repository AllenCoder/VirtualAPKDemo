package com.allen.virtualapkdemo;

import android.app.Application;
import android.content.Context;

import com.didi.virtualapk.PluginManager;

/**
 * 文 件 名: MyApplication
 * 创 建 人: Allen
 * 创建日期: 2017/7/18 17:17
 * 修改时间：
 * 修改备注：
 */

public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PluginManager.getInstance(base).init();
    }
}
