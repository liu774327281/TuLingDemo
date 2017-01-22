package com.example.loe.qianfengdemo.fragment;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loe.qianfengdemo.R;
import com.example.loe.qianfengdemo.base.BaseFragment;
import com.example.loe.qianfengdemo.view.HeaderView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loe on 12/6/16.
 */
public class HomeFragment extends BaseFragment {

    private View view;
    private String[] tabStr = {"最新","最热"};
    private TabLayout tabLayout ;
    private ViewPager homeVp;
    private List<Fragment> fragments ;
    private Fragment newHomeFragment,hotHomeFragment;
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {

        view = inflater.inflate(R.layout.fragment_home,container,false);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_home_tab);
        homeVp = (ViewPager) view.findViewById(R.id.home_pager);

        fragments = new ArrayList<>();

        newHomeFragment = new NewHomeFragment();
        hotHomeFragment = new HotHomeFragment();
        fragments.add(newHomeFragment);
        fragments.add(hotHomeFragment);

        homeVp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabStr[position];
            }

        });

        tabLayout.setupWithViewPager(homeVp);

        return view;
    }

    public void initData(){
       new HeaderView(getView()).setHeaderText("有房").setLeftVisibility(View.INVISIBLE);
    }
}
