package com.example.loe.qianfengdemo.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.loe.qianfengdemo.R;
import com.example.loe.qianfengdemo.base.BaseActivity;
import com.example.loe.qianfengdemo.base.BaseFragment;
import com.example.loe.qianfengdemo.view.HeaderView;

/**
 * Created by loe on 12/6/16.
 */
public class MyFragment extends BaseFragment {
    private Button myButton;
    private final static int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        myButton = (Button) view.findViewById(R.id.myCallBt);
        init();
        return view;
    }

    private void init() {
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((BaseActivity) getActivity()).getPermission(new BaseActivity.PermissionCallback() {
                    @Override
                    public void hasPermission() {
                        callPhone();
                    }

                    @Override
                    public void noPermission() {
                        Toast.makeText(getActivity(), "noPermission", Toast.LENGTH_SHORT).show();
                    }
                }, new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.CAMERA});
//                callPhone();

            }
        });
    }

    public void initData() {
        new HeaderView(getView()).setHeaderText("我的").setLeftVisibility(View.INVISIBLE);

    }

    public void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10010");
        intent.setData(data);
        startActivity(intent);

    }


}
