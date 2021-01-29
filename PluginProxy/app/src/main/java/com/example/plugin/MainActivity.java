package com.example.plugin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.pluginlib.PluginManager;
import com.example.pluginlib.ProxyActivity;

/**
 * https://www.androidos.net.cn
 * 插件化：每个组件业务就是一个独立apk，然后通过主app动态加载部署业务组件apk
 *
 * 优势 ：1、业务组件解耦，能够实现业务组件热插拔
 *       2、更改产品迭代模式，可分为主app以及次业务app
 *       3、改善产品更新过程，可以在不影响用户的情况下实现业务组件更新以及bug修复
 *
 * 插件化步骤——分析主App
 *    1、主App打包完成后，会形成dex，images，xml资源
 *    2、Dex靠PathClassLoader加载
 *    3、图片以及xml资源靠Resource加载
 *
 * 插件化步骤——代码实现
 *     1、创建DexClassLoader加载插件代码
 *     2、创建Resource加载资源文件
 *     3、管理插件Activity生命周期
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 通过代理跳转

        PluginManager.getInstance().init(this);

        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apkPath = Utils.copyAssetAndWrite(MainActivity.this,"ne.apk");
                PluginManager.getInstance().loadApk(apkPath);

            }
        });

        findViewById(R.id.gotoa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ProxyActivity.class);
                intent.putExtra("className","com.example.neplug.PlugActivity");
                startActivity(intent);
            }
        });


    }
}
