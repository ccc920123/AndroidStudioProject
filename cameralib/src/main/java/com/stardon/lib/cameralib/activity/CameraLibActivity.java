package com.stardon.lib.cameralib.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.stardon.lib.cameralib.R;
import com.stardon.lib.cameralib.activity.fragment.CameraFragment;
import com.stardon.lib.cameralib.activity.impl.CameraBase;
import com.stardon.lib.cameralib.activity.impl.SavePhoneImp;

/**
 * 类名: CameraLibActivity
 * <br/>功能描述: 拍照lib主入口
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/11/8
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */

public abstract class CameraLibActivity extends FragmentActivity implements CameraBase,SavePhoneImp {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameralib);
        //通过cameraLibActivity直接进入拍照预览界面
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, CameraFragment.newInstance())
                    .commit();
        }

    }


    /**
     * 方法名称: getCameraBack
     * <br/>方法详述: 直接调用的CameraFragment.getCameraBack()得到Camera界面的返回按钮，
     * <br/>这样方便其他开发者使用
     * <br/>参数:
     * <br/>返回值: Camera界面的返回按钮
     * <br/>异常抛出
     * <br/>异常抛出
     */
    protected TextView getCameraBack() {
        return CameraFragment.getCameraBack();

    }

    /**
     * 方法名称: setCameraTitleMessage
     * <br/>方法详述: 设置照片预览区域的title标题
     * <br/>参数:
     * <br/>返回值: Camera界面的返回按钮
     * <br/>异常抛出
     * <br/>异常抛出
     */
    protected void setCameraTitleMessage(String cameraTitleMessage) {

        CameraFragment.setCameraTitleMessage(cameraTitleMessage);
    }

    /**
     * 方法名称: setIsVisibleLightButton
     * <br/>方法详述: 是否显示摄像头转换按钮
     * <br/>参数: boolean true：显示；false：隐藏
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    protected void setIsVisibleCameraConvertButton(boolean isVisiable) {
        CameraFragment.setIsVisibleCameraConvertButton(isVisiable);

    }

    /**
     * 方法名称: setDefaultCamera
     * <br/>方法详述: 设置拍照摄像头的位置，默认为后摄像头。
     * <br/>参数:int   0:后摄像头    1：前摄像头
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    protected void setDefaultCamera(int CameraID) {
        CameraFragment.setDefaultCamera(CameraID);
    }

    /**
     * 方法名称: setWaterMarkTitle
     * <br/>方法详述: 设置水印的title多行请用”\r\n“换行符
     * <br/>参数: String waterMarkTitle
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    protected void setWaterMarkTitle(String waterMarkTitle) {
        CameraFragment.setWaterMarkTitle(waterMarkTitle);
    }
}
