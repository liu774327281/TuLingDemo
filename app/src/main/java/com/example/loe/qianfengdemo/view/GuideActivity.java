package com.example.loe.qianfengdemo.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.loe.qianfengdemo.R;
import com.example.loe.qianfengdemo.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by loe on 12/6/16.
 */
public class GuideActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(gContext,MainActivity.class);
                gContext.startActivity(intent);
                finish();
            }
        },1000);
    }
}
