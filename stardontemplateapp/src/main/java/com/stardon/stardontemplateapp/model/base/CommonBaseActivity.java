package com.stardon.stardontemplateapp.model.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.stardon.stardontemplateapp.R;

public abstract class CommonBaseActivity extends AppCompatActivity implements IBase {
    // Content View Elements

    private Toolbar mToolsbar;
    private ImageView mBtnLeft;
    private TextView mTvToolbar;

    private ImageView mBtnRight;

    protected View mRootView;
    private FrameLayout mFrameLayout;
    // End Of Content View Elements


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_base);
        bindViews(savedInstanceState);
        initView();//初始化控件
        initData();//初始化数据
    }

    /**
     * @方法名称: 找控件
     * @方法详述:
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    private void bindViews(Bundle savedInstanceState) {
        mRootView = createView(null, null, savedInstanceState);
        mFrameLayout = (FrameLayout) findViewById(R.id.layout);
        mToolsbar = (Toolbar) findViewById(R.id.toolsbar);
        mBtnLeft = (ImageView) findViewById(R.id.btnLeft);
        mTvToolbar = (TextView) findViewById(R.id.tv_toolbar);
        mBtnRight = (ImageView) findViewById(R.id.btnRight);
        mFrameLayout.addView(mRootView);
        setSupportActionBar(mToolsbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(this).inflate(getContentLayout(), null);
        return view;
    }

    /**
     * @方法名称: 得到Toolsbar
     * @方法详述:
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public Toolbar getToolsbar() {
        return mToolsbar;
    }

    /**
     * @方法名称: 得到左边按钮
     * @方法详述:
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public ImageView getBtnLeft() {
        return mBtnLeft;
    }

    /**
     * @方法名称: 得到标题
     * @方法详述:
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public TextView getTvToolbar() {
        return mTvToolbar;
    }

    /**
     * @方法名称: 得到右边按钮
     * @方法详述:
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public ImageView getBtnRight() {
        return mBtnRight;
    }
    /**@方法名称: setTitle
    * @方法详述: 设置标题
    * @参数: 
    * @返回值: 
    * @异常抛出 Exception:
    * @异常抛出 NullPointerException:
    */
    public void setTitle(int rse) {
        mTvToolbar.setText(rse);
    }
}
