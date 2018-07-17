package com.example.arthur.easysendler;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.arthur.easysendler.screens.RecipientListFragment;
import com.example.arthur.easysendler.screens.SettingsFragment;
import com.example.arthur.easysendler.screens.TemplatesFragment;
import com.example.arthur.easysendler.screens.WelcomeFragment;
import com.example.arthur.easysendler.services.MailService;
import com.example.arthur.easysendler.utils.Provider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class BlankFragment extends Fragment {
    private String mMessage;

    Button backButton;
    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private com.getbase.floatingactionbutton.FloatingActionButton actionA;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMessage = getArguments().getString("fragment1_message");
        mailService = App.get(getContext()).getMailService();
    }


    MailService mailService;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstancestate) {
        View v = inflater.inflate(R.layout.fragment_blank, container,
                false);


        actionA = v.findViewById(R.id.btnrun);
        actionA.setOnClickListener((View b) -> {
//            if(mailService.getRecipientId() == null || mailService.getSettingId() == null || mailService.getTemplateId() == null ){
//                Toast.makeText(getContext(), "Settings unchanged", Toast.LENGTH_LONG).show();
//                return;
//            }


            String validationMessage = "";
            if (mailService.getRecipientId() == null ){
                validationMessage = "Recipients List";
            }

            if (mailService.getSettingId() == null){
                validationMessage = validationMessage.length() > 0
                        ? validationMessage + ", Settings"
                        : validationMessage + "Settings";
            }

            if (mailService.getTemplateId() == null){
                validationMessage = validationMessage.length() > 0
                        ? validationMessage + ", Template"
                        : validationMessage + "Template";
            }

            if(validationMessage.length() > 0){
                validationMessage = "Please, select " + validationMessage;
                Toast.makeText(getContext(), validationMessage, Toast.LENGTH_LONG).show();
                return;
            }

            //            if( mailService.getSettingId() == null || mailService.getTemplateId() == null){
//                Toast.makeText(getContext(),  "Please select setting" + "Please select template", Toast.LENGTH_LONG).show();
//                return;
//            }



//            if (mailService.getSettingId() == null){
//                Toast.makeText(getContext(), "Please select setting", Toast.LENGTH_LONG).show();
//                return;
//            }
//            if (mailService.getTemplateId() == null){
//                Toast.makeText(getContext(), "Please select template", Toast.LENGTH_LONG).show();
//                return;
//            }

            Provider.Api.getMailApi().run(mailService.getRecipientId(),mailService.getTemplateId(),mailService.getSettingId())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                            resp -> {
                                Toast.makeText(getContext(), "Complite!!!!", Toast.LENGTH_LONG).show();
                            },
                            e -> Log.v("----", "->", e)
                    );

            Toast.makeText(getContext(), "Start running...", Toast.LENGTH_LONG).show();

        });


//        backButton = v.findViewById(R.id.back_button);
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                WelcomeFragment fragment1 = new WelcomeFragment();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//
//                transaction.replace(R.id.fragment_container, fragment1);
//                transaction.commit();
//
//            }
//        });


        tablayout = v.findViewById(R.id.tablayout);
        viewPager = v.findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getChildFragmentManager());


        //add fragment

        adapter.Addfragment(new RecipientListFragment(), "Recipient List");
        adapter.Addfragment(new SettingsFragment(), "Settings List");
        adapter.Addfragment(new TemplatesFragment(), "Templates List");

        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);


        return v;


    }

}
