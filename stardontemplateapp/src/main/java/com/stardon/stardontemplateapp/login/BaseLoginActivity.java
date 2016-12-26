package com.stardon.stardontemplateapp.login;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.stardon.stardontemplateapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名: {@link BaseLoginActivity}
 * <br/> 功能描述:登录界面
 * <br/> 作者: MouTao
 * <br/> 时间: 2016/12/2
 * <br/> 最后修改者:
 * <br/> 最后修改内容:
 */
public class BaseLoginActivity extends Activity {
    /**
     * 登录界面的xml
     */
    private LinearLayout mActivity;
    /**
     * 应用图标
     */
    private ImageView mLoginIcon;
    /**
     * 应用名
     */
    private TextView mSysteName;
    /**
     * 用户头像图标
     */
    private ImageView mUsericon;
    /**
     * 用户账户
     */
    private EditText mUserCount;
    /**
     * 清楚按钮
     */
    private ImageView mClean;
    /**
     * 密码图标
     */
    private ImageView mPassIcon;
    /**
     * 用户密码
     */
    private EditText mPassword;
    /**
     * 可看密码图标
     */
    private ImageView mPassSee;
    /**
     * 错误提示
     */
    private TextView mErrorView;
    /**
     * 登录按钮
     */
    private Button mLogin;
    /**
     * 其他按钮
     */
    private Button mOther;
    /**
     * 版权
     */
    private TextView mCopyRight;
    /**
     * 密码是否可见
     */
    private boolean isSee = false;
    /**
     * 密码是否可见
     */
    private boolean isShowing = false;
    /**
     * 用户名的下拉菜单
     */
    private PopupWindow popupWindow = null;

    /**
     *
     */
    private SharedPreferencesUtils userSharedP;
    List<UserCount> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_login);
        bindViews();
//
//        List<UserCount> list = new ArrayList<UserCount>();
//        list.add(new UserCount("哈哈哈", ""));
//        list.add(new UserCount("嘎嘎嘎", ""));
//        list.add(new UserCount("放大放大", ""));

        getUserCountList();

    }

    private void getUserCountList() {
        String userString = (String) SharedPreferencesUtils.getParam(BaseLoginActivity.this, 
                "USER_LIST", "");
        if (userString == null || "".equals(userString)) {
            return;
        }
        list = new ArrayList<UserCount>();
        String[] ss = userString.split(",");
        for (int i = 0; i < ss.length; i++) {
            list.add(new UserCount(ss[i], ""));
            if (i == 5) {
                return;
            }
        }
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

        mActivity = (LinearLayout) findViewById(R.id.activity_base_login);
        mPassword = (EditText) findViewById(R.id.password);
        mUserCount = (EditText) findViewById(R.id.usercount);

        mClean = (ImageView) findViewById(R.id.clean);
        mUsericon = (ImageView) findViewById(R.id.usericon);
        mLoginIcon = (ImageView) findViewById(R.id.loginicon);
        mPassSee = (ImageView) findViewById(R.id.passsee);
        mPassIcon = (ImageView) findViewById(R.id.pass_icon);

        mSysteName = (TextView) findViewById(R.id.syste_name);
        mErrorView = (TextView) findViewById(R.id.errorview);
        mCopyRight = (TextView) findViewById(R.id.copyright);
        mLogin = (Button) findViewById(R.id.login);
        mOther = (Button) findViewById(R.id.otherbtn);

        mClean.setOnClickListener(baseclick);
        mPassSee.setOnClickListener(baseclick);
//        mUserCount.addTextChangedListener(textWatcher);
        mUserCount.setOnClickListener(baseclick);
    }


    /**
     * 点击事件
     */
    private View.OnClickListener baseclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mClean) {
                mUserCount.setText("");
            } else if (view == mPassSee) {
                setPasswordCanSee();
            } else if (view == mUserCount) {
                if (list.size() != 0) {
                    showPopWindow();
                }
            }
        }
    };

    /**
     * <br/> 方法名称: setPasswordCanSee
     * <br/> 方法详述: 设置密码是否可见
     * <br/> 参数:
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    private void setPasswordCanSee() {
        if (!isSee) {
            mPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            mPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType
                    .TYPE_TEXT_VARIATION_PASSWORD);
        }
        isSee = !isSee;
        mPassword.setSelection(mPassword.getText().length());
    }

    /**
     * 用户名称的内容监听
     */
//    private TextWatcher textWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            mUserCount.requestFocus();
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            mUserCount.requestFocus();
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//            mUserCount.requestFocus();
//        }
//    };

    /**
     * 弹出系统菜单
     */
    private void showPopWindow() {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);

        // 加载需要显示的布局
        View view = layoutInflater.inflate(R.layout.user_count_list, null);
        ListView lv_group = (ListView) view.findViewById(R.id.lvGroup);

        // 加载数据
        UserCount groups = new UserCount();
        // 加载图标


//        List<UserCount> list = JSONArray.toList(JSONArray.fromObject(userString), UserCount
// .class);

        UserCountAdapter groupAdapter = new UserCountAdapter(BaseLoginActivity.this, list);
        lv_group.setAdapter(groupAdapter);

        // 创建一个PopuWidow对象
        popupWindow = new PopupWindow(view, mUserCount.getWidth(), ViewGroup.LayoutParams
                .WRAP_CONTENT);// 宽度改数字
        // 使其聚集
//        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
//        popupWindow.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
        // int xPos = windowManager.getDefaultDisplay().getWidth() / 2 -
        // popupWindow.getWidth() / 2;

        popupWindow.showAsDropDown(findViewById(R.id.usercount), 0, 0);
        // popupWindow.showAtLocation(findViewById(R.id.seting), Gravity.TOP,
        // 220, 0);
//        popupWindow.setAnimationStyle(R.style.AnimationFade);

        lv_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                mUserCount.setText(list.get(position).getUserName());
                mUserCount.setSelection(mUserCount.getText().length());
                popupWindow.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }

        super.onBackPressed();
    }

    public LinearLayout getmActivity() {
        return mActivity;
    }

    public ImageView getmLoginIcon() {
        return mLoginIcon;
    }

    public TextView getmSysteName() {
        return mSysteName;
    }

    public ImageView getmUsericon() {
        return mUsericon;
    }

    public EditText getmUserCount() {
        return mUserCount;
    }

    public ImageView getmClean() {
        return mClean;
    }

    public ImageView getmPassIcon() {
        return mPassIcon;
    }

    public EditText getmPassword() {
        return mPassword;
    }

    public ImageView getmPasssee() {
        return mPassSee;
    }

    public TextView getmErrorView() {
        return mErrorView;
    }

    public Button getmLogin() {
        return mLogin;
    }

    public Button getmOther() {
        return mOther;
    }

    public TextView getmCopyRight() {
        return mCopyRight;
    }
}
