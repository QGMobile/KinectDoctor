package com.qg.kinectdoctor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ZH_L on 2016/10/21.
 */
public abstract class ItemAdapter<E, VH extends ItemAdapter.ItemViewHolder> extends RecyclerView.Adapter<VH>{
    private List<E> mList;
    private LayoutInflater mInflater;
    private int mLayoutId;

    public ItemAdapter(Context context, List list, int layoutId){
        mInflater = LayoutInflater.from(context);
        mList = list;
        mLayoutId = layoutId;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
         return createItemView(parent,viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        E element = mList.get(position);
        holder.bindElement(element);
    }

    public abstract VH createItemView(ViewGroup parent, int viewType);

    public abstract  void bindViewHolder(VH holder);

    public View getView(ViewGroup parent, int viewType){
        return mInflater.inflate(mLayoutId, parent, false);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public abstract class ItemViewHolder<E> extends RecyclerView.ViewHolder{

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bindElement(E element);
    }
}
