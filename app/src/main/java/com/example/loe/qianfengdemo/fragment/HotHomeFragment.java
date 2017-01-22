package com.example.loe.qianfengdemo.fragment;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.loe.qianfengdemo.R;
import com.example.loe.qianfengdemo.base.BaseFragment;

/**
 * Created by loe on 13/6/16.
 */
public class HotHomeFragment extends BaseFragment {

    private View view;
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {

        view = inflater.inflate(R.layout.fragment_new_home,null,false);

        return view;
    }
}
