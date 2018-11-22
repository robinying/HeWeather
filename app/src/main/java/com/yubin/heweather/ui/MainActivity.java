package com.yubin.heweather.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yubin.heweather.R;
import com.yubin.heweather.bean.NowBean;
import com.yubin.heweather.bean.WeatherBean;
import com.yubin.heweather.config.Constant;
import com.yubin.heweather.model.CityDataSource;
import com.yubin.heweather.model.HeWeatherModel;
import com.yubin.heweather.model.Location;
import com.yubin.heweather.ui.base.BaseAppCompatActivity;
import com.yubin.heweather.ui.fragment.AirFragment;
import com.yubin.heweather.ui.fragment.MainFragment;
import com.yubin.heweather.ui.fragment.MultiCityChooseFragment;
import com.yubin.heweather.ui.main.HomePagerAdapter;
import com.yubin.heweather.ui.main.MainContract;
import com.yubin.heweather.ui.main.MainPresenter;
import com.yubin.heweather.utils.Basepreference;
import com.yubin.heweather.utils.ChangeCityEvent;
import com.yubin.heweather.utils.RxBus;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends BaseAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.coord)
    CoordinatorLayout coord;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private Location mLocation;
    private MainFragment mMainFragment;
    private AirFragment mAirFragment;
    private MultiCityChooseFragment mMultiCityChooseFragment;
    private CityDataSource cityDataSource;


    public static void show(Context context) {
        if (context != null) {
            context.startActivity(new Intent(context, MainActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityDataSource.showPickerView();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);
        HomePagerAdapter mAdapter = new HomePagerAdapter(getSupportFragmentManager());
        mMainFragment = new MainFragment();
        mAirFragment = new AirFragment();
        mMultiCityChooseFragment = new MultiCityChooseFragment();
        mAdapter.addTab(mMainFragment,getResources().getString(R.string.main_home_title));
        mAdapter.addTab(mAirFragment,getResources().getString(R.string.main_air_title));
        mAdapter.addTab(mMultiCityChooseFragment,getResources().getString(R.string.main_city_title));
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager,false);

    }

    @Override
    protected void initData() {
        super.initData();
        mLocation = new Location();
        //checkPermissions();
        initIcon();
        if (mLocation != null) {
            mLocation.startLocation();
        }
        cityDataSource = new CityDataSource(this);

    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_settings) {
            // Handle the camera action
            SettingsActivity.show(this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mLocation != null) {
            mLocation.stopLocation();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocation != null) {
            mLocation.destroyLocation();
        }
    }



    private void initIcon(){
        Basepreference.putInt("未知", R.drawable.ic_unknown_weather);
        Basepreference.putInt("晴", R.drawable.ic_sun);
        Basepreference.putInt("阴", R.drawable.ic_cloud);
        Basepreference.putInt("多云", R.drawable.ic_cloud);
        Basepreference.putInt("少云", R.drawable.ic_cloud);
        Basepreference.putInt("晴间多云", R.drawable.ic_cloud);
        Basepreference.putInt("小雨", R.drawable.ic_light_rain);
        Basepreference.putInt("中雨", R.drawable.ic_light_rain);
        Basepreference.putInt("大雨", R.drawable.ic_heavy_rain);
        Basepreference.putInt("阵雨", R.drawable.ic_thunder);
        Basepreference.putInt("雷阵雨", R.drawable.ic_thunder);
        Basepreference.putInt("霾", R.drawable.ic_fog);
        Basepreference.putInt("雾", R.drawable.ic_fog);
    }

}
