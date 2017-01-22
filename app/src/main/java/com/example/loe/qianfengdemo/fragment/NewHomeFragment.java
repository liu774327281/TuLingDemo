package com.example.loe.qianfengdemo.fragment;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.loe.qianfengdemo.R;
import com.example.loe.qianfengdemo.base.BaseFragment;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loe on 13/6/16.
 */
public class NewHomeFragment extends BaseFragment {

    private View view;
    private PullToRefreshListView myPullListView;
    private List<String> mData ;
    private MyPullListViewApapter mApapter;
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {

        view = inflater.inflate(R.layout.fragment_new_home,null,false);

        myPullListView  = (PullToRefreshListView) view.findViewById(R.id.new_hoem_listview);
        myPullListView.setMode(PullToRefreshBase.Mode.BOTH);
        init();


//      //设置当前上拉加载不可用
//      myPullListView.setPullLoadEnabled(false);
//     //设置下拉刷新可用
//     myPullListView.setPullRefreshEnabled(true);
//      //设置上拉加载可用
//     myPullListView.setPullLoadEnabled(                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       true);
//      //滑到底部是否自动加载数据，这句话一定要加要不然"已经到底啦"显示不出来
//      myPullListView.setScrollLoadEnabled(true);
        //得到真正的listview,我们在给listview设置adapter时或者设置onItemClick事件必须通过它，而不能用ptrlv_test
        ListView mListView = myPullListView.getRefreshableView();

        TextView tv = new TextView(gContext);
        tv.setText("我是头部");

        mListView.addHeaderView(tv);

        myPullListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
                Log.i("TAG","DOWN");
                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0 ;i < 10 ;i++){
                            mData.add(0,i + "头部");
                        }
                        mApapter.notifyDataSetChanged();
                        refreshView.onRefreshComplete();
                    }
                },2000);

            }

            @Override
            public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {
                Log.i("TAG","UP");
                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0 ;i < 10 ;i++){
                            mData.add(i + "尾部");
                        }
                        mApapter.notifyDataSetChanged();
                        refreshView.onRefreshComplete();
                    }
                },2000);
            }
        });

        return view;
    }

    private void init() {
        mData = new ArrayList<>();

        for(int i = 0 ;i < 30 ;i++){
            mData.add(i + "国产无良厂商");
        }
        mApapter = new MyPullListViewApapter();
        myPullListView.setAdapter(mApapter);
    }

    class MyPullListViewApapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //测试demo 不适用viewhodle

            convertView = LayoutInflater.from(gContext).inflate(R.layout.apapter_new_home_pulllistview,null);

            ((TextView)convertView.findViewById(R.id.apapter_text)).setText(mData.get(position));

            return convertView;
        }
    }

}
