package com.example.neplug;

import android.os.Bundle;

import com.example.pluginlib.PluginActivity;

public class PlugActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plug);
    }
}
