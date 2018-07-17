package com.example.arthur.easysendler.screens;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arthur.easysendler.App;
import com.example.arthur.easysendler.R;
import com.example.arthur.easysendler.SettingAdapter;
import com.example.arthur.easysendler.api.MailApi;
import com.example.arthur.easysendler.services.MailService;
import com.example.arthur.easysendler.utils.Provider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SettingsFragment extends Fragment {


    View v;
    MailApi api;
    private RecyclerView myrecyclerview;
    SettingAdapter settingAdapter;



    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_settings, container, false);
        myrecyclerview = v.findViewById(R.id.settings_list_recyclerview);

        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        settingAdapter.getItemClickSubject().subscribe(id->{
            App.get(getContext()).getMailService().setSettingId(id);
            settingAdapter.notifyDataSetChanged();
        });



        myrecyclerview.setAdapter(settingAdapter);

        return v;
    }
    MailService mailService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mailService = App.get(getContext()).getMailService();
        settingAdapter = new SettingAdapter(getContext());
        api = Provider.Api.getMailApi();
        api.getSetting(10, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        resp -> settingAdapter.setData(resp),
                        e -> Log.v("----","->",e)
                );
    }
}
