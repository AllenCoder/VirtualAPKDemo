package com.allen.virtualapkdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.didi.virtualapk.PluginManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    /**
     * 打开插件Demo
     */
    private TextView mOpen;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadPlugin(MainActivity.this);
    }

    private void initView() {
        mOpen = (TextView) findViewById(R.id.open);
        mOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String pkg = "com.allen.virtualapkplugin";
                if (PluginManager.getInstance(MainActivity.this).getLoadedPlugin(pkg) == null) {
                    Toast.makeText(MainActivity.this, "com.allen.virtualapkplugin 为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                // test Activity and Service
                Intent intent = new Intent();
                intent.setClassName(pkg, "com.allen.virtualapkplugin.MainActivity");
                startActivity(intent);
            }
        });
    }

    private void loadPlugin(Context base) {
        PluginManager pluginManager = PluginManager.getInstance(base);
        File apk = new File(Environment.getExternalStorageDirectory(), "Test1.apk");
        if (apk.exists()) {
            try {
                pluginManager.loadPlugin(apk);
            } catch (Exception e) {
                Log.e(TAG, "loadPlugin: "+e.getLocalizedMessage() );
            }
        }else {
            Toast.makeText(getApplicationContext(),"文件不存在",Toast.LENGTH_SHORT).show();
        }
    }
}
