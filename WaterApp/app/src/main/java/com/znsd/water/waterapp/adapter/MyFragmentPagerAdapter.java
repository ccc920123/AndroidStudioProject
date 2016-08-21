package com.znsd.water.waterapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;



public class MyFragmentPagerAdapter extends FragmentPagerAdapter {


	    private List<Fragment> mFragments;
	    private FragmentManager fm;
	
	
	
	public MyFragmentPagerAdapter(FragmentManager  fm,List<Fragment> mFragments) {
		super(fm);
		this.fm=fm;
		this.mFragments=mFragments;
	}


	@Override
	public Fragment getItem(int position) {
		 
		return mFragments.get(position);
	}

	@Override
	public int getCount() {
		return mFragments.size();
	}
	
}
