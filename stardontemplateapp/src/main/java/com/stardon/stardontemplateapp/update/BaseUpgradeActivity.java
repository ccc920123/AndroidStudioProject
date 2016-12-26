package com.stardon.stardontemplateapp.update;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.stardon.stardontemplateapp.R;
import com.stardon.stardontemplateapp.model.base.CommonBaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 类名:  BaseUpgradeActivity
 * <br/> 功能描述:
 * <br/>      1.此模块主要分为标题栏、版本显示、升级操作、其它三个部分，以下将分别进行说明。
 * <br/>      2. 标题栏
 * <br/>       要求：
 * <br/>         1）.由子类设置标题栏名称，默认‘系统更新’
 * <br/>         2）.由子类设计标题栏背景颜色，默认UI设计颜色（蓝色）;
 * <br/>     3.版本显示
 * <br/>      要求：
 * <br/>         1）.最新版本号由子类动态设置
 * <br/>         2）.最新版本说明内容由子类动态设置，并且实现内容自动换行的功能
 * <br/>         3）.当前版本LOGO、当前版本号（本地获取）
 * <br/>     4.升级操作
 * <br/>      要求：
 * <br/>         1）.说明：
 * <br/>                 a.下载APK URL有子类设置;
 * <br/>                 b.在下载的过程中显示下载进度条以及下载进度值，进度值以x%的格式显示；
 * <br/>                 c.停止下载：在下载的过程中可以中断下载，‘停止下载’按钮的显示由子类决定，如果是强制升级则隐藏，如果不是则显示；
 * <br/>                 d.下载成功后自动跳转进入应用安装界面，导航安装；
 * <br/>     5.其它
 * <br/>       要求：
 * <br/>         1.字体、大小都设置好，不由子类设置；
 * <br/>
 * <br/> 设置新版本号：setNewVersionNum(String newNum)
 * <br/> 设置下载链接：setURL(String url)
 * <br/> 设置文件路径：setFilePath(String path)
 * <br/> 设置apk的名称：setApkName(String name)
 * <br/> 设置是否必须升级：setIsMustUpdate(boolean b)
 * <br/> 设置旧版本号：setOldVersionNum(String oldNum)
 * <br/> 设置版本描述：setVersionContent(String content)
 * <br/> 设置系统图标：setSystemIcon(int id)
 * <br/> 作者: MouTao
 * <br/> 时间: 2016/12/6
 * <br/> 最后修改者:
 * <br/> 最后修改内容:
 */
public abstract class BaseUpgradeActivity extends CommonBaseActivity implements IUpgrade {
    /**
     * 版本号
     */
    public static final String VSESION_NUMBER = "version_number";
    /**
     * 版本描述
     */
    public static final String VSESION_CONTENT = "version_content";
    /**
     * 下载链接
     */
    public static final String VSESION_URL = "version_url";
    /**
     * 是否强制升级的标识符
     */
    public static final String VSESION_MUST = "version_must";
    /**
     * 当前Activity
     */
    private LinearLayout mActivity;
    /**
     * 图标
     */
    private ImageView mSystemIcon;
    /**
     * 旧版本号
     */
    private TextView mOldVersion;
    /**
     * 新版本号
     */
    private TextView mNewVersion;
    /**
     * 版本描述
     */
    private TextView mContent;
    /**
     * 升级按钮
     */
    private Button mUpdate;
    /**
     * 下载链接
     */
    private String URL = "https://downapp.baidu.com/appsearch/AndroidPhone/1.0.59\" +\n" +
            "                \".203/1/1012271b/20161031115409/appsearch_AndroidPhone_1-0-59" +
            "-203_1012271b\" +\n" +
            "                \".apk?responseContentDisposition=attachment%3Bfilename%3D\" +\n" +
            "                \"%22appsearch_AndroidPhone_1012271b" +
            ".apk%22&responseContentType=application%2Fvnd\" +\n" +
            "                \".android" +
            ".package-archive&request_id=1481008164_4123365920&type=static";
    /**
     * 是否需要强制升级
     */
    private boolean isNeed = false;
    /**
     * 下载框
     */
    private MyDialog mDialog;
    /**
     * 停止下载
     */
    private Button mStopBtn;
    /**
     * 下载进度百分比
     */
    private TextView mPercent;
    /**
     * 进度条
     */
    private ProgressBar mProsBar;
    /**
     * 下载线程是否正在执行
     */
    boolean isAsycn = false;
    /**
     * 文件保存路径
     */
    private String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File
            .separator +
            "wyc" + File.separator;
    /**
     * APK名称
     */
    private String ApkName = "default.apk";
    /**
     * 文件长度
     */
    int length = 0;

