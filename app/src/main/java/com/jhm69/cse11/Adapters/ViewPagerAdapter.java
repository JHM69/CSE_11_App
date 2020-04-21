package com.jhm69.cse11.Adapters;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jhm69.cse11.Fragment.FragmentFiles;
import com.jhm69.cse11.Fragment.FragmentHome;
import com.jhm69.cse11.Fragment.FragmentNotice;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position ==0) {
            return new FragmentHome();
        } else if(position == 1){
            return new FragmentNotice();
        } else{
            return new FragmentFiles();
        }
    }



    @Override
    public int getCount() {
        return 3;
    }
}