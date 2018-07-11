package com.example.arthur.easysendler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arthur.easysendler.entities.Recipient;
import com.example.arthur.easysendler.entities.Setting;

import java.util.ArrayList;
import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> {

    Context mContext;
    List<Setting> mData;


    public SettingAdapter(Context mContext) {
        this.mContext = mContext;
        this.mData = new ArrayList<>();

    }

    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_settings,parent,false);
        SettingViewHolder settingviewHolder = new SettingViewHolder(v);
        return settingviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position) {

        holder.s_name.setText(mData.get(position).getText());
        holder.s_desc.setText(mData.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<Setting> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }


    public static class SettingViewHolder extends RecyclerView.ViewHolder{
        private TextView s_name;
        private TextView s_desc;

        public SettingViewHolder(View itemView) {
            super(itemView);

            s_name = itemView.findViewById(R.id.s_name);
            s_desc = itemView.findViewById(R.id.s_desc);




        }


    }

}
