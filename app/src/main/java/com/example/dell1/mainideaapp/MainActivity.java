package com.example.dell1.mainideaapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;  //declaring viewPager
    TabLayout tabLayout;  //declaring tabLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //setting up with viewpager and tablayout for main screen
        viewPager=findViewById(R.id.viewPager);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

 public class MyPagerAdapter extends FragmentPagerAdapter{


        FragmentMyActivity fragmentMyActivity=new FragmentMyActivity();
        FragmentMyGroups fragmentMyGroups=new FragmentMyGroups();
        FragmentFavourites fragmentFavourites=new FragmentFavourites();

        MyPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0:
                    return "My Activity" ;
                case 1:
                    return  "My Groups" ;
                case 2:
                    return  "Favourites" ;
            }
            return "";
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return fragmentMyActivity;
                case 1:
                    return fragmentMyGroups;
                case 2:
                    return fragmentFavourites;
            }
            return null;
        }
        @Override
        public int getCount() {
            return 3;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_MyActivity) {
            // Handle the camera action
        } else if (id == R.id.nav_MyGroups) {

        } else if (id == R.id.nav_Favourites) {

        } else if (id == R.id.nav_ShareWithOthers) {

        } else if (id == R.id.nav_LogOut) {

        } else if (id == R.id.nav_sendFeedBack) {

        } else if (id == R.id.nav_RateMyApp) {

        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
