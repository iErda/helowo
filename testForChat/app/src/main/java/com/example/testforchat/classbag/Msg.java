package com.example.testforchat.classbag;

import android.support.v7.app.AppCompatActivity;

public class Msg  extends AppCompatActivity{
    static final public int TYPE_RECEIVE=1;
    static final public int TYPE_SEND=2;
    String content;
    int type;

    public Msg(String content,int type){
        this.content=content;
        this.type=type;
    }
}
