package com.example.arthur.easysendler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arthur.easysendler.entities.Template;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arthur on 09.07.2018.
 */

public class TemplateAdapter extends RecyclerView.Adapter<TemplateAdapter.TemplateViewHolder> {

    Context mContext;
    List<Template> mData;

    public TemplateAdapter(Context mContext) {
        this.mContext = mContext;
        this.mData = new ArrayList<>();
    }

    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_template,parent,false);
        TemplateViewHolder templateviewholder = new TemplateViewHolder(v);
        return templateviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder holder, int position) {

        holder.t_name.setText(mData.get(position).getText());
        holder.t_desc.setText(mData.get(position).getDescription());
        holder.itemView.setOnClickListener((View view)->{
            Toast.makeText(mContext, mData.get(position).getId(), Toast.LENGTH_LONG).show();
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<Template> mData) {
        this.mData = mData;
        notifyDataSetChanged();

    }

    public static class TemplateViewHolder extends RecyclerView.ViewHolder{
        private TextView t_name;
        private TextView t_desc;


        public TemplateViewHolder(View itemView) {
            super(itemView);
            t_name = itemView.findViewById(R.id.t_name);
            t_desc = itemView.findViewById(R.id.t_desc);

        }
    }
}
