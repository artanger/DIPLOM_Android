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

import com.example.arthur.easysendler.entities.Template;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by Arthur on 09.07.2018.
 */

public class TemplateAdapter extends RecyclerView.Adapter<TemplateAdapter.TemplateViewHolder> {

    Context mContext;
    List<Template> mData;
    LinearLayout mBackground;
    PublishSubject<String> itemClickSubject = PublishSubject.create();

    public PublishSubject<String> getItemClickSubject() {
        return itemClickSubject;
    }



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
        if(mData.get(position).getId().equals(App.get(mContext).getMailService().getRecipientId())){
            holder.template_layout.setBackgroundColor(0xff00ff00);
        }else{
            holder.template_layout.setBackgroundResource(R.drawable.layout_border);
        }



        holder.itemView.setOnClickListener((View view)->{
            itemClickSubject.onNext(mData.get(position).getId());

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
        private View template_layout;


        public TemplateViewHolder(View itemView) {
            super(itemView);
            t_name = itemView.findViewById(R.id.t_name);
            t_desc = itemView.findViewById(R.id.t_desc);

            template_layout = itemView.findViewById(R.id.template_layout);

        }
    }
}
