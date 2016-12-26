package com.stardon.stardontemplateapp.loading;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;

import com.stardon.stardontemplateapp.R;
import com.stardon.stardontemplateapp.loading.indicator.BallBeatIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallClipRotateIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallClipRotateMultipleIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallClipRotatePulseIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallGridBeatIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallGridPulseIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallPulseIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallPulseRiseIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallPulseSyncIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallRotateIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallScaleIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallScaleMultipleIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallScaleRippleIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallScaleRippleMultipleIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallSpinFadeLoaderIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallTrianglePathIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallZigZagDeflectIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BallZigZagIndicator;
import com.stardon.stardontemplateapp.loading.indicator.BaseIndicatorController;
import com.stardon.stardontemplateapp.loading.indicator.CubeTransitionIndicator;
import com.stardon.stardontemplateapp.loading.indicator.LineScaleIndicator;
import com.stardon.stardontemplateapp.loading.indicator.LineScalePartyIndicator;
import com.stardon.stardontemplateapp.loading.indicator.LineScalePulseOutIndicator;
import com.stardon.stardontemplateapp.loading.indicator.LineScalePulseOutRapidIndicator;
import com.stardon.stardontemplateapp.loading.indicator.LineSpinFadeLoaderIndicator;
import com.stardon.stardontemplateapp.loading.indicator.PacmanIndicator;
import com.stardon.stardontemplateapp.loading.indicator.SemiCircleSpinIndicator;
import com.stardon.stardontemplateapp.loading.indicator.SquareSpinIndicator;
import com.stardon.stardontemplateapp.loading.indicator.TriangleSkewSpinIndicator;

/**
 * 类名: LoadingIndicatorView
 * <br/>功能描述:消息等待框（用户请求状态）
 * <br/>提供了28种自定义控件，你可以直接用于布局文件，同时提供dialog，直接调用
 * <br/>initLoadingDialog来显示消息等待框
 * <br/>在布局文件1.自定义标签: xmlns:app="http://schemas.android.com/apk/res-auto"
 * <br/> 2.在控件使用中我们设置相应的自定义标签属性app:indicator="方法 "
 * <br/> 方法依次：与下图对应
 * <br/> BallPulse,BallGridPulse, BallClipRotate, BallClipRotatePulse,
 * <br/>SquareSpin,BallClipRotateMultiple,BallPulseRise,BallRotate,
 * <br/> CubeTransition,BallZigZag,BallZigZagDeflect,BallTrianglePath,
 * <br/> BallScale,LineScale,LineScaleParty,BallScaleMultiple,
 * <br/> BallPulseSync,BallBeat,LineScalePulseOut,LineScalePulseOutRapid,
 * <br/>BallScaleRipple,BallScaleRippleMultiple,BallSpinFadeLoader,LineSpinFadeLoader,
 * <br/>TriangleSkewSpin,Pacman,BallGridBeat,SemiCircleSpin
 * <br/><img width="234" height="273" src="../../../../../res/drawable/loading.gif" />
 * <br/>作者: 陈渝金
 * <br/>时间: 2016/12/2
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */




public class LoadingIndicatorView extends View {


    //indicators

    public static final int BallPulse = 0;
    public static final int BallGridPulse = 1;
    public static final int BallClipRotate = 2;
    public static final int BallClipRotatePulse = 3;
    public static final int SquareSpin = 4;
    public static final int BallClipRotateMultiple = 5;
    public static final int BallPulseRise = 6;
    public static final int BallRotate = 7;
    public static final int CubeTransition = 8;
    public static final int BallZigZag = 9;
    public static final int BallZigZagDeflect = 10;
    public static final int BallTrianglePath = 11;
    public static final int BallScale = 12;
    public static final int LineScale = 13;
    public static final int LineScaleParty = 14;
    public static final int BallScaleMultiple = 15;
    public static final int BallPulseSync = 16;
    public static final int BallBeat = 17;
    public static final int LineScalePulseOut = 18;
    public static final int LineScalePulseOutRapid = 19;
    public static final int BallScaleRipple = 20;
    public static final int BallScaleRippleMultiple = 21;
    public static final int BallSpinFadeLoader = 22;
    public static final int LineSpinFadeLoader = 23;
    public static final int TriangleSkewSpin = 24;
    public static final int Pacman = 25;
    public static final int BallGridBeat = 26;
    public static final int SemiCircleSpin = 27;
    /**
     * 默认加载的样式
     */
    private static int loadStyle = BallSpinFadeLoader;


    @IntDef(flag = true,
            value = {BallPulse, BallGridPulse, BallClipRotate, BallClipRotatePulse, SquareSpin, 
                    BallClipRotateMultiple, BallPulseRise, BallRotate, CubeTransition, 
                    BallZigZag, BallZigZagDeflect, BallTrianglePath, BallScale, LineScale, 
                    LineScaleParty, BallScaleMultiple, BallPulseSync, BallBeat, 
                    LineScalePulseOut, LineScalePulseOutRapid, BallScaleRipple, 
                    BallScaleRippleMultiple, BallSpinFadeLoader, LineSpinFadeLoader, 
                    TriangleSkewSpin, Pacman, BallGridBeat, SemiCircleSpin})
    
/*    @Retention(RetentionPolicy.CLASS.SOURCE)*/
    public @interface Indicator {
    }