    /**
     * <br/> 方法名称: initView
     * <br/> 方法详述: 初始化控件
     * <br/> 参数:
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    @Override
    public void initView() {
        setIsMustUpdate(isNeed);
        mActivity = (LinearLayout) findViewById(R.id.activity_base_upgrade);
        mSystemIcon = (ImageView) findViewById(R.id.system_icon);
        mOldVersion = (TextView) findViewById(R.id.oldversion);
        mNewVersion = (TextView) findViewById(R.id.newversion);
        mContent = (TextView) findViewById(R.id.content);
        mUpdate = (Button) findViewById(R.id.update);
        
        /*设置下载按钮的点击事件*/
        mUpdate.setOnClickListener(click);
        /*如果为强制升级，则隐藏停止下载按钮*/
        if (isNeed) {
            mStopBtn.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * <br/> 方法名称: initData
     * <br/> 方法详述: 初始化数据，界面
     * <br/> 参数:
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    @Override
    public void initData() {
        /*设置标题*/
        this.getTvToolbar().setText("版本升级");
        getIntentData();
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_base_upgrade;
    }

    /**
     * <br/> 方法名称: initDialog
     * <br/> 方法详述: 初始化dialog
     * <br/> 参数:
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    private void initDialog() {
        mDialog = new MyDialog(this, R.style.MyDialog);
        mDialog.setContentView(R.layout.dailog_upgread);

        mProsBar = (ProgressBar) mDialog.findViewById(R.id.sys_pro);
        mPercent = (TextView) mDialog.findViewById(R.id.percent);
        mStopBtn = (Button) mDialog.findViewById(R.id.stop);
        mStopBtn.setOnClickListener(click);

        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        lp.width = (int) (d.getWidth() * 0.8); // 宽度
        lp.alpha = 1.0f; // 透明度

        dialogWindow.setAttributes(lp);
        mDialog.show();
    }

    /**
     * 异步下载操作
     */
    private OnLoadData_Updata downAsyn;
    /**
     * 点击事件
     */
    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mUpdate) {
                if ("".equals(filePath)) {
                    Toast.makeText(BaseUpgradeActivity.this, "请设置文件保存路径", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                initDialog();
                downAsyn = new OnLoadData_Updata();
                downAsyn.execute();

            } else if (v == mStopBtn) {
                stopDownload();
            }
        }
    };

    /**
     * <br/> 方法名称: stopDownload
     * <br/> 方法详述: 停止下载
     * <br/> 参数:
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    private void stopDownload() {
        if (!downAsyn.cancel(true)) {
            downAsyn = new OnLoadData_Updata();
            downAsyn.execute();
        }
    }

    class OnLoadData_Updata extends AsyncTask<String, Integer, Integer> {

        @Override
        protected void onPostExecute(Integer msu) {
            // TODO Auto-generated method stub
            super.onPostExecute(msu);
            isAsycn = false;
            switch (msu) {
                case 1:
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setDataAndType(Uri.fromFile(new File(filePath + "APK/" + ApkName)), 
                            "application/vnd.android.package-archive");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    System.gc();
                    mDialog.dismiss();
                    finish();
                    break;
                case 0:
                    Toast.makeText(BaseUpgradeActivity.this, "升级失败，请重试!", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case 2:
                    Toast.makeText(BaseUpgradeActivity.this, "升级异常，请重新启动软件再进行操作!", Toast
                            .LENGTH_SHORT).show();
                    break;
            }
        }

        /*
         * 更新界面
         */
        @SuppressWarnings("unused")
        @Override
        protected void onProgressUpdate(Integer... values) {
            float fileLength = ((float) length / 1024 / 1024);
            mProsBar.setProgress(values[0]);
            mPercent.setText("(" + values[0] + "%" + ")");
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }

