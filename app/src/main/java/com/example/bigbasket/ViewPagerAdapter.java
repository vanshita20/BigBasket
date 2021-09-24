package com.example.bigbasket;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List nFragmentList = new ArrayList();
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return (Fragment)nFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return nFragmentList.size();
    }
     public void addFrag(Fragment fragment){
        nFragmentList.add(fragment);
     }
}
