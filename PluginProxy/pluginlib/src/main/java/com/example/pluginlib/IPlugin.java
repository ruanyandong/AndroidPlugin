package com.example.pluginlib;

import android.app.Activity;
import android.os.Bundle;

public interface IPlugin {

    int FROM_INTERNAL = 0;// 主app加载apk
    int FROM_EXTERNAL = 1;// 系统加载apk

    void attach(Activity activity);

    void onCreate(Bundle saveInstanceState);

}
