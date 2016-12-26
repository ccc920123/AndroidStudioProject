package com.stardon.stardontemplateapp.model;

import android.app.Dialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stardon.stardontemplateapp.R;
import com.stardon.stardontemplateapp.model.base.CommonBaseActivity;

/**
 * @类名: BaseSysSettingActivity
 * @功能描述: .标识：BaseSysSettingActivity.java extend CommonBaseActivity.java
 * 1.界面包含了标题栏、功能栏、其它三个部分，以下将分别进行说明。
 * 2.标题栏
 * 要求：
 * 1）.动态由子类设置背景，尽量采用纯色，默认使用蓝色
 * 2）.动态由子类设置返回事件
 * 3.功能栏：包含了密码修改、清楚缓存、检查版本、帮助、语言设置、自定义view、退出功能
 * 要求：
 * 1）.密码修改
 * a.由子类设置点击事件，默认跳转进入模板一并传入修改密码标识，以及返回Activity标识;
 * b.由子类设置隐藏或显示，默认隐藏；
 * 2）清楚缓存
 * a.由子类实现清楚数据的方法；清楚完成给出Toast提示；
 * b.由子类设置隐藏或显示，默认隐藏；
 * 3)检查版本
 * a.版本号默认显示当前系统版本号
 * @作者:chepan
 * @时间: 2016/12/2
 * @版权申明:陈攀
 * @最后修改者:
 * @最后修改内容:
 */
public abstract class BaseSysSettingActivity extends CommonBaseActivity {

    // Content View Elements
    /**
     * 修改密码布局
     */
    private LinearLayout mChangepwd;
    /**
     * 清除缓存
     */
    private LinearLayout mClear;
    /**
     * 自动手动按钮
     */
    private ImageView mChiose;
    /**
     * 检查版本
     */
    private LinearLayout mCheck;
    /**
     * 版本
     */
    private TextView mVesion;
    /**
     * 帮助
     */
    private LinearLayout mHelp;
    /**
     * 语言
     */
    private LinearLayout mLanuage;
    /**
     * 退出
     */
    private LinearLayout mQuit;

    // End Of Content View Elements
    /**
     * 退出程序Dialog
     */
    private Dialog dialogQuit;

    @Override
    public int getContentLayout() {
        return R.layout.activity_base_sys_setting;
    }

    @Override
    public void initView() {
        mChangepwd = (LinearLayout) findViewById(R.id.changepwd);
        mClear = (LinearLayout) findViewById(R.id.clear);
        mChiose = (ImageView) findViewById(R.id.chiose);
        if (SharedPreferencesUtils.getBoolean(BaseSysSettingActivity.this,"auto",false)){
            mChiose.setImageResource(R.drawable.turnon);
        }else{
            mChiose.setImageResource(R.drawable.turnoff);
        }
        mCheck = (LinearLayout) findViewById(R.id.check);
        mVesion = (TextView) findViewById(R.id.vesion);
        mVesion.setText(getVersion());
        mHelp = (LinearLayout) findViewById(R.id.help);
        mLanuage = (LinearLayout) findViewById(R.id.lanuage);
        mQuit = (LinearLayout) findViewById(R.id.quit);
        mQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuiteDialog();
            }
        });
        mLanuage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageDialog();
            }
        });
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMemerous();
            }
        });
        mChiose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharedPreferencesUtils.getBoolean(BaseSysSettingActivity.this,"auto",false)){
                    mChiose.setImageResource(R.drawable.turnoff);
                    SharedPreferencesUtils.setBoolean(BaseSysSettingActivity.this,"auto",false);
                }else{
                    mChiose.setImageResource(R.drawable.turnon);
                    SharedPreferencesUtils.setBoolean(BaseSysSettingActivity.this, "auto", true);
                }
            }
        });
    }

    /**
     * @方法名称: languageDialog
     * @方法详述: 弹出选择语言对话框
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public void languageDialog() {


    }

    /**
     * @方法名称: getChangepwd
     * @方法详述: 得到修改密码的控件
     * @参数:
     * @返回值:LinearLayout
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public LinearLayout getChangepwd() {
        return mChangepwd;
    }



    /**
     * @方法名称: getClear
     * @方法详述: 得到清除缓存的控件
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public LinearLayout getClear() {
        return mClear;
    }

    /**
     * @方法名称: getChiose
     * @方法详述: 得到手动自动控件
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public ImageView getChiose() {
        return mChiose;
    }

    /**
     * @方法名称: getCheck
     * @方法详述: 得到检查版本的控件
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public LinearLayout getCheck() {
        return mCheck;
    }

    /**
     * @方法名称: getHelp
     * @方法详述: 得到帮助的控件
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public LinearLayout getHelp() {
        return mHelp;
    }

    /**
     * @方法名称: getLanuage
     * @方法详述: 得到语言设置的控件
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public LinearLayout getLanuage() {
        return mLanuage;
    }

    /**
     * @方法名称: getQuit
     * @方法详述: 得到退出的控件
     * @参数:
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public LinearLayout getQuit() {
        return mQuit;
    }

    /**
     * @方法名称: QuiteDialog
     * @方法详述:退出程序的Dialog
     * @参数: null
     * @返回值: void
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    private void QuiteDialog() {

        dialogQuit = new Dialog(this, R.style.MyDialogStyle_top);
        dialogQuit.setContentView(R.layout.dialog_exit);
        dialogQuit.setCanceledOnTouchOutside(true);

        // 取消弹出框
        dialogQuit.findViewById(R.id.otherView1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogQuit.cancel();
            }
        });

        // 取消弹出框
        dialogQuit.findViewById(R.id.otherView2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogQuit.cancel();
            }
        });

        // 退出当前用户
        dialogQuit.findViewById(R.id.exitLogin).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UserQuite();
            }
        });

        // 退出程序
        dialogQuit.findViewById(R.id.exitSystem).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogQuit.dismiss();
                finish();
                System.exit(0);
            }
        });
        dialogQuit.show();

    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return this.getString(R.string.version_name) + version;
        } catch (Exception e) {
            e.printStackTrace();
            return this.getString(R.string.can_not_find_version_name);
        }
    }

    /**
     * @方法名称: UserQuite
     * @方法详述: 退出当前用户
     * @参数: 子类重写这个方法实现退出当前用户的操作
     * @返回值:
     * @异常抛出 Exception:
     * @异常抛出 NullPointerException:
     */
    public void UserQuite() {
    }

    /**@方法名称:clearMemerous
    * @方法详述:清除缓存的方法子类重写
    * @参数:
    * @返回值:
    * @异常抛出 Exception:
    * @异常抛出 NullPointerException:
    */
    public void clearMemerous(){

    }
}
