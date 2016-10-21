package com.qg.kinectdoctor.adapter;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qg.kinectdoctor.R;
import com.qg.kinectdoctor.fragment.ChatListFragment;
import com.qg.kinectdoctor.model.ChatInfoBean;

import java.util.List;

/**
 * Created by ZH_L on 2016/10/21.
 */
public class ChatListAdapter extends ItemAdapter<ChatInfoBean, ChatListAdapter.ChatInfoHolder>{

    private static final String TAG = ChatListAdapter.class.getSimpleName();

    public ChatListAdapter(Context context, List list, int layoutId) {
        super(context, list, layoutId);
    }

    @Override
    public ChatInfoHolder createItemView(ViewGroup parent, int viewType) {
        View v = getView(parent, viewType);
        return new ChatInfoHolder(v);
    }


    public class ChatInfoHolder extends ItemViewHolder<ChatInfoBean>{
        private TextView nameTv;
        private TextView unReadTv;

        public ChatInfoHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initChilds(View itemView) {
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            unReadTv = (TextView)itemView.findViewById(R.id.unread_tv);
        }

        @Override
        public void bindElement(ChatInfoBean bean) {
            String name = bean.getName();
            nameTv.setText(name);
            int unReadCount = bean.getUnReadCount();
            if(unReadCount <= 0 ){
                unReadTv.setVisibility(View.GONE);
                unReadTv.setText("");
            }else{
                unReadTv.setText(""+unReadCount);
                unReadTv.setVisibility(View.VISIBLE);
            }
        }
    }
}