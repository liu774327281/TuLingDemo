package com.example.loe.qianfengdemo.fragment;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.loe.qianfengdemo.R;
import com.example.loe.qianfengdemo.adapter.ChatMsgAdapter;
import com.example.loe.qianfengdemo.base.BaseFragment;
import com.example.loe.qianfengdemo.dao.ApiStringCallback;
import com.example.loe.qianfengdemo.dao.ChatMsgDao;
import com.example.loe.qianfengdemo.model.ChatMsgModel;
import com.example.loe.qianfengdemo.view.HeaderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loe on 12/6/16.
 */
public class MessageFragment extends BaseFragment implements View.OnClickListener{

    private ListView mChatListView;
    private Button mChatsend;
    private EditText mChated;
    private ChatMsgAdapter chatMsgAdapter;
    private List<ChatMsgModel> msgModels;
    private ChatMsgDao chatMsgDao ;
    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {

        View view = inflater.inflate(R.layout.fragment_message,container,false);

        mChatListView= (ListView) view.findViewById(R.id.id_chat_listview_msgs);
        mChated= (EditText) view.findViewById(R.id.id_chat_input_msg);
        mChatsend = (Button) view.findViewById(R.id.id_chat_send_msg);

        mChatsend.setOnClickListener(this);
        msgModels = new ArrayList<>();

        ChatMsgModel chat = new ChatMsgModel();
        chat.setType("0");
        chat.setContent("您好我是图灵,很高兴为您服务");
        msgModels.add(chat);

        chatMsgAdapter = new ChatMsgAdapter(gContext,msgModels);
        mChatListView.setAdapter(chatMsgAdapter);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        new HeaderView(getView()).setHeaderText("图灵为您服务").setLeftVisibility(View.INVISIBLE);
        chatMsgDao  = new ChatMsgDao();
    }

    @Override
    public void onClick(View v) {
        if(!mChated.getText().toString().equals("")){
            ChatMsgModel chat = new ChatMsgModel();
            chat.setType("1");
            chat.setContent(mChated.getText().toString());
            msgModels.add(chat);
            chatMsgAdapter.notifyDataSetChanged();

            chatMsgDao.getChatMsg(mChated.getText().toString(), new ApiStringCallback(getActivity()) {
                @Override
                public void onSuccessEvent(String response) {
                    JSONObject jsonObject =null;
                    String msg = null;
                    try {
                        jsonObject = new JSONObject(response);
                        msg = jsonObject.getString("text");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(msg != null) {
                        ChatMsgModel chat = new ChatMsgModel();
                        chat.setType("0");
                        chat.setContent(msg);
                        msgModels.add(chat);
                        chatMsgAdapter.notifyDataSetChanged();
                        mChatListView.setSelection(mChatListView.getAdapter().getCount());
                    }
                }
            });
            mChated.setText("");
        }
    }
}
