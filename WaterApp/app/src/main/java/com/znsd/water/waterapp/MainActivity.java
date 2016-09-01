package com.znsd.water.waterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.znsd.water.waterapp.adapter.MyFragmentPagerAdapter;
import com.znsd.water.waterapp.application.BaseActivity;
import com.znsd.water.waterapp.fragment.Fragment_Business;
import com.znsd.water.waterapp.fragment.Fragment_Main;
import com.znsd.water.waterapp.fragment.Fragment_Pay;
import com.znsd.water.waterapp.fragment.Fragment_Profile;
import com.znsd.water.waterapp.widget.ChildViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener,RadioGroup.OnCheckedChangeListener {



    private RadioGroup rg_tab_bar;
    private RadioButton rb_main;
    private RadioButton rb_business;
    private RadioButton rb_profile;
    private RadioButton rb_pay;
    private ChildViewPager vpager;


    // 几个代表页面的常量
    public static final int PAGE_MAIN = 0;
    public static final int PAGE_BUSINESS = 1;
    public static final int PAGE_PROFILE = 2;
    public static final int PAGE_PAY = 3;

    private MyFragmentPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
        bindViews();
        init();


    }




    private void bindViews() {
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_main = (RadioButton) findViewById(R.id.rb_main);
        rb_business = (RadioButton) findViewById(R.id.rb_business);
        rb_profile = (RadioButton) findViewById(R.id.rb_profile);
        rb_pay = (RadioButton) findViewById(R.id.rb_pay);
        vpager = (ChildViewPager) findViewById(R.id.vpager);

    }


    private void init() {
        rb_main.setChecked(true);
        List<Fragment> mFragments = new ArrayList<Fragment>();
        mFragments.add(Fragment_Main.getInstance());
        mFragments.add(new Fragment_Business());
        mFragments.add(new Fragment_Profile());
        mFragments.add(new Fragment_Pay());
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),
                mFragments);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.setOnPageChangeListener(this);
        rg_tab_bar.setOnCheckedChangeListener(this);



    }

    /**
     * 跳转页面
     * @param pClass
     * @param pBundle
     */
    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_main:
                vpager.setCurrentItem(PAGE_MAIN);
                break;
            case R.id.rb_business:

                vpager.setCurrentItem(PAGE_BUSINESS);
                break;
            case R.id.rb_profile:

                vpager.setCurrentItem(PAGE_PROFILE);
                break;
            case R.id.rb_pay:
                vpager.setCurrentItem(PAGE_PAY);
                break;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
// state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_MAIN:
                    rb_main.setChecked(true);
                    break;
                case PAGE_BUSINESS:

                    rb_business.setChecked(true);
                    break;
                case PAGE_PROFILE:

                    rb_profile.setChecked(true);
                    break;
                case PAGE_PAY:
                    rb_pay.setChecked(true);
                    break;
            }
        }
    }
}
