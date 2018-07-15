package com.example.arthur.easysendler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arthur.easysendler.entities.Recipient;
import com.example.arthur.easysendler.entities.Setting;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.SettingViewHolder> {

    Context mContext;
    List<Setting> mData;
    LinearLayout mBackground;
    PublishSubject<String> itemClickSubject = PublishSubject.create();

    public PublishSubject<String> getItemClickSubject() {
        return itemClickSubject;
    }


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

        if(mData.get(position).getId().equals(App.get(mContext).getMailService().getRecipientId())){
            holder.settinglayout.setBackgroundColor(0xff00ff00);
        }else{
            holder.settinglayout.setBackgroundResource(R.drawable.layout_border);
        }



        holder.itemView.setOnClickListener((View view)->{
            itemClickSubject.onNext(mData.get(position).getId());

        });
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
        private View settinglayout;

        public SettingViewHolder(View itemView) {
            super(itemView);

            s_name = itemView.findViewById(R.id.s_name);
            s_desc = itemView.findViewById(R.id.s_desc);

            settinglayout = itemView.findViewById(R.id.settinglayout);




        }


    }

}
