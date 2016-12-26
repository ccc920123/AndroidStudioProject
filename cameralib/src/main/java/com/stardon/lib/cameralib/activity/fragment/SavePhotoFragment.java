package com.stardon.lib.cameralib.activity.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stardon.lib.cameralib.R;
import com.stardon.lib.cameralib.activity.impl.SavePhoneImp;
import com.stardon.lib.cameralib.activity.utils.ImageUtility;
import com.stardon.lib.cameralib.activity.utils.ZoomImageView;

import java.io.IOException;

/**
 * 类名: SavePhotoFragment
 * <br/>功能描述:相片预览界面，该界面支持相片的放缩，同时支持重拍。
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/11/9
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */

public class SavePhotoFragment extends Fragment {
    /**
     * tag
     */
    public static final String TAG = SavePhotoFragment.class.getSimpleName();
    /**
     * 常量，bitmap数据标识
     */
    public static final String BITMAP_KEY = "bitmap_byte_array";
    /**
     * 常量，标题标识，如果水印重前面传值过来
     */
    public static final String TITLE_KEY = "titleMessage";

    /**
     * 常量，相机数据标识
     */
    public static final String CAMERA_KEY = "camera_id";
    /**
     * 照片bitmap
     */
    private Bitmap photoBitmap;
    /**
     * 图片预览控件，支持放缩
     */
    private ZoomImageView photoImageView;
    /**
     * 保存按钮
     */
    private TextView savePhoto;
    /**
     * 返回按钮
     */
    private TextView mButtonBack;
    /**
     * activity
     */
    private Activity activity;
    /**
     * 回调接口
     */
    private SavePhoneImp mCallBack;
    /**
     * 重拍按钮
     */
    private TextView mCancel;

  private static int  rotationCamera;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallBack = (SavePhoneImp) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "你未实现SavePhoneImp接口");
        }
        this.activity = activity;

    }

    ;

    /**
     * 方法名称: newInstance
     * <br/>方法详述: 接受CameraFragmnet传过来的照片数据【byte】
     * <br/>参数:byte 照片数据
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public static Fragment newInstance(byte[] bitmapByteArray, String strWaterMarkTitle, int mCameraID,int rotation) {
        Fragment fragment = new SavePhotoFragment();
        Bundle args = new Bundle();
        args.putByteArray(BITMAP_KEY, bitmapByteArray);
        args.putString(TITLE_KEY, strWaterMarkTitle);
        args.putInt(CAMERA_KEY, mCameraID);
        rotationCamera=rotation;
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 方法名称: SavePhotoFragment
     * <br/>方法详述:
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_edit_save_photo, container,
                false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        byte[] data = getArguments().getByteArray(BITMAP_KEY);//得到拍照界面的照片byte
        //将数据通过回掉传递到实现接口的activity界面
        mCallBack.getPhoneDate(data);
        String title = getArguments().getString(TITLE_KEY);//得到标题（水印）
        photoImageView = (ZoomImageView) view.findViewById(R.id.photo);
        savePhoto = (TextView) view.findViewById(R.id.save_photo);
        mCallBack.getPhoneSave(savePhoto);
        mButtonBack = (TextView) view.findViewById(R.id.back);
        mCallBack.getPhoneBack(mButtonBack);
        mCancel = (TextView) view.findViewById(R.id.cancel);
        mCancel.setOnClickListener(cancelCkick);
        mCallBack.getPhoneCancel(mCancel);
        int mCamera = getArguments().getInt(CAMERA_KEY);
        setPicture(data, photoImageView, title, mCamera);

    }

    /**
     * 方法名称: cancelCkick
     * <br/>方法详述: 重新拍照
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private View.OnClickListener cancelCkick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            getFragmentManager().popBackStack();
        }
    };

    @Override
    public void onDestroy() {
        if (photoBitmap != null) {
            photoBitmap.recycle();
            photoBitmap = null;

        }
        super.onDestroy();
    }

    /**
     * 方法名称: setPicture
     * <br/>方法详述: 显示拍照的图片，
     * 执行步骤：先将byte转成bitmap，在设置到自定义放缩的图片view上
     * <br/>参数:  byte   ZoomImageView
     * <br/>返回值: null
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void setPicture(byte[] data, ZoomImageView photoImageView, String title, int id) {
        photoBitmap = ImageUtility.waterMarkPicture(activity, data, title);//得到bitmap
        if(id==1)
        {
            switch (rotationCamera)
            {
                case 0:
                    photoBitmap = ImageUtility.adjustPhotoRotation(photoBitmap, rotationCamera+180);
                    break;
                case 90:
                    photoBitmap = ImageUtility.adjustPhotoRotation(photoBitmap, rotationCamera+270);
                    break;
                case 180:
                    photoBitmap = ImageUtility.adjustPhotoRotation(photoBitmap, rotationCamera);
                    break;
                case 270:
//                    photoBitmap = ImageUtility.adjustPhotoRotation(photoBitmap, rotationCamera-90);
                    break;
            }

//            if(rotationCamera>180) {
//                photoBitmap = ImageUtility.adjustPhotoRotation(photoBitmap, rotationCamera);
//            }
        }
        mCallBack.getPhoneDate(photoBitmap);
        photoImageView.setImageBitmap(photoBitmap.copy(Bitmap.Config.ARGB_8888,
                true));
    }

    private int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
}
