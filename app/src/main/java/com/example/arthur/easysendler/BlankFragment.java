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
        actionA.setOnClickListener(b -> {
            if(mailService.getRecipientId() == null || mailService.getSettingId() == null || mailService.getTemplateId() == null ){
                Toast.makeText(getContext(), "Settings unchanged", Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(getContext(), "Settings changed", Toast.LENGTH_LONG).show();

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

        adapter.Addfragment(new RecipientListFragment(), "RecipientList Fragment");
        adapter.Addfragment(new SettingsFragment(), "Settings Fragment");
        adapter.Addfragment(new TemplatesFragment(), "Templates Fragment");

        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);


        return v;


    }

}
