package com.example.testforchat;

import com.example.testforchat.classbag.Msg;
import com.example.testforchat.classbag.MsgAdapter;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.content.Intent;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.EditText;
import android.widget.Button;

import java.util.List;
import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    //----缺少等待输入动画/正在输入语音的动画
    private RecyclerView recyclerview;
    private MsgAdapter mMsgAdapter;
    private EditText et_input;
    private Button bt_send;
    private Button bt_toRecord;

    private List<Msg>mMsgSendList;
    private List<Msg>mMsgRecvList;
    private int answer_cnt;
    private List<Msg>mMsgList;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatactivity);
        Intent intent=getIntent();
        Bundle info=intent.getExtras();
        username=info.getString("username");

        initView();
        initData();
        initAdapter();
    }

    private void initView(){
        recyclerview=(RecyclerView)findViewById(R.id.recyclerview);
        et_input=(EditText)findViewById(R.id.et_input);
        bt_send=(Button)findViewById(R.id.bt_send);
        bt_toRecord=(Button)findViewById(R.id.bt_toRecord);
        answer_cnt=-1;

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);

        bt_toRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///跳转处
                /*
                Intent intent=new Intent();
                intent.setClass(ChatActivity.this, PersonSpaceActivity.class);
                startActivity(intent);
                ChatActivity.this.finish();
                 */
            }
        });

        bt_send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String content=et_input.getText().toString().trim();
                if(!content.isEmpty()){
                    Msg msg=new Msg(content,Msg.TYPE_SEND);
                    mMsgSendList.add(msg);
                    mMsgList.add(msg);
                    mMsgAdapter.refreshContainer(mMsgList);//刷新视图保存的聊天记录，这一步也许是多余的，
                                                              // 但由于对notifyDataSetChanged的机理不够清楚，故如此
                    mMsgAdapter.notifyDataSetChanged();
                    recyclerview.scrollToPosition(mMsgList.size()-1);
                    et_input.setText("");

                    //-----未实现的方案二：开启一个线程
                    //方案一：
                    //开启一个动画

                    //放置应答
                    answer_cnt+=1;
                    int sn=answer_cnt%mMsgRecvList.size();
                    mMsgList.add(mMsgRecvList.get(sn));
                    mMsgAdapter.notifyDataSetChanged();
                    recyclerview.scrollToPosition(mMsgList.size()-1);
                }
            }
        });
    }

    private void initData(){
        mMsgSendList=new ArrayList<>();
        mMsgSendList.add(new Msg("Hello World!",Msg.TYPE_SEND));
        mMsgSendList.add(new Msg("Who are you?",Msg.TYPE_SEND));

        mMsgRecvList=new ArrayList<>();
        mMsgRecvList.add(new Msg("I've seen things you people wouldn't believe.",Msg.TYPE_RECEIVE));
        mMsgRecvList.add(new Msg("Attack ships on fire off the shoulder of Orion.",Msg.TYPE_RECEIVE));
        mMsgRecvList.add(new Msg("I've watched c-beams glitter in the dark near the Tannhauser Gate.",Msg.TYPE_RECEIVE));
        mMsgRecvList.add(new Msg("All those ... ",Msg.TYPE_RECEIVE));
        mMsgRecvList.add(new Msg("moments will be lost in time, ",Msg.TYPE_RECEIVE));
        mMsgRecvList.add(new Msg("like tears...",Msg.TYPE_RECEIVE));
        mMsgRecvList.add(new Msg("in rain.",Msg.TYPE_RECEIVE));

        mMsgList=new ArrayList<>();
        mMsgList.add(mMsgSendList.get(0));
        mMsgList.add(mMsgSendList.get(1));
        mMsgList.add(new Msg("Hello,"+username,Msg.TYPE_RECEIVE));
        mMsgRecvList.add(new Msg("...",Msg.TYPE_RECEIVE));
    }

    private void initAdapter(){
        mMsgAdapter=new MsgAdapter(mMsgList);
        recyclerview.setAdapter(mMsgAdapter);
    }
}
