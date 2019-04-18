package com.example.testforchat.classbag;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import android.support.v7.widget.RecyclerView;

import com.example.testforchat.R;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    List<Msg> mMsgList;
    public MsgAdapter(List<Msg>mMsgList){
        this.mMsgList=mMsgList;
    }

    public void refreshContainer(List<Msg>mMsgList){
        this.mMsgList=mMsgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        //本案例中只有一种viewType类型，故参数无用
        //本案例中的view类型即R.layout.chat_item类型
        View view=View.inflate(parent.getContext(), R.layout.chat_item,null);
        return new ViewHolder(view);//包装
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Msg msg=mMsgList.get(position);
        if(msg.type==Msg.TYPE_RECEIVE){
            holder.tv_receive.setVisibility(View.VISIBLE);//显示发送方
            holder.tv_send.setVisibility(View.GONE);//隐藏接收方，留下位置
            holder.tv_receive.setText(msg.content);
        }else {
            holder.tv_send.setVisibility(View.VISIBLE);
            holder.tv_receive.setVisibility(View.GONE);
            holder.tv_send.setText(msg.content);
        }
    }

    @Override
    public int getItemCount(){
        return mMsgList.size();
    }//表示聊天记录的条数

    static class ViewHolder extends RecyclerView.ViewHolder{//View的包装
        private TextView tv_receive;
        private TextView tv_send;

        ViewHolder(View itemView){
            super(itemView);
            tv_receive=(TextView)itemView.findViewById(R.id.tv_receive);
            tv_send=(TextView)itemView.findViewById(R.id.tv_send);
        }
    }
}
