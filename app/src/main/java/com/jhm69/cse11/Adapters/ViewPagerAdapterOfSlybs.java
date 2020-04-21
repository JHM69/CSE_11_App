package com.jhm69.cse11.Adapters;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jhm69.cse11.Fragment.FragmentSlybsFirstYear;
import com.jhm69.cse11.Fragment.FtagmentSlybsSecondYear;
import com.jhm69.cse11.Fragment.FtagmentSlybsThirdYear;
import com.jhm69.cse11.Fragment.FtagmentSlybsFourthYear;

public class ViewPagerAdapterOfSlybs extends FragmentPagerAdapter {

    public ViewPagerAdapterOfSlybs(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position ==0) {
            return new FragmentSlybsFirstYear();
        } else if(position == 1){
            return new FtagmentSlybsSecondYear();
        } else if(position == 2){
			return new FtagmentSlybsThirdYear();
		}else{
			return new FtagmentSlybsFourthYear();
		}
	}



    @Override
    public int getCount() {
        return 4;
    }
}