    //Sizes (with defaults in DP)
    public static int DEFAULT_SIZE = 45;

    //attrs
    int mIndicatorId;
    int mIndicatorColor = Color.parseColor("#ff0f81d9");

    Paint mPaint;

    BaseIndicatorController mIndicatorController;

    private boolean mHasAnimation;


    public LoadingIndicatorView(Context context) {
        super(context);
        init(null, 0);
    }

    public LoadingIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public LoadingIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadingIndicatorView(Context context, AttributeSet attrs, int defStyleAttr, int 
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LoadingIndicatorView);
        mIndicatorId = a.getInt(R.styleable.LoadingIndicatorView_indicator, loadStyle);
        a.recycle();
        mPaint = new Paint();
        mPaint.setColor(mIndicatorColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        applyIndicator();
    }

    private void applyIndicator() {
        switch (mIndicatorId) {
            case BallPulse:
                mIndicatorController = new BallPulseIndicator();
                break;
            case BallGridPulse:
                mIndicatorController = new BallGridPulseIndicator();
                break;
            case BallClipRotate:
                mIndicatorController = new BallClipRotateIndicator();
                break;
            case BallClipRotatePulse:
                mIndicatorController = new BallClipRotatePulseIndicator();
                break;
            case SquareSpin:
                mIndicatorController = new SquareSpinIndicator();
                break;
            case BallClipRotateMultiple:
                mIndicatorController = new BallClipRotateMultipleIndicator();
                break;
            case BallPulseRise:
                mIndicatorController = new BallPulseRiseIndicator();
                break;
            case BallRotate:
                mIndicatorController = new BallRotateIndicator();
                break;
            case CubeTransition:
                mIndicatorController = new CubeTransitionIndicator();
                break;
            case BallZigZag:
                mIndicatorController = new BallZigZagIndicator();
                break;
            case BallZigZagDeflect:
                mIndicatorController = new BallZigZagDeflectIndicator();
                break;
            case BallTrianglePath:
                mIndicatorController = new BallTrianglePathIndicator();
                break;
            case BallScale:
                mIndicatorController = new BallScaleIndicator();
                break;
            case LineScale:
                mIndicatorController = new LineScaleIndicator();
                break;
            case LineScaleParty:
                mIndicatorController = new LineScalePartyIndicator();
                break;
            case BallScaleMultiple:
                mIndicatorController = new BallScaleMultipleIndicator();
                break;
            case BallPulseSync:
                mIndicatorController = new BallPulseSyncIndicator();
                break;
            case BallBeat:
                mIndicatorController = new BallBeatIndicator();
                break;
            case LineScalePulseOut:
                mIndicatorController = new LineScalePulseOutIndicator();
                break;
            case LineScalePulseOutRapid:
                mIndicatorController = new LineScalePulseOutRapidIndicator();
                break;
            case BallScaleRipple:
                mIndicatorController = new BallScaleRippleIndicator();
                break;
            case BallScaleRippleMultiple:
                mIndicatorController = new BallScaleRippleMultipleIndicator();
                break;
            case BallSpinFadeLoader:
                mIndicatorController = new BallSpinFadeLoaderIndicator();
                break;
            case LineSpinFadeLoader:
                mIndicatorController = new LineSpinFadeLoaderIndicator();
                break;
            case TriangleSkewSpin:
                mIndicatorController = new TriangleSkewSpinIndicator();
                break;
            case Pacman:
                mIndicatorController = new PacmanIndicator();
                break;
            case BallGridBeat:
                mIndicatorController = new BallGridBeatIndicator();
                break;
            case SemiCircleSpin:
                mIndicatorController = new SemiCircleSpinIndicator();
                break;
        }
        mIndicatorController.setTarget(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureDimension(dp2px(DEFAULT_SIZE), widthMeasureSpec);
        int height = measureDimension(dp2px(DEFAULT_SIZE), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureDimension(int defaultSize, int measureSpec) {
        int result = defaultSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize);
        } else {
            result = defaultSize;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawIndicator(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (!mHasAnimation) {
            mHasAnimation = true;
            applyAnimation();
        }
    }

    void drawIndicator(Canvas canvas) {
        mIndicatorController.draw(canvas, mPaint);
    }

    void applyAnimation() {
        mIndicatorController.createAnimation();
    }

    private int dp2px(int dpValue) {
        return (int) getContext().getResources().getDisplayMetrics().density * dpValue;
    }

    /**
     * @return color 颜色，默认蓝色
     */
    public int getColor() {
        return mIndicatorColor;
    }

    /**
     * @param color String 类型 ff0f81d9
     *              颜色，默认蓝色
     */
    @SuppressWarnings("deprecation")
    public void setColor(String color) {
        this.mIndicatorColor = Color.parseColor(color);
    }

    /**
     * @return size 显示大小，默认45dip。
     */
    public int getSize() {
        return DEFAULT_SIZE;
    }

    /**
     * @param size 显示大小，默认45dip。
     */
    public static void setSize(int size) {
        DEFAULT_SIZE = size;
    }

    public static void setStyle(int style) {
        loadStyle = style;


    }

}
