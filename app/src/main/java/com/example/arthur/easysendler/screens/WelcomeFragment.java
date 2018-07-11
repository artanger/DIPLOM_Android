package com.example.arthur.easysendler.screens;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.arthur.easysendler.R;

/**
 * Created by Arthur on 20.06.2018.
 */

public class WelcomeFragment extends Fragment {

    OnFragment1ClickListener mCallback;
    Button button1;

    public interface OnFragment1ClickListener
    {
        void onFragment1Message( String message);

    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
            mCallback = (OnFragment1ClickListener)context;

        }catch(ClassCastException e)
        {
            throw new ClassCastException(context.toString() +
                    " must Implement OnFragment1ClickListener");
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstancestate)
    {

        View v = inflater.inflate(R.layout.welcomefragment,container,
                false);
        button1 = (Button) v.findViewById(R.id.nextpage);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onFragment1Message("Button1");
            }
        });


        return v;




    }

}
