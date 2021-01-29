package com.example.pluginlib;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class PluginActivity extends Activity implements IPlugin{

    private int mFrom = FROM_INTERNAL;//
    private Activity mProxyActivity;

    @Override
    public void attach(Activity activity) {
        mProxyActivity = activity;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        if (saveInstanceState != null){
            mFrom = saveInstanceState.getInt("FROM");
        }
        Log.i("ryd_tag", "onCreate mFrom:" + mFrom);
        if (mFrom == FROM_INTERNAL){
            super.onCreate(saveInstanceState);
            //mProxyActivity = this;
        }
    }

    @Override
    public void setContentView(int layoutResId) {
        Log.i("ryd_tag", "setContentView mFrom:" + mFrom);
        if (mFrom == FROM_INTERNAL){
            super.setContentView(layoutResId);
            return;
        }
        mProxyActivity.setContentView(layoutResId);
    }
}
