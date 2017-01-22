package com.example.loe.qianfengdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loe.qianfengdemo.app.AppContext;

/**
 * Created by loe on 12/6/16.
 */
public abstract class BaseFragment extends Fragment {

    protected Context gContext;
    protected AppContext appContext;
    //创建 用于初始化 优先级最高的 数据
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gContext = getActivity();
        appContext = AppContext.getAppContext();
    }

    //用于创建Fragment 的 View
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView(inflater,container);
        return view;
    }

    //用于初始化 Fragment 相互 数据 data
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //如果有数据需要在这里进行初始化
    public void initData() {

    }

    //抽象方法 其子类必须实现
    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container);
}
