package com.example.loe.qianfengdemo.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.loe.qianfengdemo.R;
import com.example.loe.qianfengdemo.base.BaseActivity;
import com.example.loe.qianfengdemo.base.BaseFragment;
import com.example.loe.qianfengdemo.fragment.HomeFragment;
import com.example.loe.qianfengdemo.fragment.MessageFragment;
import com.example.loe.qianfengdemo.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    private List<BaseFragment> fragments ;
    private ViewPager mainVp ;
    private BaseFragment homeFragment, messageFragment, myFragment;
    private RadioGroup mRadioGroup;
    private RadioButton mHomeBt,mMessageBt,mMyBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        fragments = new ArrayList<>();

        homeFragment = new HomeFragment();
        messageFragment = new MessageFragment();
        myFragment = new MyFragment();

        fragments.add(homeFragment);
        fragments.add(messageFragment);
        fragments.add(myFragment);
    }

    private void initView() {

        mRadioGroup = (RadioGroup) findViewById(R.id.main_RadioGroup);
        mHomeBt = (RadioButton) findViewById(R.id.main_rb_home);
        mMessageBt = (RadioButton) findViewById(R.id.main_rb_message);
        mMyBt = (RadioButton) findViewById(R.id.main_rb_my);

        mainVp = (ViewPager) findViewById(R.id.main_ViewPager);
        //初始化 适配器
        mainVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        //为RadioGroup 设置监听
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.main_rb_home:
                        mainVp.setCurrentItem(0,false);
                    break;
                    case R.id.main_rb_message:
                        mainVp.setCurrentItem(1,false);
                    break;
                    case R.id.main_rb_my:
                        mainVp.setCurrentItem(2,false);
                    break;

                }
            }
        });
        //默认点击第一bt
        mHomeBt.setChecked(true);
        mainVp.setOffscreenPageLimit(3); //防止Viewpager 自动销毁

        //监听viewpager滑动监听
        mainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mHomeBt.setChecked(true);
                        break;
                    case 1:
                        mMessageBt.setChecked(true);
                        break;
                    case 2:
                        mMyBt.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
