package com.jhm69.cse11.Activity;

import android.os.*;

import androidx.core.view.*;
import androidx.appcompat.app.*;
import androidx.appcompat.widget.*;

import com.google.android.material.tabs.TabLayout;
import com.jhm69.cse11.Adapters.ViewPagerAdapterOfSlybs;
import com.jhm69.cse11.R;

import androidx.viewpager.widget.ViewPager;

public class SlybsActivity extends AppCompatActivity {

    private ViewPager viewPager2;
    
    private TabLayout tabLayout2;
    private String[] pageTitle2 = {"1st Year", "2nd Year","3rd Year","4th Year"};


	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slybs);

        final ViewPager viewPager2 = (ViewPager)findViewById(R.id.view_pager2);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);


        setSupportActionBar(toolbar2);

        //create default navigation drawer toggle
        
        //setting Tab update_class_edit (number of Tabs = number of ViewPager pages)
        tabLayout2 = (TabLayout) findViewById(R.id.tab_layout2);
        for (int i = 0; i < 4; i++) {
            tabLayout2.addTab(tabLayout2.newTab().setText(pageTitle2[i]));
        }

        //set gravity for tab bar
        tabLayout2.setTabGravity(TabLayout.GRAVITY_FILL);

        //handling navigation view item event
        
        
        //set viewpager adapter
        ViewPagerAdapterOfSlybs pagerAdapter2 = new ViewPagerAdapterOfSlybs(getSupportFragmentManager());
        viewPager2.setAdapter(pagerAdapter2);



        //change Tab selection when swipe ViewPager
        viewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout2));

        //change ViewPager page when tab selected
        tabLayout2.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
				@Override
				public void onTabSelected(TabLayout.Tab tab) {
					viewPager2.setCurrentItem(tab.getPosition());
				}

				@Override
				public void onTabUnselected(TabLayout.Tab tab) {

				}

				@Override
				public void onTabReselected(TabLayout.Tab tab) {

				}
            });


    }

}
