package com.znsd.water.waterapp.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.znsd.water.waterapp.fragment.Fragment_Main;


public class ChildViewPager extends ViewPager {

	private float startX;
	private float startY;

	public ChildViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ChildViewPager(Context context) {
		super(context);
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent arg0) {
//		try{
//		return super.onTouchEvent(arg0);
//		}catch(Exception e)
//		{
//		}
//		return false;
//	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		try {
			switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				startX = ev.getX();
				startY = ev.getY();

				break;
			case MotionEvent.ACTION_MOVE:

				int dx = (int) (ev.getX() - startX);
				int dy = (int) (ev.getY() - startY);
				if (Math.abs(dx) + Math.abs(dy) < 50) {
					return false;
				}
				int[] location = new int[2];
				View view = Fragment_Main.getInstance().getmViewFlowView();
				view.getLocationOnScreen(location);
				int x = location[0];
				int y = location[1];

				if (ev.getX() < x || ev.getX() > (x + view.getWidth())
						|| ev.getY() < y || ev.getY() > (y + view.getHeight())) {
					return true;
				} else {

					return false;
				}

			}
		} catch (Exception e) {
			return false;
		}
		return super.onInterceptTouchEvent(ev);
	}

}
