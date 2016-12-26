package com.stardon.stardontemplateapp.welcome.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stardon.stardontemplateapp.R;

public class BaseWelcomeActivity extends Activity {
    /**
     * BaseWelcomeActivity的Activity
     */
    private RelativeLayout mActivity;
    /**
     * 系统应用图标
     */
    private ImageView mSystemIcon;
    /**
     * 主标题
     */
    private TextView mMianTitle;
    /**
     * 副标题
     */
    private TextView mVicetitle;
    /**
     * 版权显示
     */
    private TextView mCopyRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_welcome);
        bindViews();
    }


    /**
     * <br/> 方法名称: bindViews
     * <br/> 方法详述: 初始化控件
     * <br/> 参数:
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    private void bindViews() {

        mActivity = (RelativeLayout) findViewById(R.id.activity_base_welcome);
        mSystemIcon = (ImageView) findViewById(R.id.system_icon);
        mMianTitle = (TextView) findViewById(R.id.miantitle);
        mVicetitle = (TextView) findViewById(R.id.vicetitle);
        mCopyRight = (TextView) findViewById(R.id.copyright);
    }

    /**
     * <br/> 方法名称:  getmSystemIcon
     * <br/> 方法详述: 获取系统应用图标
     * <br/> 参数:
     * <br/> 返回值:mSystemIcon 系统应用图标的引用
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    public ImageView getmSystemIcon() {
        return mSystemIcon;
    }

    /**
     * <br/> 方法名称: getmMainTitle
     * <br/> 方法详述: 获取主标题控件
     * <br/> 参数:
     * <br/> 返回值:  mMainTitle，主标题控件
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    public TextView getmMianTitle() {
        return mMianTitle;
    }

    /**
     * <br/> 方法名称:  getmVicetitle
     * <br/> 方法详述: 获取副标题控件
     * <br/> 参数:
     * <br/> 返回值:mVicetitle 系统副标题的引用
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    public TextView getmVicetitle() {
        return mVicetitle;
    }

    /**
     * <br/> 方法名称:  getmCopyRight
     * <br/> 方法详述: 获取版权控件
     * <br/> 参数:
     * <br/> 返回值:mCopyRight 系统版权的引用
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    public TextView getmCopyRight() {
        return mCopyRight;
    }

    /**
     * <br/> 方法名称:setActivityBackgroud
     * <br/> 方法详述:给欢迎界面设置背景图片
     * <br/> 参数:id，背景图片的资源id。
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    public void setActivityBackgroud(int id) {
        mActivity.setBackground(getResources().getDrawable(id));
    }
}
