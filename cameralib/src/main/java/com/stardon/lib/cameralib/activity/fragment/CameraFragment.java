package com.stardon.lib.cameralib.activity.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stardon.lib.cameralib.R;
import com.stardon.lib.cameralib.activity.CameraLibActivity;
import com.stardon.lib.cameralib.activity.utils.ButtonTools;
import com.stardon.lib.cameralib.activity.utils.ScreenUtils;
import com.stardon.lib.cameralib.activity.utils.SquareCameraPreview;

import java.io.IOException;
import java.util.List;

import static android.hardware.Camera.PictureCallback;
import static android.hardware.Camera.ShutterCallback;
import static android.hardware.Camera.getCameraInfo;
import static android.hardware.Camera.open;
import static com.stardon.lib.cameralib.R.id.cameraconvert;

@SuppressLint("NewApi")
public class CameraFragment extends Fragment implements SurfaceHolder.Callback,
        PictureCallback {
    /**
     * tag
     */
    public static final String TAG = CameraFragment.class.getSimpleName();
    /**
     * 常量，相机标识
     */
    public static final String CAMERA_ID_KEY = "camera_id";
    /**
     * 常量，模式标识
     */
    public static final String CAMERA_FLASH_KEY = "flash_mode";

    /**
     * 相机id
     */
    private static int mCameraID;
    /**
     * 相机模式
     */
    private String mFlashMode;
    /**
     * 预览界面的title
     */
    private static TextView mPictitle;
    /**
     * 相机
     */
    private static Camera mCamera;
    /**
     * 支持缩放预览界面
     */
    private SquareCameraPreview mPreviewView;
    /**
     * holder
     */
    private SurfaceHolder mSurfaceHolder;
    /**
     * 预览缩放事件
     */

    private CameraOrientationListener mOrientationListener;
    /**
     * 预览界面最大宽度
     */
    private int PREVIEW_SIZE_MAX_WIDTH;
    /**
     * 照片最大宽度
     */
    private int PICTURE_SIZE_MAX_WIDTH;

    /**
     * 灯光模式
     */
    private CheckBox cameraLight;
    /**
     * 聚焦模式
     */
    private CheckBox cameraFocus;
    /**
     * 摄像头切换
     */
    private TextView cameraConvert;
    /**
     * 摄像头切换cameraConvert的RelativeLayout
     */
    private static RelativeLayout relativeLayoutCameraConvert;
    /**
     * 返回
     */
    private static TextView cameraBack;
    /**
     * 拍照按钮
     */
    private static ImageView takePhotoBtn;
    /**
     * 水印文字
     */
    private static String StrWaterMarkTitle = "";

    private static CameraLibActivity CameraActivity;
    /**
     * 储存摄像头
     */
    private SharedPreferences sharedpr;

    private  int rotationCamera;
    /**
     * 方法名称: newInstance
     * <br/>方法详述: 拍照预览界面口
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    public static Fragment newInstance() {


        return new CameraFragment();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        CameraActivity = (CameraLibActivity) activity;
        mOrientationListener = new CameraOrientationListener(activity);//激活多点触控事件
        PREVIEW_SIZE_MAX_WIDTH = ScreenUtils.getScreenWidth(activity);//以屏幕宽度来设置预览宽度
        PICTURE_SIZE_MAX_WIDTH = ScreenUtils.getScreenWidth(activity);//以屏幕宽度来设置照片宽度
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedpr = getActivity().getSharedPreferences("CameraShp", 0);
        if (savedInstanceState == null) {
            mCameraID = sharedpr.getInt(CAMERA_ID_KEY, getBackCameraID());
//            mCameraID = getBackCameraID();
            mFlashMode = Camera.Parameters.FLASH_MODE_OFF;
        } else {
            mCameraID = savedInstanceState.getInt(CAMERA_ID_KEY);
            mFlashMode = savedInstanceState.getString(CAMERA_FLASH_KEY);
        }
        mOrientationListener.enable();
        mPictitle = (TextView) view.findViewById(R.id.pictitle);
        //聚焦
        cameraFocus = (CheckBox) view.findViewById(R.id.camerafocue);
        cameraFocus.setOnCheckedChangeListener(chenckListener);
        //切换
        cameraConvert = (TextView) view.findViewById(cameraconvert);

        relativeLayoutCameraConvert = (RelativeLayout) view.findViewById(R.id.relativelayoutcameraconvert);
        //灯光
        cameraLight = (CheckBox) view.findViewById(R.id.cameralight);
        cameraLight.setOnCheckedChangeListener(chenckListener);
        //摄像头切换
        cameraConvert = (TextView) view.findViewById(cameraconvert);
        cameraConvert.setOnClickListener(click);
        //返回
        cameraBack = (TextView) view.findViewById(R.id.cameraback);

        mPreviewView = (SquareCameraPreview) view
                .findViewById(R.id.camera_preview_view);
        mPreviewView.getHolder().addCallback(CameraFragment.this);
        final View changeCameraFlashModeBtn = view.findViewById(R.id.flash);
        final ImageView autoFlashIcon = (ImageView) view
                .findViewById(R.id.flash_icon);
        changeCameraFlashModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ButtonTools.isFastDoubleClick()) {
//                    if (mFlashMode
//                            .equalsIgnoreCase(Camera.Parameters.FLASH_MODE_AUTO)) {
//                        mFlashMode = Camera.Parameters.FLASH_MODE_ON;
//                        autoFlashIcon.setImageDrawable(getResources().getDrawable(
//                                R.mipmap.icon_toggle_flashon));
//                    } else
                    if (mFlashMode
                            .equalsIgnoreCase(Camera.Parameters.FLASH_MODE_ON)) {
                        mFlashMode = Camera.Parameters.FLASH_MODE_OFF;
                        autoFlashIcon.setImageDrawable(getResources().getDrawable(
                                R.mipmap.icon_toggle_flash));//关闭
                    } else if (mFlashMode
                            .equalsIgnoreCase(Camera.Parameters.FLASH_MODE_OFF)) {
                        mFlashMode = Camera.Parameters.FLASH_MODE_ON;
                        autoFlashIcon.setImageDrawable(getResources().getDrawable(
                                R.mipmap.icon_toggle_flashon));
//                        mFlashMode = Camera.Parameters.FLASH_MODE_AUTO;
//                        autoFlashIcon.setImageDrawable(getResources().getDrawable(
//                                R.mipmap.icon_toggle_flash));//自动

                    }
                    setupCamera();
                }
            }
        });
        //拍照按钮
        takePhotoBtn = (ImageView) view
                .findViewById(R.id.capture_image_button);
        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ButtonTools.isFastDoubleClick()) {
                    takePicture();
                }
            }
        });
        CameraActivity.bindView();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SharedPreferences.Editor editor = sharedpr.edit();
        editor.putInt(CAMERA_ID_KEY, mCameraID);
        editor.commit();
    }

    /**
     * 方法名称: chenckListener
     * <br/>方法详述:  聚焦和灯光的选中事件。
     * <br/>参数: null
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    private CompoundButton.OnCheckedChangeListener chenckListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView == cameraFocus) {
                setupCamera();
            } else {
                isOpenLight(isChecked);
            }

        }
    };
    /**
     * 方法名称: 点击事件
     * <br/>方法详述: 设置切换摄像头的点击事件。点击可以设置当前摄像头的位置（前，后）
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
         if(ButtonTools.isFastDoubleClick())
         {
             return;
         }
            if (mCameraID==0) {
                mCameraID = getFrontCameraID();
                // stop the preview
                if (mCamera != null) {
                    stopCameraPreview();
                    mCamera.release();
                    mCamera = null;
                }
                getCamera(mCameraID);
                if (mCamera != null) {
                    startCameraPreview();
                }


            } else {
                mCameraID = getBackCameraID();
                // stop the preview
                if (mCamera != null) {
                    stopCameraPreview();
                    mCamera.release();
                    mCamera = null;
                }
                getCamera(mCameraID);
                if (mCamera != null) {
                    startCameraPreview();
                }
            }


        }
    };

    /**
     * 方法名称: isOpenLight()
     * <br/>方法详述: 是否开启等灯光
     * <br/>参数:  boolean
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void isOpenLight(boolean isChaeck) {
//判断手机是否支持灯光
        if (getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            if (isChaeck) {
                Camera.Parameters parameters = mCamera
                        .getParameters();
                parameters
                        .setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                mCamera.setParameters(parameters);
                // camera.startPreview();
            } else {
                Camera.Parameters parameters = mCamera
                        .getParameters();
                parameters
                        .setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                mCamera.setParameters(parameters);
                // camera.startPreview();
            }
        } else {
            Toast.makeText(getActivity(), "当前设备部支持闪光", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(CAMERA_ID_KEY, mCameraID);
        outState.putString(CAMERA_FLASH_KEY, mFlashMode);
        super.onSaveInstanceState(outState);
    }

    /**
     * 方法名称: getCamera
     * <br/>方法详述: 通过传入摄像头id，打开相机
     * <br/>参数: 摄像头id
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void getCamera(int cameraID) {
        Log.d(TAG, "get camera with id " + cameraID);
        try {
            mCamera = open(cameraID);
            mPreviewView.setCamera(mCamera);
        } catch (Exception e) {
            Log.d(TAG, "Can't open camera with id " + cameraID);
            getActivity().onBackPressed();
            getActivity().finish();
            e.printStackTrace();
        }
    }

    /**
     * Start the camera preview
     */
    /**
     * 方法名称: startCameraPreview()
     * <br/>方法详述: 开始预览拍照
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void startCameraPreview() {
        determineDisplayOrientation();
        setupCamera();

        try {
            mCamera.setPreviewDisplay(mSurfaceHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Can't start camera preview due to IOException " + e);
            e.printStackTrace();
        }
    }

    /**
     * 方法名称: stopCameraPreview
     * <br/>方法详述: 停止预览
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void stopCameraPreview() {
        // Nulls out callbacks, stops face detection
        mCamera.setPreviewCallback(null);
        mCamera.stopPreview();
        mPreviewView.setCamera(null);
    }

    /**
     * 方法名称: determineDisplayOrientation
     * <br/>方法详述: 自动旋转，摄像头成像的图片角度(预览块)
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    private void determineDisplayOrientation() {
        CameraInfo cameraInfo = new CameraInfo();
        getCameraInfo(mCameraID, cameraInfo);

        int rotation = getActivity().getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;

        switch (rotation) {
            case Surface.ROTATION_0: {
                degrees = 0;
                break;
            }
            case Surface.ROTATION_90: {
                degrees = 90;
                break;
            }
            case Surface.ROTATION_180: {
                degrees = 180;
                break;
            }
            case Surface.ROTATION_270: {
                degrees = 270;

                break;
            }
        }

        int displayOrientation;

        // Camera direction
        if (cameraInfo.facing == CameraInfo.CAMERA_FACING_FRONT) {
            // Orientation is angle of rotation when facing the camera for
            // the camera image to match the natural orientation of the device
            displayOrientation = (cameraInfo.orientation + degrees) % 360;
            displayOrientation = (360 - displayOrientation) % 360;
        } else {
            displayOrientation = (cameraInfo.orientation - degrees + 360) % 360;
        }
//         mDisplayOrientation = (cameraInfo.orientation - degrees + 360) % 360;
//         mLayoutOrientation = degrees;
        // try{

        mCamera.setDisplayOrientation(displayOrientation);
        // }catch (NullPointerException e)
        // {
        // mCamera = Camera.open(getBackCameraID());
        // }
    }

    /**
     * Setup the camera parameters
     */
    /**
     * 方法名称: setupCamera
     * <br/>方法详述: 设置相机的parameters参数，包括预览，照片大小，是否连续自动对焦
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void setupCamera() {
        // Never keep a global parameters


        Camera.Parameters parameters = mCamera.getParameters();

        Size bestPreviewSize = determineBestPreviewSize(parameters);
        Size bestPictureSize = determineBestPictureSize(parameters);

        parameters
                .setPreviewSize(bestPreviewSize.width, bestPreviewSize.height);
        parameters
                .setPictureSize(bestPictureSize.width, bestPictureSize.height);

        //判断是否勾选中聚焦
        if (cameraFocus.isChecked()) {
            // Set continuous picture focus, if it's supported
            if (parameters.getSupportedFocusModes().contains(
                    Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                parameters
                        .setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//照连续聚焦
            }
        } else {
            if (parameters.getSupportedFocusModes().contains(
                    Camera.Parameters.FOCUS_MODE_AUTO)) {
                parameters
                        .setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);//自动焦点
            }
        }

        final View changeCameraFlashModeBtn = getView()
                .findViewById(R.id.flash);
        //得到相机闪光模式集合
        List<String> flashModes = parameters.getSupportedFlashModes();
        //判断手机是否支持闪光，支持就显示闪光调节，不支持隐藏闪光调节。
        if (flashModes != null && flashModes.contains(mFlashMode)) {
            parameters.setFlashMode(mFlashMode);
            changeCameraFlashModeBtn.setVisibility(View.VISIBLE);
        } else {
            changeCameraFlashModeBtn.setVisibility(View.GONE);
        }

        // Lock in the changes
        mCamera.setParameters(parameters);
    }

    /**
     * 方法名称:determineBestPreviewSize
     * <br/>方法详述: 预览区域
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private Size determineBestPreviewSize(Camera.Parameters parameters) {
        return determineBestSize(parameters.getSupportedPreviewSizes(),
                PREVIEW_SIZE_MAX_WIDTH);
    }

    /**
     * 方法名称:determineBestPreviewSize
     * <br/>方法详述: 预览照片
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    private Size determineBestPictureSize(Camera.Parameters parameters) {
        return determineBestSize(parameters.getSupportedPictureSizes(),
                PICTURE_SIZE_MAX_WIDTH);
    }

    /**
     * 方法名称: determineBestSize
     * <br/>方法详述: 选择最佳的照片集合
     * <br/>参数:  list 照片集合
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private Size determineBestSize(List<Size> sizes, int widthThreshold) {
        Size bestSize = null;
        Size size;
        int numOfSizes = sizes.size();
        for (int i = 0; i < numOfSizes; i++) {
            size = sizes.get(i);
            boolean isDesireRatio = (size.width / 4) == (size.height / 3);
            boolean isBetterSize = (bestSize == null)
                    || size.width > bestSize.width;

            if (isDesireRatio && isBetterSize) {
                bestSize = size;
            }
        }

        if (bestSize == null) {
            Log.d(TAG, "cannot find the best camera size");
            return sizes.get(sizes.size() - 1);
        }

        return bestSize;
    }

    /**
     * 方法名称: restartPreview()
     * <br/>方法详述: 重新开是预览
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void restartPreview() {
        stopCameraPreview();
        mCamera.release();

        getCamera(mCameraID);
        if (mCamera != null) {
            startCameraPreview();
        } else {
            restartPreview();
        }
    }

    /**
     * 方法名称: getFrontCameraID（）
     * <br/>方法详述: 得到前摄像头,如果不支持直接得后摄像头
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private int getFrontCameraID() {
        PackageManager pm = getActivity().getPackageManager();
        if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            return CameraInfo.CAMERA_FACING_FRONT;
        }

        return getBackCameraID();
    }

    /**
     * 方法名称: getBackCameraID()
     * <br/>方法详述: 得到后摄像头
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private static int getBackCameraID() {
        return CameraInfo.CAMERA_FACING_BACK;
    }

    /**
     * 方法名称: takePicture
     * <br/>方法详述: 拍照，将预览界面转换成byte
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    private void takePicture() {
        mOrientationListener.rememberOrientation();

        // Shutter callback occurs after the image is captured. This can
        // be used to trigger a sound to let the user know that image is taken
        ShutterCallback shutterCallback = null;

        // Raw callback occurs when the raw image data is available
        PictureCallback raw = null;

        // postView callback occurs when a scaled, fully processed
        // postView image is available.
        PictureCallback postView = null;

        // jpeg callback occurs when the compressed image is available
        try {
            //系统返回照片数据
            mCamera.takePicture(shutterCallback, raw, postView, this);
        } catch (Exception e) {

        }
    }

    @Override
    public void onStop() {
        mOrientationListener.disable();

        // stop the preview
        if (mCamera != null) {
            stopCameraPreview();
            mCamera.release();
            mCamera = null;
        }
        super.onStop();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mSurfaceHolder = holder;

        getCamera(mCameraID);
        if (mCamera != null) {
            startCameraPreview();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // stopCameraPreview();
        // mCamera.release();
        // The surface is destroyed with the visibility of the SurfaceView is
        // set to View.Invisible
    }

    // @Override
    // public void onActivityResult(int requestCode, int resultCode, Intent
    // data) {
    // if (resultCode != Activity.RESULT_OK) return;
    //
    // switch (requestCode) {
    // case 1:
    // Uri imageUri = data.getData();
    // break;
    //
    // default:
    // super.onActivityResult(requestCode, resultCode, data);
    // }
    // }

    /**
     * 方法名称: onPictureTaken
     * <br/>方法详述:  得到照片数据data
     * <br/>参数:
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {

        // int rotation = (
        // mDisplayOrientation
        // + mOrientationListener.getRememberedNormalOrientation()
        // + mLayoutOrientation
        // ) % 360;

        // Bitmap bitmap = ImageUtility.waterMarkPicture(getActivity(),
        // data,true);
        // Uri uri = ImageUtility.savePicture(getActivity(), bitmap);
        //将灯光至为关闭状态
        cameraLight.setChecked(false);
        rotationCamera=mOrientationListener.getRememberedNormalOrientation();
        //跳转到图片预览界面
        getFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.fragment_container,
                        SavePhotoFragment.newInstance(data, StrWaterMarkTitle,mCameraID,rotationCamera),
                        SavePhotoFragment.TAG).addToBackStack(null).commit();
    }


    /**
     * 类名: CameraOrientationListener
     * <br/>功能描述: 相机预览界面自动旋转类，在调用相机时手机预览界面与成像成颠倒情况
     * <br/>作者: 陈渝金
     * <br/>时间: 2016/11/9
     * <br/>最后修改者:
     * <br/>最后修改内容:
     */

    private static class CameraOrientationListener extends
            OrientationEventListener {

        private int mCurrentNormalizedOrientation;
        private int mRememberedNormalOrientation;

        public CameraOrientationListener(Context context) {
            super(context, SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        public void onOrientationChanged(int orientation) {
            if (orientation != ORIENTATION_UNKNOWN) {
                mCurrentNormalizedOrientation = normalize(orientation);
            }
        }

        private int normalize(int degrees) {
            if (degrees > 315 || degrees <= 45) {
                return 0;
            }

            if (degrees > 45 && degrees <= 135) {
                return 90;
            }

            if (degrees > 135 && degrees <= 225) {
                return 180;
            }

            if (degrees > 225 && degrees <= 315) {
                return 270;
            }

            throw new RuntimeException(
                    "The physics as we know them are no more. Watch out for anomalies.");
        }

        public void rememberOrientation() {
            mRememberedNormalOrientation = mCurrentNormalizedOrientation;
        }

        public int getRememberedNormalOrientation() {
            return mRememberedNormalOrientation;
        }
    }
//=================暴露方法区============

    /**
     * 方法名称: getCameraBack
     * <br/>方法详述: 得到拍照界面的返回按钮，你可以拿到该按钮设置自己喜欢的颜色，字体等包括事件
     * <br/>参数:
     * <br/>返回值:cameraBack返回按钮
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public static TextView getCameraBack() {
        return cameraBack;
    }

    /**
     * 方法名称: setCameraTitleMessage
     * <br/>方法详述: 设置拍照预览界面标题，次方法取决于用户是否设置 设置“”（空表示没有），默认“”
     * <br/>参数:titleMessage  String类型
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public static void setCameraTitleMessage(String titleMessage) {
        mPictitle.setText(titleMessage);//设置照片名称

    }

    /**
     * 方法名称: setIsVisibleCameraConvertButton
     * <br/>方法详述: 设置拍照预览界面是否显示转换摄像头按钮，此设置取决于用户。默认情况下显示
     * <br/>参数:boolean    true显示  false  隐藏
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public static void setIsVisibleCameraConvertButton(boolean isVisible) {
        if (isVisible) {
            relativeLayoutCameraConvert.setVisibility(View.VISIBLE);
        } else {
            relativeLayoutCameraConvert.setVisibility(View.GONE);
        }

    }

    /**
     * 方法名称: setDefaultCamera
     * <br/>方法详述: 设置拍照摄像头的位置，默认为后摄像头。
     * <br/>参数:int   0:后摄像头    1：前摄像头
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public static void setDefaultCamera(int cameraID) {
        mCameraID = cameraID;
    }

    /**
     * 方法名称: setWaterMarkTitle
     * <br/>方法详述: 设置水印的title多行请用”/\n“换行符
     * <br/>参数: String waterMarkTitle
     * <br/>返回值:
     * <br/>异常抛出 Exception:
     * <br/>异常抛出 NullPointerException:
     */
    public static void setWaterMarkTitle(String waterMarkTitle) {
        StrWaterMarkTitle = waterMarkTitle;
    }


}
