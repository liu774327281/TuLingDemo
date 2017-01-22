package com.example.loe.qianfengdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.loe.qianfengdemo.R;
import com.example.loe.qianfengdemo.model.ChatMsgModel;
import com.example.loe.qianfengdemo.uitls.TimeUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by loe on 13/6/16.
 */
public class ChatMsgAdapter extends BaseAdapter {

    private List<ChatMsgModel> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    public ChatMsgAdapter(Context context, List<ChatMsgModel> data) {
        this.mContext = context;
        this.mData = data;
        mInflater = LayoutInflater.from(this.mContext);
    }

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
        ViewHodel hodel = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.chat_msg_main, null);
            hodel = new ViewHodel();
            hodel.date = (TextView) convertView.findViewById(R.id.id_chat_form_msg_date);
            hodel.msg = (TextView) convertView.findViewById(R.id.id_chat_from_msg_info);
            hodel.to = convertView.findViewById(R.id.id_Chat_to_layout);
            hodel.from = convertView.findViewById(R.id.id_chat_form_layout);
            hodel.msg2 = (TextView)convertView.findViewById(R.id.id_chat_to_msg_info);
            hodel.date2 = (TextView)convertView.findViewById(R.id.id_chat_to_msg_date);
            convertView.setTag(hodel);
        } else {
            hodel = (ViewHodel) convertView.getTag();
        }

        if (mData.get(position).getType().equals("0")) {
            hodel.to.setVisibility(View.GONE);
            hodel.from.setVisibility(View.VISIBLE);
        } else {
            hodel.to.setVisibility(View.VISIBLE);
            hodel.from.setVisibility(View.GONE);
        }

        hodel.date.setText(TimeUtils.timeSimple(new Date().getTime()));
        hodel.msg.setText(mData.get(position).getContent());
        hodel.date2.setText(TimeUtils.timeSimple(new Date().getTime()));
        hodel.msg2.setText(mData.get(position).getContent());

        return convertView;
    }

    class ViewHodel {
        TextView date;
        TextView msg;
        TextView date2;
        TextView msg2;
        View to;
        View from;
    }
}
