package com.znsd.water.waterapp.application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.znsd.water.waterapp.R;

public class BaseActivity extends FragmentActivity {
	protected Context mContext;
	private RelativeLayout mTitleLayout;
	private TextView mTitleService;
	private TextView mTitleText;
	private LinearLayout llyContentView;
	private TextView submit;



	public static BaseActivity getInitialize() {

		BaseActivity base = new BaseActivity();

		return base;

	}

	public void back(View v) {
		finish();
	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(R.layout.baseactivity);
		mTitleLayout = (RelativeLayout) findViewById(R.id.title_layout);
		mTitleService = (TextView) findViewById(R.id.service);
		mTitleText = (TextView) findViewById(R.id.title_title);
		submit = (TextView) findViewById(R.id.submit);
		llyContentView = (LinearLayout) findViewById(R.id.llyContentView);


	}



	/**
	 * 隐藏标题
	 */
	public void hideTitle(boolean ishide) {
		if (ishide) {
			mTitleLayout.setVisibility(View.GONE);
		}
	}



	/**
	 * 更改标题
	 */
	public void changeTitleText(int resid) {
		mTitleText.setText(resid);
	}

	/**
	 * 更改标题
	 */
	public void changeTitleText(String resid) {
		mTitleText.setText(resid);
	}

	/**
	 * 得到返回键
	 */
	public TextView getTitleBack() {
		return mTitleService;
	}

	/**
	 * 得到消息
	 */
	public TextView getSubmitTv() {
		return submit;
	}








	/**
	 * 通过类名启动Activity
	 *
	 * @param pClass
	 */
	public void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	/**
	 * 通过类名启动Activity，并且含有Bundle数据
	 *
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




}
