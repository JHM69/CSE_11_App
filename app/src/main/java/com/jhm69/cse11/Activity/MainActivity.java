package com.jhm69.cse11.Activity;

import android.content.*;
import android.net.*;
import android.os.*;

import androidx.core.view.*;
import androidx.core.widget.*;
import androidx.appcompat.app.*;
import androidx.appcompat.widget.*;
import android.view.*;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.jhm69.cse11.Adapters.ViewPagerAdapter;
import com.jhm69.cse11.R;

import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ViewPager viewPager;
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private String[] pageTitle = {"Home", "Notices","Files"};


    private CardView student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);

        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (int i = 0; i < 3; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
            TextView tv = (TextView)LayoutInflater.from(this).inflate(R.layout.custom_tab,null);
            tabLayout.getTabAt(i).setCustomView(tv);
        }

        //set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //handling navigation view item event
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        //set viewpager adapter
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);



        //change Tab selection when swipe ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //change ViewPager page when tab selected
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        displaySelectedScreen(id);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displaySelectedScreen(int itemId) {

        switch (itemId) {
            case R.id.meme:
                try {
                    Intent sharingIntent = new Intent(Intent.ACTION_VIEW);
                    sharingIntent.setType("URL");
                    sharingIntent.setData(Uri.parse("fb://facewebmodal/f?href=https://m.facebook.com/coder.bhai.jnu"));
                    sharingIntent.setPackage("com.facebook.katana");
                    startActivity(sharingIntent);
                } catch (ActivityNotFoundException e) {
                    Uri urin = Uri.parse("https://m.facebook.com/coder.bhai.jnu");
                    Intent goToMarketn = new Intent(Intent.ACTION_VIEW, urin);
                    startActivity(goToMarketn);
                }


                break;
            case R.id.dev:

                Intent intent = new Intent(getApplicationContext(), AboutActivity .class);
                startActivity(intent);



                break;
            case R.id.site:

                Uri ur= Uri.parse("https://www.jnu.ac.bd/dept/portal/web/cse");
                Intent goToMarke = new Intent(Intent.ACTION_VIEW, ur);
                startActivity(goToMarke);

                break;
            case R.id.stu:
                Intent inten = new Intent(getApplicationContext(), StudentListActivity.class);
                startActivity(inten);
                break;

            case R.id.dsite:
                Uri uri = Uri.parse("https://www.jnu.ac.bd/");
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(goToMarket);
                break;

            case R.id.exit:
                finish();
                break;


        }
    }


    @Override
    public void onBackPressed() {
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}