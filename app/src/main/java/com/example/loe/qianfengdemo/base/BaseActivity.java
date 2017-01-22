package com.example.loe.qianfengdemo.base;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loe.qianfengdemo.R;
import com.example.loe.qianfengdemo.app.AppContext;
import com.example.loe.qianfengdemo.view.HeaderView;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础类
 * Created by loe on 12/6/16.
 */
public class BaseActivity extends AppCompatActivity {

    // 基类提供Context 方便子类直接使用
    protected Context gContext;

    protected AppContext gAppContext;

    private PermissionCallback callback;

    private final static int PERMISSION_CALLBACK_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gContext = this;

        gAppContext = AppContext.getAppContext();

    }

    public void getPermission(PermissionCallback callback, String[] permissions) {
        this.callback = callback;

        List perms = findDeniedPermissions(this,permissions);

        if(perms.size() > 0) {
            String[] perm = (String[]) perms.toArray(new String[perms.size()]);
            ActivityCompat.requestPermissions(this,
                    perm,
                    PERMISSION_CALLBACK_CODE);

        }else {
            this.callback.hasPermission();
        }
    }


    @TargetApi(value = Build.VERSION_CODES.M)
    public List<String> findDeniedPermissions(Activity activity, String... permission) {
        List<String> denyPermissions = new ArrayList<>();
        for (String value : permission) {
            if (activity.checkSelfPermission(value) != PackageManager.PERMISSION_GRANTED) {
                denyPermissions.add(value);
            }
        }
        return denyPermissions;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == PERMISSION_CALLBACK_CODE) {
            //判断当前组接口是否全部通过
            boolean flag = true;

            for (int  g : grantResults) {
                if (g == PackageManager.PERMISSION_GRANTED) {
                    Log.i("TAG_LI", "ok");
                } else {
                    flag = false;
                    Log.i("TAG_LI", "Denide");
                }
                if (flag){
                    this.callback.hasPermission();
                }else {
                    this.callback.noPermission();
                }
            }

            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 权限申请接口
     */
    public interface PermissionCallback {

        void hasPermission();

        void noPermission();
    }
}