        @Override
        protected Integer doInBackground(String... arg0) {
            isAsycn = true;
            try {
                System.out.println("下载地址:" + URL);
                URL url = new URL(URL);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.connect();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream is = conn.getInputStream();
                    File file = new File(filePath + "APK");
                    if (!file.exists()) {
                        file.mkdirs();
                    }

                    FileOutputStream fos = new FileOutputStream(new File(filePath + "APK/" +
                            ApkName));

                    length = conn.getContentLength();

                    byte[] bt = new byte[1024];

                    int count = 0;
                    int updataJD = 0;
                    int i = 0;

                    while ((i = is.read(bt)) > 0) {
                        count += i;
                        updataJD = (int) (((float) count / length) * 100);
                        // 更新进度
                        publishProgress(updataJD);
                        fos.write(bt, 0, i);
                    }

                    fos.flush();
                    fos.close();
                    is.close();
                    // 下载完成
                    publishProgress(100);
                    System.err.println("下载文件成功");
                    return 1;
                } else {
                    System.err.println("下载文件失败");
                    return 0;
                }

            } catch (Exception e) {
                System.err.println("下载文件失败:" + e.getMessage());
                return 2;
            }
        }

    }

    /**
     * <br/> 方法名称: setNewVersionNum
     * <br/> 方法详述: 设置新版本号
     * <br/> 参数: newNum，新版本号
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    public void setNewVersionNum(String newNum) {
        mNewVersion.setText(newNum);
    }

    /**
     * <br/> 方法名称: setURL
     * <br/> 方法详述: 设置下载链接
     * <br/> 参数: url，下载链接
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */

    public void setURL(String url) {
        URL = url;
    }

    /**
     * <br/> 方法名称: setFilePath
     * <br/> 方法详述: 设置文件保存路径
     * <br/> 参数: path，下载链接
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    public void setFilePath(String path) {
        filePath = path;
    }

    /**
     * <br/> 方法名称: setFilePath
     * <br/> 方法详述: 设置文件保存路径
     * <br/> 参数: path，下载链接
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    public void setApkName(String name) {
        ApkName = name;
    }


    /**
     * <br/> 方法名称: setIsMustUpdate
     * <br/> 方法详述: 设置是否强制升级，true的时候隐藏停止下载按钮
     * <br/> 参数: b，是否强制升级
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    public void setIsMustUpdate(boolean b) {
         /*如果为强制升级，则隐藏停止下载按钮*/
        if (b) {
            mStopBtn.setVisibility(View.INVISIBLE);
        } else {
            mStopBtn.setVisibility(View.VISIBLE);
        }
    }

    ;

    /**
     * <br/> 方法名称: setOldVersionNum
     * <br/> 方法详述: 设置旧版本
     * <br/> 参数: oldNum，就版本号
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    public void setOldVersionNum(String oldNum) {
        mOldVersion.setText(oldNum);
    }

    /**
     * <br/> 方法名称: setVersionContent
     * <br/> 方法详述: 设置版本内容
     * <br/> 参数: content，版本内容
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    public void setVersionContent(String content) {
        mContent.setText(content);
    }

    /**
     * <br/> 方法名称: setSystemIcon
     * <br/> 方法详述: 设置版本内容
     * <br/> 参数: id，资源id
     * <br/> 返回值:
     * <br/> 异常抛出 Exception:
     * <br/> 异常抛出 NullPointerException:
     */
    public void setSystemIcon(int id) {
        mSystemIcon.setImageDrawable(getResources().getDrawable(id));
    }


    public LinearLayout getmActivityView() {
        return mActivity;
    }

    public ImageView getmSystemIconView() {
        return mSystemIcon;
    }

    public TextView getmOldVersionView() {
        return mOldVersion;
    }

    public TextView getmNewVersionView() {
        return mNewVersion;
    }

    public TextView getmContentView() {
        return mContent;
    }

    public Button getmUpdateView() {
        return mUpdate;
    }

    @Override
    public void onBackPressed() {
        if (mDialog != null) {
            mDialog.dismiss();
            downAsyn.cancel(true);
        }
        super.onBackPressed();
    }
}
