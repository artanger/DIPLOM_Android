package com.example.arthur.easysendler;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arthur.easysendler.entities.Recipient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by Arthur on 04.07.2018.
 */

public class RecipientAdapter extends RecyclerView.Adapter<RecipientAdapter.MyViewHolder> {

    Context mContext;
    List<Recipient> mData;
    LinearLayout mBackground;
    PublishSubject<String> itemClickSubject = PublishSubject.create();

    public PublishSubject<String> getItemClickSubject() {
        return itemClickSubject;
    }

    public void setData(List<Recipient> mData) {
        this.mData = mData;
        this.mBackground = mBackground;
        notifyDataSetChanged();
    }

    public RecipientAdapter(Context mContext) {
        this.mContext = mContext;
        this.mData = new ArrayList<>();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v;
            v = LayoutInflater.from(mContext).inflate(R.layout.item_rllst,parent,false);
            mBackground = v.findViewById(R.id.rllayout);

            MyViewHolder viewHolder = new MyViewHolder(v);
            return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.rl_name.setText(mData.get(position).getText());
        holder.rl_desc.setText(mData.get(position).getDescription());


        if(mData.get(position).getId().equals(App.get(mContext).getMailService().getRecipientId())){
            holder.rllayout.setBackgroundResource(R.drawable.select_item_border);
        }else{
            holder.rllayout.setBackgroundResource(R.drawable.layout_border);
        }



        holder.itemView.setOnClickListener((View view)->{
            itemClickSubject.onNext(mData.get(position).getId());

        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView rl_name;
        private TextView rl_desc;
        private View rllayout;

        public MyViewHolder(View itemView){
            super(itemView);

            rl_name = itemView.findViewById(R.id.rl_name);
            rl_desc = itemView.findViewById(R.id.rl_desc);
            rllayout = itemView.findViewById(R.id.rllayout);


        }
    }
}
