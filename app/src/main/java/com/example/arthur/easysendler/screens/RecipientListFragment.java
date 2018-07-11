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

import com.example.arthur.easysendler.R;
import com.example.arthur.easysendler.RecipientAdapter;
import com.example.arthur.easysendler.api.MailApi;
import com.example.arthur.easysendler.utils.Provider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RecipientListFragment extends Fragment {

    View v;
    MailApi api;
    private RecyclerView myrecyclerview;
    RecipientAdapter recipientAdapter;

    public RecipientListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_recipient_list, container, false);

        myrecyclerview = v.findViewById(R.id.rllst_recyclerview);


        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recipientAdapter);
        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recipientAdapter = new RecipientAdapter(getContext());
        api = Provider.Api.getMailApi();
        api.getRecipientList(10, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        resp -> recipientAdapter.setData(resp),
                        e -> Log.v("----","->",e)
                );



    }
}
