package com.example.arthur.easysendler;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 03.07.2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fstFragment = new ArrayList<>();

    private final List<String> fstTiles = new ArrayList<>();


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fstFragment.get(position);
    }

    @Override
    public int getCount() {
        return fstTiles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fstTiles.get(position);
    }

    public void Addfragment(Fragment fragment, String title){

        fstFragment.add(fragment);
        fstTiles.add(title);

    }


}
