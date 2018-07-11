package com.example.arthur.easysendler;



import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.arthur.easysendler.screens.WelcomeFragment;


public class MainActivity extends AppCompatActivity
        implements WelcomeFragment.OnFragment1ClickListener   {


    public void onFragment1Message( String message)
    {

        BlankFragment fragment2 = new BlankFragment();
        Bundle args = new Bundle();
        args.putString("fragment1_message",message);
        fragment2.setArguments(args);


        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        transaction
                .replace(R.id.fragment_container, fragment2);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.fragment_container) != null)
        {

            WelcomeFragment fragment1 = new WelcomeFragment();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.add(R.id.fragment_container, fragment1);
            transaction.commit();

        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);






    }


}
