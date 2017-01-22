package com.example.loe.qianfengdemo.view;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.loe.qianfengdemo.R;
import com.example.loe.qianfengdemo.app.AppContext;

/**
 * Created by loe on 12/6/16.
 */
public class HeaderView {
    private final static String TAG = "HeaderView";
    private Activity activity;
    private TextView textView;
    private RelativeLayout left;
    private RelativeLayout rigth;
    private ImageView right_icon;
    private TextView right_textl;

    /**
     * Activity 中 需要 传入 Activity
     * Fragment 中 需要 传入 Fragment 中的 View 对象
    * @param obj
    */
    public HeaderView(Object obj){
        if(obj instanceof View){
            View view = (View) obj;
            textView = (TextView) view.findViewById(R.id.headerbar_text);
            left = (RelativeLayout) view.findViewById(R.id.headerbar_left);
            rigth = (RelativeLayout) view.findViewById(R.id.headerbar_right);
            right_icon = (ImageView) view.findViewById(R.id.headerbar_right_icon);
            right_textl = (TextView) view.findViewById(R.id.headerbar_right_text);
        }else if (obj instanceof Activity){
            Activity activity = (Activity) obj;
            textView = (TextView) activity.findViewById(R.id.headerbar_text);
            left = (RelativeLayout) activity.findViewById(R.id.headerbar_left);
            rigth = (RelativeLayout) activity.findViewById(R.id.headerbar_right);
            right_icon = (ImageView) activity.findViewById(R.id.headerbar_right_icon);
            right_textl = (TextView) activity.findViewById(R.id.headerbar_right_text);
        }else {
            Log.e(TAG,"参数传入错误");
            return;
        }

    }
    //设置主要标题文字
    public HeaderView setHeaderText(String str){
        textView.setText(str);
        return this;
    }
    //设置右边标题文字
    public HeaderView setHeaderRigthText(String str){
        right_textl.setText(str);
        right_icon.setVisibility(View.GONE);
        right_textl.setVisibility(View.VISIBLE);
        return this;
    }
    //设置右边标题图标
    public HeaderView setHeaderRigthIcon(int id){
        right_icon.setBackgroundResource(id);
        right_icon.setVisibility(View.VISIBLE);
        right_textl.setVisibility(View.GONE);
        return this;
    }

    //隐藏左边图标
    public HeaderView setLeftVisibility(int visibility){
        left.setVisibility(visibility);
        return this;
    }

    //设置点击事件
    public HeaderView setHeaderClickListener(final HeaderclickListener listener){
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                    listener.leftClickListener(v);
            }
        });
        rigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                    listener.rightClickListener(v);
            }
        });

        return this;
    }

    public interface HeaderclickListener {
        /**
         * 左边点击
         */
         void leftClickListener(View v);

        /**
         * 右边点击事件
         */
         void rightClickListener(View v);
    }

}
